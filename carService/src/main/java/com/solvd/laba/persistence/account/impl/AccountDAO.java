package com.solvd.laba.persistence.account.impl;

import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.account.IAccountDAO;
import com.solvd.laba.domain.account.Account;
import com.solvd.laba.domain.account.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccountDAO implements IAccountDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO accounts (login, password) VALUES (?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM accounts WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM accounts";
    private static final String ADD_ROLE_TO_ACCOUNT_QUERY = "INSERT INTO roles_has_accounts (roles_id, accounts_id) VALUES(?,?)";
    private static final String GET_ROLES_BY_ACCOUNT_QUERY = "SELECT r.* FROM roles r JOIN roles_has_accounts ra ON r.id = ra.roles_id WHERE ra.accounts_id = ?";
    private static final String DELETE_ROLE_FROM_ACCOUNT_QUERY = "DELETE FROM roles_has_accounts WHERE accounts_id = ? AND roles_id = ?";
    private static final String DELETE_ACCOUNT_QUERY = "DELETE FROM accounts WHERE id = ?";
    private static final String UPDATE_ACCOUNT_QUERY = "UPDATE accounts SET login = ?, password = ? WHERE id = ?";

    @Override
    public void create(Account account) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setString(1, account.getLogin());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();

            LOGGER.info("Account created: {}", account);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Account getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Account account = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {

            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                account = new Account();
                account.setId(result.getLong("id"));
                account.setLogin(result.getString("login"));
                account.setPassword(result.getString("password"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return account;
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Account account = new Account();
                account.setId(result.getLong("id"));
                account.setLogin(result.getString("login"));
                account.setPassword(result.getString("password"));

                accounts.add(account);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return accounts;
    }

    @Override
    public void update(Account account) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_QUERY)) {
            preparedStatement.setString(1, account.getLogin());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setLong(3, account.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Account updated: {}", account);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ACCOUNT_QUERY)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            LOGGER.info("Account deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void addRoleToAccount(Account account, Role role) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ROLE_TO_ACCOUNT_QUERY)) {
            preparedStatement.setLong(1, role.getId());
            preparedStatement.setLong(2, account.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Role {} added to Account {}", role.getName(), account.getLogin());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteRoleFromAccount(Account account, Role role) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROLE_FROM_ACCOUNT_QUERY)) {
            preparedStatement.setLong(1, account.getId());
            preparedStatement.setLong(2, role.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                LOGGER.info("Role {} deleted from Account {}", role.getName(), account.getLogin());
            } else {
                LOGGER.warn("Role {} not found for Account {}", role.getName(), account.getLogin());
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Set<Role> getRolesByAccount(Account account) {
        Set<Role> roles = new HashSet<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ROLES_BY_ACCOUNT_QUERY)) {
            preparedStatement.setLong(1, account.getId());

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Role role = new Role();
                role.setId(result.getLong("id"));
                role.setName(result.getString("name"));

                roles.add(role);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return roles;
    }

}
