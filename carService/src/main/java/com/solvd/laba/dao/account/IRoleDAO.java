package com.solvd.laba.dao.account;

import com.solvd.laba.dao.CommonDAO;
import com.solvd.laba.domain.account.Account;
import com.solvd.laba.domain.account.Role;

import java.util.List;

public interface IRoleDAO extends CommonDAO<Role> {
    void update(Role role);
    void delete(Long id);
    List<Account> getAccountsByRole(Role role);
}
