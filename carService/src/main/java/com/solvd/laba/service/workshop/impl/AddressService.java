package com.solvd.laba.service.workshop.impl;

import com.solvd.laba.config.Config;
import com.solvd.laba.domain.workshop.Address;
import com.solvd.laba.persistence.workshop.IAddressDAO;
import com.solvd.laba.persistence.workshop.impl.AddressDAO;
import com.solvd.laba.persistence.workshop.impl.mybatis.AddressMyBatisImpl;
import com.solvd.laba.service.workshop.IAddressService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class AddressService implements IAddressService {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private final IAddressDAO addressDAO;

    public AddressService() {
        if (Config.IMPL.getValue().equals("jdbc")) {
            addressDAO = new AddressDAO();
        } else if (Config.IMPL.getValue().equals("myBatis")) {
            addressDAO = new AddressMyBatisImpl();
        } else {
            LOGGER.info("{}: Data source was not specified or is invalid. Defaulting to JDBC implementation", this.getClass().getSimpleName());
            addressDAO = new AddressDAO();
        }
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
