package com.solvd.laba.dao.workshop;

import com.solvd.laba.dao.CommonDAO;
import com.solvd.laba.domain.workshop.Address;

public interface IAddressDAO extends CommonDAO<Address> {
    void update(Address address);

    void delete(Long id);
}
