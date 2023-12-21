package com.solvd.laba.service.account;

import com.solvd.laba.domain.account.Role;

import java.util.List;

public interface IRoleService {
    void createRole(Role role);

    Role getRoleById(Long id);

    List<Role> getAllRoles();

    void updateRole(Role role);

    void deleteRole(Long id);
}
