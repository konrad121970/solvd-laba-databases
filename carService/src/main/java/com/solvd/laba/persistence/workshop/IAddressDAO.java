package com.solvd.laba.persistence.workshop;

import com.solvd.laba.domain.workshop.Address;
import com.solvd.laba.persistence.CommonDAO;

public interface IAddressDAO extends CommonDAO<Address> {
    void create(Address address);

    void update(Address address);

    void delete(Long id);
}
