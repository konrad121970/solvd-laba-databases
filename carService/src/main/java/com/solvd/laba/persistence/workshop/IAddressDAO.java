package com.solvd.laba.persistence.workshop;

import com.solvd.laba.domain.workshop.Address;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;

public interface IAddressDAO extends CommonDAO<Address> {
    void create(Address address);

    List<Address> getAll();

    void update(Address address);

    void delete(Long id);
}
