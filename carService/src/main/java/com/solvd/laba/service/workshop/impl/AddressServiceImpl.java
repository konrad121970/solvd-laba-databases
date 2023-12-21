package com.solvd.laba.service.workshop.impl;

import com.solvd.laba.persistence.workshop.IAddressDAO;
import com.solvd.laba.persistence.workshop.impl.AddressDAO;
import com.solvd.laba.domain.workshop.Address;
import com.solvd.laba.service.workshop.IAddressService;

import java.util.List;

public class AddressServiceImpl implements IAddressService {

    private final IAddressDAO adressDAO;

    public AddressServiceImpl() {
        this.adressDAO = new AddressDAO();
    }

    @Override
    public void createAddress(Address address) {
        adressDAO.create(address);
    }

    @Override
    public Address getAdressById(Long id) {
        return null;
    }

    @Override
    public List<Address> getAllAdresses() {
        return adressDAO.getAll();
    }
}
