package com.solvd.laba.service.account;

import com.solvd.laba.domain.account.Account;
import com.solvd.laba.domain.account.Role;

import java.util.Set;

public interface IAccountService {
    void createAccount(Account account);

    Account getAccountById(Long id);

    Set<Role> getRolesByAccount(Account account);

    void addRoleToAccount(Account account, Role role);

    void deleteRoleFromAccount(Account account, Role role);

    void updateAccount(Account account);

    void deleteAccount(Long id);
}
