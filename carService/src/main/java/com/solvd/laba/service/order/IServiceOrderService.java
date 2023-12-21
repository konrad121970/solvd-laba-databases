package com.solvd.laba.service.order;

import com.solvd.laba.domain.order.ServiceOrder;

import java.util.List;

public interface IServiceOrderService {
    void createServiceOrder(ServiceOrder serviceOrder);

    ServiceOrder getServiceOrderById(Long id);

    List<ServiceOrder> getAllServiceOrders();

    void updateServiceOrder(ServiceOrder serviceOrder);

    void deleteServiceOrder(Long id);
}
