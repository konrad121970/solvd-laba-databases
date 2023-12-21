package com.solvd.laba.dao.workshop.impl;

import com.solvd.laba.dao.ConnectionPool;
import com.solvd.laba.dao.workshop.IWorkshopDAO;
import com.solvd.laba.domain.workshop.Address;
import com.solvd.laba.domain.workshop.Workshop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkshopDAO implements IWorkshopDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String CREATE_QUERY =
            "INSERT INTO workshops (adresses_id, name, nip) VALUES (?, ?, ?)";

    private static final String GET_BY_ID_QUERY =
            "SELECT * FROM workshops WHERE id = ?";

    private static final String GET_ALL_QUERY =
            "SELECT * FROM workshops";

    private static final String UPDATE_WORKSHOP_QUERY =
            "UPDATE workshops SET adresses_id = ?, name = ?, nip = ? WHERE id = ?";

    private static final String DELETE_WORKSHOP_QUERY =
            "DELETE FROM workshops WHERE id = ?";

    @Override
    public void create(Workshop workshop) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {

            preparedStatement.setLong(1, workshop.getAddress().getId());
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
        Workshop workshop = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {

            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                workshop = mapResultSetToWorkshop(result);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return workshop;
    }

    @Override
    public List<Workshop> getAll() {
        List<Workshop> workshops = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Workshop workshop = mapResultSetToWorkshop(result);
                workshops.add(workshop);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return workshops;
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

    private Workshop mapResultSetToWorkshop(ResultSet result) throws SQLException {
        Workshop workshop = new Workshop();
        workshop.setId(result.getLong("id"));
        workshop.setName(result.getString("name"));
        workshop.setNip(result.getString("nip"));

        Address address = new Address();
        address.setId(result.getLong("adresses_id"));
        workshop.setAddress(address);

        return workshop;
    }
}
