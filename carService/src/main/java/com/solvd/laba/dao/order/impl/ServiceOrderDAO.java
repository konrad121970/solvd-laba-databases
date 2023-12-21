package com.solvd.laba.dao.order.impl;

import com.solvd.laba.dao.order.IServiceOrderDAO;
import com.solvd.laba.domain.order.ServiceOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class ServiceOrderDAO implements IServiceOrderDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void create(ServiceOrder object) {

    }

    @Override
    public ServiceOrder getById(Long id) {
        return null;
    }

    @Override
    public List<ServiceOrder> getAll() {
        return null;
    }
}
