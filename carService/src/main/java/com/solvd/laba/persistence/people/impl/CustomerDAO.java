package com.solvd.laba.persistence.people.impl;

import com.solvd.laba.domain.people.Customer;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.order.impl.ServiceOrderDAO;
import com.solvd.laba.persistence.people.ICustomerDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends PersonDAO<Customer> implements ICustomerDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO customers (name, surname, phone_number) VALUES (?, ?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT c.id AS customer_id, c.name AS customer_name, c.surname AS customer_surname, c.email as customer_email," +
            "                    c.phone_number AS customer_phone_number, so.id AS service_order_id, so.date AS service_order_date, so.description AS service_order_description, so.completed as service_order_completed  " +
            "                    FROM customers c " +
            "                    LEFT JOIN service_orders so ON c.id = so.customers_id " +
            "                    WHERE c.id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM customers";
    private static final String UPDATE_QUERY = "UPDATE customers SET name = ?, surname = ?, phone_number = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM customers WHERE id = ?";

    public List<Customer> mapRow(ResultSet resultSet, List<Customer> customers) throws SQLException {
        if (customers == null) {
            customers = new ArrayList<>();
        }

        Long customerId = resultSet.getLong("customer_id");

        if (customerId != 0) {
            Customer customer = findById(customerId, customers);

            customer.setId(customerId);
            customer.setEmail(resultSet.getString("customer_email"));
            customer.setName(resultSet.getString("customer_name"));
            customer.setSurname(resultSet.getString("customer_surname"));
            customer.setPhoneNumber(resultSet.getString("customer_phone_number"));

            customer.setServiceOrders(ServiceOrderDAO.mapRow(resultSet, customer.getServiceOrders()));

            customers.add(customer);
        }

        return customers;
    }

    private Customer findById(Long id, List<Customer> customers) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Customer newCustomer = new Customer();
                    newCustomer.setId(id);
                    customers.add(newCustomer);
                    return newCustomer;
                });
    }

    @Override
    public void create(Customer customer) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setString(3, customer.getPhoneNumber());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating customer failed, no ID obtained.");
                }
            }

            LOGGER.info("Customer created: {}", customer);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Customer getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                customers = mapRow(result, customers);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return customers.get(0);
    }

    @Override
    public List<Customer> getAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                customers = mapRow(result, customers);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return customers;
    }

    @Override
    public void update(Customer customer) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setLong(4, customer.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Customer updated: {}", customer);

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

            LOGGER.info("Customer deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
