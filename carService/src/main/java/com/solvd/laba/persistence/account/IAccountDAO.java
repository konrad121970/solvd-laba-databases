package com.solvd.laba.persistence.account;

import com.solvd.laba.domain.account.Account;
import com.solvd.laba.domain.account.Role;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;
import java.util.Set;

public interface IAccountDAO extends CommonDAO<Account> {
    void create(Account account);

    List<Account> getAll();

    void update(Account account);

    void delete(Long id);

    void addRoleToAccount(Account account, Role role);

    void deleteRoleFromAccount(Account account, Role role);

    Set<Role> getRolesByAccount(Account account);

    Account getAccountByEmployee(Long employeeId);
}
