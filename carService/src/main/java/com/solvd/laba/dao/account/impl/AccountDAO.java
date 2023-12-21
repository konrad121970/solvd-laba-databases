package com.solvd.laba.dao.account.impl;

import com.solvd.laba.dao.account.IAccountDAO;
import com.solvd.laba.domain.account.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class AccountDAO implements IAccountDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void create(Account object) {

    }

    @Override
    public Account getById(Long id) {
        return null;
    }

    @Override
    public List<Account> getAll() {
        return null;
    }
}
