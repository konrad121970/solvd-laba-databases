package com.solvd.laba.persistence.contract.impl;

import com.solvd.laba.domain.contract.BonusPayment;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.contract.IBonusPaymentDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BonusPaymentDAO implements IBonusPaymentDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO bonus_payments (amount, description, monthly_payments_id) VALUES (?, ?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM bonus_payments WHERE id = ?";
    /*   private static final String GET_ALL_QUERY = "SELECT * FROM bonus_payments";*/
    private static final String UPDATE_QUERY = "UPDATE bonus_payments SET amount = ?, description = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM bonus_payments WHERE id = ?";
    private static final String GET_BONUS_PAYMENTS_BY_MONTHLY_PAYMENT_ID_QUERY = "SELECT * FROM bonus_payments WHERE monthly_payments_id = ?";

    public static List<BonusPayment> mapRow(ResultSet resultSet, List<BonusPayment> bonusPayments) throws SQLException {
        Long id = resultSet.getLong("bonus_payment_id");

        if (id != 0) {
            if (bonusPayments == null) {
                bonusPayments = new ArrayList<>();
            }

            BonusPayment bonusPayment = findById(id, bonusPayments);
            bonusPayment.setAmount(resultSet.getDouble("bonus_payment_amount"));
            bonusPayment.setDescription(resultSet.getString("bonus_payment_description"));
        }
        return bonusPayments;
    }

    private static BonusPayment findById(Long id, List<BonusPayment> bonusPayments) {
        return bonusPayments.stream().filter(bonusPayment -> bonusPayment.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    BonusPayment newBonusPayment = new BonusPayment();
                    newBonusPayment.setId(id);
                    bonusPayments.add(newBonusPayment);
                    return newBonusPayment;
                });
    }

    @Override
    public void create(BonusPayment bonusPayment, Long monthlyPaymentId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setDouble(1, bonusPayment.getAmount());
            preparedStatement.setString(2, bonusPayment.getDescription());
            preparedStatement.setLong(3, monthlyPaymentId);

            preparedStatement.executeUpdate();

            LOGGER.info("BonusPayment created: {}", bonusPayment);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public BonusPayment getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        BonusPayment bonusPayment = new BonusPayment();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                bonusPayment.setId(result.getLong("id"));
                bonusPayment.setAmount(result.getDouble("amount"));
                bonusPayment.setDescription(result.getString("description"));

            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return bonusPayment;
    }

    @Override
    public void update(BonusPayment bonusPayment) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setDouble(1, bonusPayment.getAmount());
            preparedStatement.setString(2, bonusPayment.getDescription());
            preparedStatement.setLong(3, bonusPayment.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("BonusPayment updated: {}", bonusPayment);

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

            LOGGER.info("BonusPayment deleted with id: {}", id);

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
