package com.solvd.laba.dao.workshop.impl;

import com.solvd.laba.dao.ConnectionPool;
import com.solvd.laba.dao.workshop.IAddressDAO;
import com.solvd.laba.domain.workshop.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO implements IAddressDAO {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO adresses (city, street, building_number, postal_code) VALUES (?, ?, ?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM adresses WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM adresses";

    @Override
    public void create(Address address) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {

            preparedStatement.setString(1, address.getCity());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setString(3, address.getBuildingNumber());
            preparedStatement.setString(4, address.getPostalCode());

            preparedStatement.executeUpdate();

            System.out.println("Adress created: " + address);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Address getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Address address = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {

            preparedStatement.setLong(1, id);

            ResultSet result =  preparedStatement.executeQuery();

            if(result.next()){
                address = new Address();
                address.setId(result.getLong("id"));
                address.setCity(result.getString("city"));
                address.setStreet(result.getString("street"));
                address.setBuildingNumber(result.getString("building_number"));
                address.setPostalCode(result.getString("postal_code"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return address;
    }

    @Override
    public List<Address> getAll() {
        List<Address> addresses = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Address address = new Address();
                address.setId(result.getLong("id"));
                address.setCity(result.getString("city"));
                address.setStreet(result.getString("street"));
                address.setBuildingNumber(result.getString("building_number"));
                address.setPostalCode(result.getString("postal_code"));

                addresses.add(address);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return addresses;
    }
}
