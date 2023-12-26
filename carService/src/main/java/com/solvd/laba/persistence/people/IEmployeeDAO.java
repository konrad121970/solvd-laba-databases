package com.solvd.laba.persistence.people;

import com.solvd.laba.domain.people.Employee;

public interface IEmployeeDAO extends IPersonDAO<Employee> {
    void create(Employee employee, Long workshopId);

    void update(Employee employee);

    void delete(Long id);
}
