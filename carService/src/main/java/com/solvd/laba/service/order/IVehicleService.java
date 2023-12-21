package com.solvd.laba.service.order;

import com.solvd.laba.domain.order.Vehicle;

import java.util.List;

public interface IVehicleService {
    void createVehicle(Vehicle vehicle);

    Vehicle getVehicleById(Long id);

    List<Vehicle> getAllVehicles();

    void updateVehicle(Vehicle vehicle);

    void deleteVehicle(Long id);
}
