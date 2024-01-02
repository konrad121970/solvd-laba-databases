package com.solvd.laba.service.workshop.impl;

import com.solvd.laba.domain.workshop.Address;
import com.solvd.laba.persistence.workshop.IAddressDAO;
import com.solvd.laba.persistence.workshop.impl.mybatis.AddressMyBatisImpl;
import com.solvd.laba.service.workshop.IAddressService;

public class AddressService implements IAddressService {

    private final IAddressDAO addressDAO;

    public AddressService() {
        // addressDAO = new AddressDAO();

        addressDAO = new AddressMyBatisImpl();
    }

    @Override
    public void createAddress(Address address) {
        addressDAO.create(address);
    }

    @Override
    public Address getAddressById(Long id) {
        return addressDAO.getById(id);
    }

}
