package com.solvd.laba.persistence.order;

import com.solvd.laba.persistence.CommonDAO;
import com.solvd.laba.domain.order.ServiceOrder;

public interface IServiceOrderDAO extends CommonDAO<ServiceOrder> {
    void update(ServiceOrder serviceOrder);

    void delete(Long id);
}
