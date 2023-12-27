package com.solvd.laba.persistence.order.impl;

import com.solvd.laba.domain.order.Invoice;
import com.solvd.laba.domain.order.ServiceOrder;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.order.IServiceOrderDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceOrderDAO implements IServiceOrderDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO service_orders (date, completed, description, invoice_id) VALUES (?, ?, ?, ?)";
    private static final String GET_BY_ID_QUERY =
            "SELECT " +
                    "   so.id AS service_order_id, so.date AS service_order_date, so.completed AS service_order_completed, " +
                    "   so.description AS service_order_description, i.id as invoice_id, i.date_time as invoice_date_time, i.total_price as invoice_total_price, " +
                    "   p.product_number as product_number, p.name as product_name, p.price as product_price " +
                    "FROM service_orders so " +
                    "LEFT JOIN invoices i ON so.invoices_id = i.id " +
                    "LEFT JOIN invoices_has_products ihp ON ihp.invoices_id = i.id " +
                    "LEFT JOIN products p ON p.id = ihp.products_id " +
                    "WHERE so.id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM service_orders";
    private static final String UPDATE_QUERY = "UPDATE service_orders SET date = ?, completed = ?, description = ?, invoice_id = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM service_orders WHERE id = ?";

    public static List<ServiceOrder> mapRow(ResultSet resultSet, List<ServiceOrder> serviceOrders) throws SQLException {
        if (serviceOrders == null) {
            serviceOrders = new ArrayList<>();
        }

        Long id = resultSet.getLong("service_order_id");

        if (id != 0) {
            if (!serviceOrders.stream().anyMatch(so -> so.getId() == id)) {

                ServiceOrder serviceOrder = findById(id, serviceOrders);
                serviceOrder.setDescription(resultSet.getString("service_order_description"));
                serviceOrder.setCompleted(resultSet.getBoolean("service_order_completed"));
                serviceOrder.setDate(resultSet.getDate("service_order_date"));

                // serviceOrders.add(serviceOrder);

            }

        /*    Invoice invoice = InvoiceDAO.mapInvoice(resultSet);
            if (invoice != null) {
                serviceOrder.setInvoice(invoice);
            }*/


        }

        return serviceOrders;
    }

    private static ServiceOrder findById(Long id, List<ServiceOrder> serviceOrders) {
        return serviceOrders.stream()
                .filter(vehicle -> vehicle.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    ServiceOrder newServiceORder = new ServiceOrder();
                    newServiceORder.setId(id);
                    serviceOrders.add(newServiceORder);
                    return newServiceORder;
                });
    }

    @Override
    public void create(ServiceOrder serviceOrder) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setDate(1, serviceOrder.getDate());
            preparedStatement.setBoolean(2, serviceOrder.isCompleted());
            preparedStatement.setString(3, serviceOrder.getDescription());
            if (serviceOrder.getInvoice().isPresent()) {
                preparedStatement.setLong(4, serviceOrder.getInvoice().get().getId());
            } else {
                preparedStatement.setObject(4, null);
            }

            preparedStatement.executeUpdate();

            LOGGER.info("ServiceOrder created: {}", serviceOrder);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public ServiceOrder getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        List<ServiceOrder> serviceOrders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                serviceOrders = mapRow(result, serviceOrders);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return serviceOrders.get(0);
    }

    @Override
    public List<ServiceOrder> getAll() {
        List<ServiceOrder> serviceOrders = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                ServiceOrder serviceOrder = mapServiceOrder(result);
                serviceOrders.add(serviceOrder);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return serviceOrders;
    }

    @Override
    public void update(ServiceOrder serviceOrder) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setDate(1, serviceOrder.getDate());
            preparedStatement.setBoolean(2, serviceOrder.isCompleted());
            preparedStatement.setString(3, serviceOrder.getDescription());
            if (serviceOrder.getInvoice().isPresent()) {
                preparedStatement.setLong(4, serviceOrder.getInvoice().get().getId());
            } else {
                preparedStatement.setObject(4, null);
            }
            preparedStatement.setLong(5, serviceOrder.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("ServiceOrder updated: {}", serviceOrder);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            LOGGER.info("ServiceOrder deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    private ServiceOrder mapServiceOrder(ResultSet resultSet) throws SQLException {
        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setId(resultSet.getLong("id"));
        serviceOrder.setDate(resultSet.getDate("date"));
        serviceOrder.setCompleted(resultSet.getBoolean("completed"));
        serviceOrder.setDescription(resultSet.getString("description"));
        long invoiceId = resultSet.getLong("invoice_id");
        if (!resultSet.wasNull()) {
            Invoice invoice = new Invoice();
            invoice.setId(invoiceId);
            serviceOrder.setInvoice(invoice);
        } else {
            serviceOrder.setInvoice(null);
        }
        return serviceOrder;
    }
}
