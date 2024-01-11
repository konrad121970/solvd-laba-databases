package com.solvd.laba.service.workshop.impl;

import com.solvd.laba.config.Config;
import com.solvd.laba.domain.workshop.Address;
import com.solvd.laba.persistence.RepositoryFactory;
import com.solvd.laba.persistence.workshop.IAddressDAO;
import com.solvd.laba.service.workshop.IAddressService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class AddressService implements IAddressService {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private final IAddressDAO addressDAO;

    public AddressService() {

        addressDAO = RepositoryFactory.createAddressRepository(Config.IMPL.getValue());
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
