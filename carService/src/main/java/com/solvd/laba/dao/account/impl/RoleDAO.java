package com.solvd.laba.dao.account.impl;

import com.solvd.laba.dao.account.IRoleDAO;
import com.solvd.laba.domain.account.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class RoleDAO implements IRoleDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void create(Role object) {

    }

    @Override
    public Role getById(Long id) {
        return null;
    }

    @Override
    public List<Role> getAll() {
        return null;
    }
}
