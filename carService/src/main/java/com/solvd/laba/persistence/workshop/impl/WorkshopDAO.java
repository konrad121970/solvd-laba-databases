package com.solvd.laba.persistence.workshop.impl;

import com.solvd.laba.domain.workshop.Workshop;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.order.impl.ServiceOrderDAO;
import com.solvd.laba.persistence.people.impl.EmployeeDAO;
import com.solvd.laba.persistence.stock.impl.StockDAO;
import com.solvd.laba.persistence.workshop.IWorkshopDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkshopDAO implements IWorkshopDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String CREATE_QUERY =
            "INSERT INTO workshops (addresses_id, name, nip) VALUES (?, ?, ?)";

    private static final String GET_BY_ID_QUERY =
            "SELECT " +
                    "    a.id AS address_id, a.city AS address_city, a.street AS address_street,  " +
                    "    a.building_number AS address_building_number, a.postal_code AS address_postal_code,  " +
                    "    w.id AS workshop_id, w.name AS workshop_name, w.nip AS workshop_nip,  " +
                    "    s.id AS stock_id, s.name AS stock_name,  " +
                    "    p.id AS product_id, p.product_number, p.name AS product_name, p.price AS product_price,  " +
                    "    e.id AS employee_id, e.name AS employee_name, e.surname AS employee_surname, e.phone_number AS employee_phone_number, e.position as employee_position, " +
                    "    c.id AS contract_id, c.start_date AS contract_start_date, c.end_date AS contract_end_date,  " +
                    "    c.type AS contract_type, c.salary AS contract_salary, c.active AS contract_active,  " +
                    "    m.id AS monthly_payment_id, m.amount AS monthly_payment_amount, m.payment_date AS monthly_payment_date,  " +
                    "    b.id AS bonus_payment_id, b.amount AS bonus_payment_amount, b.description AS bonus_payment_description,  " +
                    "    ac.id AS account_id, ac.login AS account_login, ac.password AS account_password,  " +
                    "    r.id AS role_id, r.name AS role_name,  " +
                    "    i.id AS invoice_id, i.date_time AS invoice_date_time, i.total_price AS invoice_total_price,  " +
                    "    cst.id AS customer_id, cst.name AS customer_name, cst.surname AS customer_surname,  " +
                    "    cst.email AS customer_email, cst.phone_number AS customer_phone_number,  " +
                    "    v.id AS vehicle_id, v.vin, v.make, v.model, v.license_plate,  " +
                    "    so.id AS service_order_id, so.vehicles_id, so.customers_id, so.date AS service_order_date,  " +
                    "    so.completed AS service_order_completed, so.invoices_id AS service_order_invoices_id,  " +
                    "    so.workshop_id AS service_order_workshop_id, so.employees_id AS service_order_employees_id,  " +
                    "    so.description AS service_order_description  " +
                    "FROM addresses a  " +
                    "LEFT JOIN workshops w ON a.id = w.addresses_id  " +
                    "LEFT JOIN stocks s ON w.id = s.workshops_id  " +
                    "LEFT JOIN products p ON s.id = p.id  " +
                    "LEFT JOIN stocks_has_products sp ON s.id = sp.stocks_id  " +
                    "LEFT JOIN employees e ON w.id = e.workshop_id  " +
                    "LEFT JOIN contracts c ON e.id = c.employees_id  " +
                    "LEFT JOIN monthly_payments m ON e.id = m.employees_id  " +
                    "LEFT JOIN bonus_payments b ON m.id = b.monthly_payments_id  " +
                    "LEFT JOIN accounts ac ON e.id = ac.employees_id  " +
                    "LEFT JOIN roles_has_accounts ra ON ac.id = ra.accounts_id  " +
                    "LEFT JOIN roles r ON ra.roles_id = r.id " +
                    "LEFT JOIN invoices_has_products ihp ON ihp.products_id = p.id " +
                    "LEFT JOIN service_orders so ON w.id = so.workshop_id  " +
                    "LEFT JOIN invoices i ON i.id = ihp.invoices_id  " +
                    "LEFT JOIN customers cst ON cst.id = so.customers_id  " +
                    "LEFT JOIN vehicles v ON v.id = so.vehicles_id  " +
                    "WHERE w.id = ?";

    private static final String UPDATE_WORKSHOP_QUERY =
            "UPDATE workshops SET addresses_id = ?, name = ?, nip = ? WHERE id = ?";

    private static final String DELETE_WORKSHOP_QUERY =
            "DELETE FROM workshops WHERE id = ?";

    @Override
    public void create(Workshop workshop, Long addressId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {

            preparedStatement.setLong(1, addressId);
            preparedStatement.setString(2, workshop.getName());
            preparedStatement.setString(3, workshop.getNip());

            preparedStatement.executeUpdate();

            LOGGER.info("Workshop created: {}", workshop);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Workshop getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Workshop workshop = new Workshop();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {

            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                mapWorkshop(result, workshop);
                workshop.setEmployees(EmployeeDAO.mapRow(result, workshop.getEmployees()));
                workshop.setServiceOrders(ServiceOrderDAO.mapRow(result, workshop.getServiceOrders()));
                workshop.setStocks(StockDAO.mapRow(result, workshop.getStocks()));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return workshop;
    }


    @Override
    public void update(Workshop workshop) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_WORKSHOP_QUERY)) {

            preparedStatement.setLong(1, workshop.getAddress().getId());
            preparedStatement.setString(2, workshop.getName());
            preparedStatement.setString(3, workshop.getNip());
            preparedStatement.setLong(4, workshop.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Workshop updated: {}", workshop);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_WORKSHOP_QUERY)) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            LOGGER.info("Workshop deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    private Workshop mapWorkshop(ResultSet result, Workshop workshop) throws SQLException {
        workshop.setId(result.getLong("workshop_id"));
        workshop.setName(result.getString("workshop_name"));
        workshop.setNip(result.getString("workshop_nip"));

        workshop.setAddress(AddressDAO.mapAddress(result));
        workshop.setEmployees(EmployeeDAO.mapRow(result, workshop.getEmployees()));

        return workshop;
    }
}
