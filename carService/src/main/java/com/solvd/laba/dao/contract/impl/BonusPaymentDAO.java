package com.solvd.laba.dao.contract.impl;

import com.solvd.laba.dao.contract.IBonusPaymentDAO;
import com.solvd.laba.domain.contract.BonusPayment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class BonusPaymentDAO implements IBonusPaymentDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void create(BonusPayment object) {

    }

    @Override
    public BonusPayment getById(Long id) {
        return null;
    }

    @Override
    public List<BonusPayment> getAll() {
        return null;
    }
}
