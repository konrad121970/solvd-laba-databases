package com.solvd.laba.persistence.order.impl;

import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.order.IServiceOrderDAO;
import com.solvd.laba.domain.order.Invoice;
import com.solvd.laba.domain.order.ServiceOrder;
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
    private static final String GET_BY_ID_QUERY = "SELECT * FROM service_orders WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM service_orders";
    private static final String UPDATE_QUERY = "UPDATE service_orders SET date = ?, completed = ?, description = ?, invoice_id = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM service_orders WHERE id = ?";

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
        ServiceOrder serviceOrder = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                serviceOrder = mapServiceOrder(result);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return serviceOrder;
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
