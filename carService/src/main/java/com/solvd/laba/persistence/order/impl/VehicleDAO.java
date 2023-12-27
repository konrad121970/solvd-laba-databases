package com.solvd.laba.persistence.order.impl;

import com.solvd.laba.domain.order.ServiceOrder;
import com.solvd.laba.domain.order.Vehicle;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.order.IVehicleDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO implements IVehicleDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO vehicles (vin, make, model, license_plate) VALUES (?, ?, ?, ?)";
    private static final String GET_BY_ID_QUERY =
            "SELECT " +
                    "   v.id AS vehicle_id, v.vin AS vehicle_vin, v.make AS vehicle_make, v.model AS vehicle_model, v.license_plate AS vehicle_license_plate, " +
                    "   so.id AS service_order_id, so.description AS service_order_description, so.completed AS service_order_completed, so.date as service_order_date " +
                    "FROM vehicles v " +
                    "LEFT JOIN service_orders so ON v.id = so.vehicles_id " +
                    "WHERE v.id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM vehicles";
    private static final String GET_SERVICE_ORDERS_BY_VEHICLE_QUERY = "SELECT so.* FROM service_orders so JOIN vehicles v ON so.vehicle_id = v.id WHERE v.id = ?";
    private static final String DELETE_VEHICLE_QUERY = "DELETE FROM vehicles WHERE id = ?";
    private static final String UPDATE_VEHICLE_QUERY = "UPDATE vehicles SET vin = ?, make = ?, model = ?, license_plate = ? WHERE id = ?";

    public static List<Vehicle> mapRow(ResultSet resultSet, List<Vehicle> vehicles) throws SQLException {
        List<ServiceOrder> serviceOrders = new ArrayList<>();
        if (vehicles == null) {
            vehicles = new ArrayList<>();
        }

        Long id = resultSet.getLong("vehicle_id");

        if (id != 0) {
            Vehicle vehicle = findById(id, vehicles);
            vehicle.setVin(resultSet.getString("vehicle_vin"));
            vehicle.setMake(resultSet.getString("vehicle_make"));
            vehicle.setModel(resultSet.getString("vehicle_model"));
            vehicle.setLicensePlate(resultSet.getString("vehicle_license_plate"));

            serviceOrders = ServiceOrderDAO.mapRow(resultSet, serviceOrders);
            vehicle.setServiceOrders(serviceOrders);

            vehicles.add(vehicle);
        }

        return vehicles;
    }

    private static Vehicle findById(Long id, List<Vehicle> vehicles) {
        return vehicles.stream()
                .filter(vehicle -> vehicle.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Vehicle newVehicle = new Vehicle();
                    newVehicle.setId(id);
                    vehicles.add(newVehicle);
                    return newVehicle;
                });
    }

    @Override
    public void create(Vehicle vehicle) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setString(1, vehicle.getVin());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setString(4, vehicle.getLicensePlate());

            preparedStatement.executeUpdate();

            LOGGER.info("Vehicle created: {}", vehicle);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Vehicle getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Vehicle> vehicles = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {

            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {

                mapRow(result, vehicles);

            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return vehicles.get(0);
    }

    @Override
    public List<Vehicle> getAll() {
        List<Vehicle> vehicles = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(result.getLong("id"));
                vehicle.setVin(result.getString("vin"));
                vehicle.setMake(result.getString("make"));
                vehicle.setModel(result.getString("model"));
                vehicle.setLicensePlate(result.getString("license_plate"));
                vehicle.setServiceOrders(getServiceOrdersByVehicle(vehicle.getId()));

                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return vehicles;
    }

    @Override
    public void update(Vehicle vehicle) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VEHICLE_QUERY)) {
            preparedStatement.setString(1, vehicle.getVin());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setString(4, vehicle.getLicensePlate());
            preparedStatement.setLong(5, vehicle.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Vehicle updated: {}", vehicle);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VEHICLE_QUERY)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            LOGGER.info("Vehicle deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<ServiceOrder> getServiceOrdersByVehicle(Long vehicleId) {
        List<ServiceOrder> serviceOrders = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_SERVICE_ORDERS_BY_VEHICLE_QUERY)) {
            preparedStatement.setLong(1, vehicleId);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                ServiceOrder serviceOrder = new ServiceOrder();
                serviceOrder.setId(result.getLong("id"));
                // Dodaj pozostałe pola ServiceOrder

                serviceOrders.add(serviceOrder);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return serviceOrders;
    }
}
