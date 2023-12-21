package com.solvd.laba.service.order.impl;

import com.solvd.laba.domain.order.ServiceOrder;
import com.solvd.laba.persistence.order.IServiceOrderDAO;
import com.solvd.laba.persistence.order.impl.ServiceOrderDAO;
import com.solvd.laba.service.order.IServiceOrderService;

import java.util.List;

public class ServiceOrderService implements IServiceOrderService {

    private final IServiceOrderDAO serviceOrderDAO;

    public ServiceOrderService() {
        this.serviceOrderDAO = new ServiceOrderDAO();
    }

    @Override
    public void createServiceOrder(ServiceOrder serviceOrder) {
        serviceOrderDAO.create(serviceOrder);
    }

    @Override
    public ServiceOrder getServiceOrderById(Long id) {
        return serviceOrderDAO.getById(id);
    }

    @Override
    public List<ServiceOrder> getAllServiceOrders() {
        return serviceOrderDAO.getAll();
    }

    @Override
    public void updateServiceOrder(ServiceOrder serviceOrder) {
        serviceOrderDAO.update(serviceOrder);
    }

    @Override
    public void deleteServiceOrder(Long id) {
        serviceOrderDAO.delete(id);
    }
}
