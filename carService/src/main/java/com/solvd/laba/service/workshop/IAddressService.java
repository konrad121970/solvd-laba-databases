package com.solvd.laba.service.workshop;

import com.solvd.laba.domain.workshop.Address;

public interface IAddressService {

    void createAddress(Address address);


    Address getAddressById(Long id);

}