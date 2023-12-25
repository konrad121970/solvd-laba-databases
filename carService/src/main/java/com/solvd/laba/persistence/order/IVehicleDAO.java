package com.solvd.laba.persistence.order;

import com.solvd.laba.domain.order.ServiceOrder;
import com.solvd.laba.domain.order.Vehicle;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;

public interface IVehicleDAO extends CommonDAO<Vehicle> {
    void create(Vehicle vehicle);

    List<Vehicle> getAll();

    void update(Vehicle vehicle);

    void delete(Long id);

    List<ServiceOrder> getServiceOrdersByVehicle(Long vehicleId);
}
