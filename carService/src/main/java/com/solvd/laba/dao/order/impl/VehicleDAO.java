package com.solvd.laba.dao.order.impl;

import com.solvd.laba.dao.order.IVehicleDAO;
import com.solvd.laba.domain.order.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class VehicleDAO implements IVehicleDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void create(Vehicle object) {

    }

    @Override
    public Vehicle getById(Long id) {
        return null;
    }

    @Override
    public List<Vehicle> getAll() {
        return null;
    }
}
