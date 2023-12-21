package com.solvd.laba.dao.workshop.impl;

import com.solvd.laba.config.Config;
import com.solvd.laba.dao.ConnectionPool;
import com.solvd.laba.dao.workshop.IAdressDAO;
import com.solvd.laba.domain.workshop.Adress;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AdressDAO implements IAdressDAO {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO adresses (city, street, building_number, postal_code) VALUES (?, ?, ?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM adresses WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM adresses";

    @Override
    public void create(Adress adress) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {

            preparedStatement.setString(1, adress.getCity());
            preparedStatement.setString(2, adress.getStreet());
            preparedStatement.setString(3, adress.getBuildingNumber());
            preparedStatement.setString(4, adress.getPostalCode());

            preparedStatement.executeUpdate();

            System.out.println("Adress created: " + adress);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Adress getById(Long id) {
        return null;
    }

    @Override
    public List<Adress> getAll() {
        return null;
    }
}
