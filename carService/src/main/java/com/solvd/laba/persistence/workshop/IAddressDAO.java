package com.solvd.laba.persistence.workshop;

import com.solvd.laba.persistence.CommonDAO;
import com.solvd.laba.domain.workshop.Address;

public interface IAddressDAO extends CommonDAO<Address> {
    void update(Address address);

    void delete(Long id);
}
