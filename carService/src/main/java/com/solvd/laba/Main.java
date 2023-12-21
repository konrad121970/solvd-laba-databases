package com.solvd.laba;

import com.solvd.laba.domain.workshop.Address;
import com.solvd.laba.service.workshop.IAddressService;
import com.solvd.laba.service.workshop.impl.AddressServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {

        IAddressService addressService = new AddressServiceImpl();

        Address address = new Address();
        address.setCity("Hajnowka");
        address.setStreet("Main");
        address.setBuildingNumber("123");
        address.setPostalCode("17-200");

        addressService.createAddress(address);

        List<Address> addresses;
        addresses = addressService.getAllAddresses();
        addresses.forEach(address1 -> LOGGER.info(address1.toString()));

    }
}