package com.solvd.laba.dao.contract.impl;

import com.solvd.laba.domain.contract.BonusPayment;
import com.solvd.laba.domain.contract.MonthlyPayment;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.contract.IMonthlyPaymentDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MonthlyPaymentDAO implements IMonthlyPaymentDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO monthly_payments (amount, payment_date) VALUES (?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM monthly_payments WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM monthly_payments";
    private static final String GET_BONUS_PAYMENTS_BY_MONTHLY_PAYMENT_ID_QUERY =
            "SELECT * FROM bonus_payments WHERE monthly_payment_id = ?";
    private static final String UPDATE_QUERY = "UPDATE monthly_payments SET amount = ?, payment_date = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM monthly_payments WHERE id = ?";

    @Override
    public void create(MonthlyPayment monthlyPayment) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setDouble(1, monthlyPayment.getAmount());
            preparedStatement.setDate(2, monthlyPayment.getPaymentDate());
            preparedStatement.executeUpdate();
            LOGGER.info("MonthlyPayment created: {}", monthlyPayment);
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
            if (result.next()) {
                monthlyPayment = new MonthlyPayment();
                monthlyPayment.setId(result.getLong("id"));
                monthlyPayment.setAmount(result.getDouble("amount"));
                monthlyPayment.setPaymentDate(result.getDate("payment_date"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return monthlyPayment;
    }

    @Override
    public List<MonthlyPayment> getAll() {
        List<MonthlyPayment> monthlyPayments = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                MonthlyPayment monthlyPayment = new MonthlyPayment();
                monthlyPayment.setId(result.getLong("id"));
                monthlyPayment.setAmount(result.getDouble("amount"));
                monthlyPayment.setPaymentDate(result.getDate("payment_date"));
                monthlyPayments.add(monthlyPayment);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return monthlyPayments;
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

    @Override
    public List<BonusPayment> getBonusPaymentsByMonthlyPaymentId(Long monthlyPaymentId) {
        List<BonusPayment> bonusPayments = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BONUS_PAYMENTS_BY_MONTHLY_PAYMENT_ID_QUERY)) {
            preparedStatement.setLong(1, monthlyPaymentId);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                BonusPayment bonusPayment = new BonusPayment();
                bonusPayment.setId(result.getLong("id"));
                bonusPayment.setAmount(result.getDouble("amount"));
                bonusPayment.setDescription(result.getString("description"));
                bonusPayments.add(bonusPayment);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return bonusPayments;
    }
}
