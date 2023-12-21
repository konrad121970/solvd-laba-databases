package com.solvd.laba.service.workshop;

import com.solvd.laba.domain.workshop.Address;

import java.util.List;

public interface IAddressService {

    void createAddress(Address address);

    List<Address> getAllAddresses();

    Address getAddressById(Long id);

}