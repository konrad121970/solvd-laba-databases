package com.solvd.laba.persistence.account.impl;

import com.solvd.laba.domain.account.Account;
import com.solvd.laba.domain.account.Role;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.account.IRoleDAO;
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

public class RoleDAO implements IRoleDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO roles (name) VALUES (?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM roles WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM roles";
    private static final String UPDATE_ROLE_QUERY = "UPDATE roles SET name = ? WHERE id = ?";
    private static final String DELETE_ROLE_QUERY = "DELETE FROM roles WHERE id = ?";
    private static final String GET_ACCOUNTS_BY_ROLE_QUERY = "SELECT a.* FROM accounts a " +
            "JOIN roles_has_accounts ra ON a.id = ra.accounts_id " +
            "WHERE ra.roles_id = ?";

    public static Set<Role> mapRoles(ResultSet resultSet, Set<Role> roles) throws SQLException {

        if (roles == null) {
            roles = new HashSet<>();
        }

        Long id = resultSet.getLong("role_id");

        if (id != 0) {
            Role role = new Role();
            role.setId(id);
            role.setName(resultSet.getString("role_name"));
            roles.add(role);
        }

        return roles;
    }

    @Override
    public void create(Role role) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setString(1, role.getName());
            preparedStatement.executeUpdate();
            LOGGER.info("Role created: {}", role);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    // TODO: getByid
    @Override
    public Role getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Role role = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                /*mapRoles(result, )*/
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return role;
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
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

    @Override
    public void update(Role role) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROLE_QUERY)) {
            preparedStatement.setString(1, role.getName());
            preparedStatement.setLong(2, role.getId());
            preparedStatement.executeUpdate();
            LOGGER.info("Role updated: {}", role);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROLE_QUERY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            LOGGER.info("Role deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Account> getAccountsByRole(Role role) {
        List<Account> accounts = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNTS_BY_ROLE_QUERY)) {
            preparedStatement.setLong(1, role.getId());
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                accounts.add(AccountDAO.mapAccount(result));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return accounts;
    }

}