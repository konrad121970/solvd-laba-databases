package com.solvd.laba.persistence.contract.impl;

import com.solvd.laba.domain.contract.BonusPayment;
import com.solvd.laba.domain.contract.MonthlyPayment;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.contract.IMonthlyPaymentDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonthlyPaymentDAO implements IMonthlyPaymentDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO monthly_payments (amount, payment_date, employees_id) VALUES (?, ?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT mp.id AS monthly_payment_id, mp.amount as monthly_payment_amount, mp.payment_date as monthly_payment_date, bp.id AS bonus_payment_id, bp.amount AS bonus_payment_amount, bp.description as bonus_payment_description" +
            "FROM monthly_payments mp " +
            "LEFT JOIN bonus_payments bp ON mp.id = bp.monthly_payments_id " +
            "WHERE mp.id = ?";
    private static final String GET_ALL_QUERY = "SELECT mp.id AS monthly_payment_id, mp.amount as monthly_payment_amount, mp.payment_date as monthly_payment_date, " +
            "bp.id AS bonus_payment_id, bp.amount AS bonus_payment_amount, bp.description AS bonus_payment_description " +
            "FROM monthly_payments mp " +
            "LEFT JOIN bonus_payments bp ON mp.id = bp.monthly_payments_id";


    private static final String UPDATE_QUERY = "UPDATE monthly_payments SET amount = ?, payment_date = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM monthly_payments WHERE id = ?";

    public static List<MonthlyPayment> mapMonthlyPayments(ResultSet resultSet, List<MonthlyPayment> monthlyPayments) throws SQLException {

        if (monthlyPayments == null) {
            monthlyPayments = new ArrayList<>();
        }

        Long id = resultSet.getLong("monthly_payment_id");

        if (id != 0) {
            MonthlyPayment monthlyPayment = findById(id, monthlyPayments);
            monthlyPayment.setAmount(resultSet.getDouble("monthly_payment_amount"));
            monthlyPayment.setPaymentDate(resultSet.getDate("monthly_payment_date"));

            List<BonusPayment> bonusPayments = BonusPaymentDAO.mapRow(resultSet, monthlyPayment.getBonusPaymentList());
            monthlyPayment.setBonusPaymentList(bonusPayments);
        }

        return monthlyPayments;
    }

    public static MonthlyPayment findById(Long id, List<MonthlyPayment> monthlyPayments) {
        return monthlyPayments.stream()
                .filter(monthlyPayment -> monthlyPayment.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    MonthlyPayment newMOnthlyPAyment = new MonthlyPayment();
                    newMOnthlyPAyment.setId(id);
                    monthlyPayments.add(newMOnthlyPAyment);
                    return newMOnthlyPAyment;
                });
    }

    @Override
    public List<MonthlyPayment> getAllMonthlyPaymentsByEmployeeId(Long employeeId) {
        List<MonthlyPayment> monthlyPayments = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY + " WHERE employees_id = ?")) {
            preparedStatement.setLong(1, employeeId);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                mapMonthlyPayments(result, monthlyPayments);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return monthlyPayments;
    }

    @Override
    public void create(MonthlyPayment monthlyPayment, Long employeesId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, monthlyPayment.getAmount());
            preparedStatement.setDate(2, monthlyPayment.getPaymentDate());
            preparedStatement.setLong(3, employeesId);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                long generatedId = generatedKeys.getLong(1);
                monthlyPayment.setId(generatedId);
                LOGGER.info("MonthlyPayment created with id: {}", generatedId);
            } else {
                LOGGER.warn("Failed to retrieve generated id for MonthlyPayment");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public MonthlyPayment getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        MonthlyPayment monthlyPayment = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();

            Map<Long, MonthlyPayment> monthlyPaymentsMap = new HashMap<>();

            while (result.next()) {
                Long monthlyPaymentId = result.getLong("monthly_payment_id");

                if (!monthlyPaymentsMap.containsKey(monthlyPaymentId)) {
                    monthlyPayment = new MonthlyPayment();
                    monthlyPayment.setId(monthlyPaymentId);
                    monthlyPayment.setAmount(result.getDouble("amount"));
                    monthlyPayment.setPaymentDate(result.getDate("payment_date"));
                    monthlyPayment.setBonusPaymentList(new ArrayList<>());

                    monthlyPaymentsMap.put(monthlyPaymentId, monthlyPayment);
                }

                List<BonusPayment> bonusPayments = BonusPaymentDAO.mapRow(result, monthlyPayment.getBonusPaymentList());
                monthlyPayment.setBonusPaymentList(bonusPayments);
            }

            monthlyPayment = new ArrayList<>(monthlyPaymentsMap.values()).get(0);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return monthlyPayment;
    }

    @Override
    public void update(MonthlyPayment monthlyPayment) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setDouble(1, monthlyPayment.getAmount());
            preparedStatement.setDate(2, monthlyPayment.getPaymentDate());
            preparedStatement.setLong(3, monthlyPayment.getId());
            preparedStatement.executeUpdate();
            LOGGER.info("MonthlyPayment updated: {}", monthlyPayment);
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
            LOGGER.info("MonthlyPayment deleted with id: {}", id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
