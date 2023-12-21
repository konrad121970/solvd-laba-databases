package com.solvd.laba.persistence.order;

import com.solvd.laba.domain.order.ServiceOrder;
import com.solvd.laba.persistence.CommonDAO;

public interface IServiceOrderDAO extends CommonDAO<ServiceOrder> {
    void create(ServiceOrder serviceOrder);

    void update(ServiceOrder serviceOrder);

    void delete(Long id);
}
