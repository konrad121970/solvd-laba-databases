package com.solvd.laba.persistence.people;

import com.solvd.laba.domain.people.Employee;

import java.util.List;

public interface IEmployeeDAO extends IPersonDAO<Employee> {
    void create(Employee employee, Long workshopId);

    List<Employee> getAll();

    void update(Employee employee);

    void delete(Long id);
}
