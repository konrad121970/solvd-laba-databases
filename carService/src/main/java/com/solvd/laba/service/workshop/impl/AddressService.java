package com.solvd.laba.service.workshop.impl;

import com.solvd.laba.domain.workshop.Address;
import com.solvd.laba.persistence.workshop.IAddressDAO;
import com.solvd.laba.persistence.workshop.impl.AddressDAO;
import com.solvd.laba.service.workshop.IAddressService;

import java.util.List;

public class AddressService implements IAddressService {

    private final IAddressDAO addressDAO;

    public AddressService() {
        this.addressDAO = new AddressDAO();
    }

    @Override
    public void createAddress(Address address) {
        addressDAO.create(address);
    }

    @Override
    public Address getAddressById(Long id) {
        return addressDAO.getById(id);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressDAO.getAll();
    }
}
