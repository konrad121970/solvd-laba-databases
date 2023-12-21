package com.solvd.laba.dao.contract.impl;

import com.solvd.laba.dao.contract.IContractDAO;
import com.solvd.laba.dao.people.ICustomerDAO;
import com.solvd.laba.domain.contract.Contract;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class ContractDAO implements IContractDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void create(Contract object) {

    }

    @Override
    public Contract getById(Long id) {
        return null;
    }

    @Override
    public List<Contract> getAll() {
        return null;
    }
}
