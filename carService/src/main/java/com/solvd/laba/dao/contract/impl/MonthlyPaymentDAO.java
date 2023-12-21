package com.solvd.laba.dao.contract.impl;

import com.solvd.laba.dao.contract.IMonthlyPaymentDAO;
import com.solvd.laba.domain.contract.MonthlyPayment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class MonthlyPaymentDAO implements IMonthlyPaymentDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void create(MonthlyPayment object) {

    }

    @Override
    public MonthlyPayment getById(Long id) {
        return null;
    }

    @Override
    public List<MonthlyPayment> getAll() {
        return null;
    }
}
