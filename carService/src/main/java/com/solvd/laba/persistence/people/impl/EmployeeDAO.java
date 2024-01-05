package com.solvd.laba.persistence.people.impl;

import com.solvd.laba.domain.people.Employee;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.contract.impl.ContractDAO;
import com.solvd.laba.persistence.contract.impl.MonthlyPaymentDAO;
import com.solvd.laba.persistence.order.impl.ServiceOrderDAO;
import com.solvd.laba.persistence.people.IEmployeeDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements IEmployeeDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO employees (workshop_id, name, surname, phone_number, position) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT e.id AS employee_id, e.name AS employee_name, e.surname AS employee_surname," +
            "                    e.phone_number AS employee_phone_number, e.position AS employee_position," +
            "                    m.id AS monthly_payment_id, m.amount AS monthly_payment_amount, m.payment_date AS monthly_payment_date," +
            "                    b.id AS bonus_payment_id, b.amount AS bonus_payment_amount, b.description AS bonus_payment_description," +
            "                    c.id AS contract_id, c.start_date AS contract_start_date, c.end_date AS contract_end_date, " +
            "                    c.type AS contract_type, c.salary AS contract_salary, c.active AS contract_active, " +
            "                    a.id AS account_id, a.login as account_login, a.password as account_password, r.id AS role_id, r.name AS role_name, " +
            "                    so.id AS service_order_id, so.date AS service_order_date, so.completed AS service_order_completed, so.description AS service_order_description " +
            "                    FROM employees e " +

            "                    LEFT JOIN service_orders so ON e.id = so.employees_id " +
            "                    LEFT JOIN monthly_payments m ON e.id = m.employees_id " +
            "                    LEFT JOIN bonus_payments b ON m.id = b.monthly_payments_id " +
            "                    LEFT JOIN contracts c ON e.id = c.employees_id" +
            "                    LEFT JOIN accounts a ON e.id = a.employees_id " +
            "                    LEFT JOIN roles_has_accounts ra ON a.id = ra.accounts_id " +
            "                    LEFT JOIN roles r ON ra.roles_id = r.id " +
            "                    WHERE e.id = ?";

    private static final String GET_ALL_BY_WORKSHOP_QUERY = "SELECT e.id AS employee_id, e.name AS employee_name, e.surname AS employee_surname," +
            "                    e.phone_number AS employee_phone_number, e.position AS employee_position," +
            "                    m.id AS monthly_payment_id, m.amount AS monthly_payment_amount, m.payment_date AS monthly_payment_date," +
            "                    b.id AS bonus_payment_id, b.amount AS bonus_payment_amount, b.description AS bonus_payment_description," +
            "                    c.id AS contract_id, c.start_date AS contract_start_date, c.end_date AS contract_end_date, " +
            "                    c.type AS contract_type, c.salary AS contract_salary, c.active AS contract_active " +
            "                    FROM employees e " +
            "                    LEFT JOIN monthly_payments m ON e.id = m.employees_id " +
            "                    LEFT JOIN bonus_payments b ON m.id = b.monthly_payments_id " +
            "                    LEFT JOIN contracts c ON e.id = c.employees_id WHERE e.workshops_id = ?";
    private static final String UPDATE_QUERY = "UPDATE employees SET workshop_id = ?, name = ?, surname = ?, phone_number = ?, position = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM employees WHERE id = ?";

    public static List<Employee> mapRow(ResultSet resultSet, List<Employee> employees) throws SQLException {


        if (employees == null) {
            employees = new ArrayList<>();
        }


        Long employeeId = resultSet.getLong("employee_id");

        if (employeeId != 0) {
            Employee employee = findById(employeeId, employees);

            employee.setId(employeeId);
            employee.setName(resultSet.getString("employee_name"));
            employee.setSurname(resultSet.getString("employee_surname"));
            employee.setPhoneNumber(resultSet.getString("employee_phone_number"));
            employee.setPosition(resultSet.getString("employee_position"));

            employee.setMonthlyPayments(MonthlyPaymentDAO.mapMonthlyPayments(resultSet, employee.getMonthlyPayments()));

            employee.setContracts(ContractDAO.mapRow(resultSet, employee.getContracts()));

            // employee.setAccount(AccountDAO.mapAccount(resultSet));

            employee.setServiceOrders(ServiceOrderDAO.mapRow(resultSet, employee.getServiceOrders()));

            employees.add(employee);

        }

        return employees;
    }

    private static Employee findById(Long id, List<Employee> employees) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Employee newEmployee = new Employee();
                    newEmployee.setId(id);
                    employees.add(newEmployee);
                    return newEmployee;
                });
    }


    @Override
    public void create(Employee employee, Long workshopId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, workshopId);
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getSurname());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setString(5, employee.getPosition());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    employee.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating employee failed, no ID obtained.");
                }
            }

            LOGGER.info("Employee created: {}", employee);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Employee getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                employees = mapRow(result, employees);
            }


        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return employees.get(0);
    }


    @Override
    public void update(Employee employee) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getSurname());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setString(5, employee.getPosition());
            preparedStatement.setLong(6, employee.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Employee updated: {}", employee);

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

            LOGGER.info("Employee deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
