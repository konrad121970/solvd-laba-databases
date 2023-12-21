package com.solvd.laba.dao.people.impl;

import com.solvd.laba.dao.people.ICustomerDAO;
import com.solvd.laba.domain.people.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class CustomerDAO extends PersonDAO<Customer> implements ICustomerDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void create(Customer object) {

    }

    @Override
    public Customer getById(Long id) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }
}
