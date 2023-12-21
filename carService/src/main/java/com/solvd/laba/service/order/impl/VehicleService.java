package com.solvd.laba.service.order.impl;

import com.solvd.laba.domain.order.Vehicle;
import com.solvd.laba.persistence.order.IVehicleDAO;
import com.solvd.laba.persistence.order.impl.VehicleDAO;
import com.solvd.laba.service.order.IVehicleService;

import java.util.List;

public class VehicleService implements IVehicleService {

    private final IVehicleDAO vehicleDAO;

    public VehicleService() {
        this.vehicleDAO = new VehicleDAO();
    }

    @Override
    public void createVehicle(Vehicle vehicle) {
        vehicleDAO.create(vehicle);
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleDAO.getById(id);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.getAll();
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        vehicleDAO.update(vehicle);
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleDAO.delete(id);
    }
}
