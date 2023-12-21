package com.solvd.laba.dao.account;

import com.solvd.laba.dao.CommonDAO;
import com.solvd.laba.domain.account.Account;
import com.solvd.laba.domain.account.Role;

import java.util.List;

public interface IAccountDAO extends CommonDAO<Account> {
    public void update(Account account);
    public void delete(Long id);
    public void addRoleToAccount(Account account, Role role);
    public void deleteRoleFromAccount(Account account, Role role);
    public List<Role> getRolesByAccount(Account account);
}
