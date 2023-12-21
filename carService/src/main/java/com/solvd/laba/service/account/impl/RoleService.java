package com.solvd.laba.service.account.impl;

import com.solvd.laba.domain.account.Role;
import com.solvd.laba.persistence.account.IRoleDAO;
import com.solvd.laba.service.account.IRoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class RoleService implements IRoleService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final IRoleDAO roleDAO;

    public RoleService(IRoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public void createRole(Role role) {
        roleDAO.create(role);
        LOGGER.info("Role created: {}", role);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleDAO.getById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAll();
    }

    @Override
    public void updateRole(Role role) {
        roleDAO.update(role);
        LOGGER.info("Role updated: {}", role);
    }

    @Override
    public void deleteRole(Long id) {
        roleDAO.delete(id);
        LOGGER.info("Role deleted with id: {}", id);
    }
}
