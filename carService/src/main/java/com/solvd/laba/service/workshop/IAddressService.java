package com.solvd.laba.service.workshop;

import com.solvd.laba.domain.workshop.Address;

import java.util.List;

public interface IAddressService {

    void createAddress(Address address);

    Address getAdressById(Long id);

    List<Address> getAllAdresses();
}