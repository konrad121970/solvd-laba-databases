package com.solvd.laba.persistence.contract.impl;

import com.solvd.laba.domain.contract.Contract;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.contract.IContractDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContractDAO implements IContractDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO contracts (start_date, end_date, type, salary, active, employees_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM contracts WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM contracts";
    private static final String UPDATE_QUERY = "UPDATE contracts SET start_date = ?, end_date = ?, type = ?, salary = ?, active = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM contracts WHERE id = ?";

    public static List<Contract> mapRow(ResultSet resultSet, List<Contract> contracts) throws SQLException {
        Long id = resultSet.getLong("contract_id");

        if (contracts == null) {
            contracts = new ArrayList<>();
        }

        if (id != 0) {
            Contract contract = findById(id, contracts);
            contract.setStartDate(resultSet.getDate("contract_start_date"));
            contract.setEndDate(resultSet.getDate("contract_end_date"));
            contract.setType(resultSet.getString("contract_type"));
            contract.setSalary(resultSet.getDouble("contract_salary"));
            contract.setActive(resultSet.getBoolean("contract_active"));
        }
        return contracts;
    }

    private static Contract findById(Long id, List<Contract> contracts) {
        return contracts.stream()
                .filter(contract -> contract.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Contract newContract = new Contract();
                    newContract.setId(id);
                    contracts.add(newContract);
                    return newContract;
                });
    }

    @Override
    public void create(Contract contract, Long employeeId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setDate(1, contract.getStartDate());
            preparedStatement.setDate(2, contract.getEndDate());
            preparedStatement.setString(3, contract.getType());
            preparedStatement.setDouble(4, contract.getSalary());
            preparedStatement.setBoolean(5, contract.isActive());
            preparedStatement.setLong(6, employeeId);

            preparedStatement.executeUpdate();

            LOGGER.info("Contract created: {}", contract);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Contract getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Contract contract = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                contract = mapContract(result);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return contract;
    }

    @Override
    public List<Contract> getAll() {
        List<Contract> contracts = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Contract contract = mapContract(result);
                contracts.add(contract);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return contracts;
    }

    @Override
    public void update(Contract contract) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setDate(1, contract.getStartDate());
            preparedStatement.setDate(2, contract.getEndDate());
            preparedStatement.setString(3, contract.getType());
            preparedStatement.setDouble(4, contract.getSalary());
            preparedStatement.setBoolean(5, contract.isActive());
            preparedStatement.setLong(6, contract.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Contract updated: {}", contract);

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

            LOGGER.info("Contract deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    private Contract mapContract(ResultSet resultSet) throws SQLException {
        Contract contract = new Contract();
        contract.setId(resultSet.getLong("id"));
        contract.setStartDate(resultSet.getDate("start_date"));
        contract.setEndDate(resultSet.getDate("end_date"));
        contract.setType(resultSet.getString("type"));
        contract.setSalary(resultSet.getDouble("salary"));
        contract.setActive(resultSet.getBoolean("active"));
        return contract;
    }
}
