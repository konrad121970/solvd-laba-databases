package com.solvd.laba.service.account.impl;


import com.solvd.laba.domain.account.Account;
import com.solvd.laba.domain.account.Role;
import com.solvd.laba.persistence.account.IAccountDAO;
import com.solvd.laba.persistence.account.impl.AccountDAO;
import com.solvd.laba.service.account.IAccountService;
import com.solvd.laba.service.account.IRoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.Set;

public class AccountService implements IAccountService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final IAccountDAO accountDAO;
    private final IRoleService roleService;

    public AccountService() {
        this.accountDAO = new AccountDAO();
        this.roleService = new RoleService();
    }

    @Override
    public void createAccount(Account account) { // (Account account, Long employeeId)
        accountDAO.create(account);
        LOGGER.info("Account created: {}", account);
    }

    @Override
    public Account getAccountById(Long id) {
        return accountDAO.getById(id);
    }

    @Override
    public Set<Role> getRolesByAccount(Account account) {
        return accountDAO.getRolesByAccount(account);
    }

    @Override
    public void addRoleToAccount(Account account, Role role) {
        roleService.createRole(role); // Create role if not exists
        accountDAO.addRoleToAccount(account, role);
        LOGGER.info("Role {} added to Account {}", role.getName(), account.getLogin());
    }

    @Override
    public void deleteRoleFromAccount(Account account, Role role) {
        accountDAO.deleteRoleFromAccount(account, role);
        LOGGER.info("Role {} deleted from Account {}", role.getName(), account.getLogin());
    }

    @Override
    public void updateAccount(Account account) {
        accountDAO.update(account);
        LOGGER.info("Account updated: {}", account);
    }

    @Override
    public void deleteAccount(Long id) {
        accountDAO.delete(id);
        LOGGER.info("Account deleted with id: {}", id);
    }
}
