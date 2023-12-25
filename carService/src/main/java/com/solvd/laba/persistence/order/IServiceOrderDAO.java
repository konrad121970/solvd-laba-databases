package com.solvd.laba.persistence.order;

import com.solvd.laba.domain.order.ServiceOrder;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;

public interface IServiceOrderDAO extends CommonDAO<ServiceOrder> {
    void create(ServiceOrder serviceOrder);

    List<ServiceOrder> getAll();

    void update(ServiceOrder serviceOrder);

    void delete(Long id);
}
