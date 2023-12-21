package com.solvd.laba.persistence.people.impl;

import com.solvd.laba.persistence.people.IEmployeeDAO;
import com.solvd.laba.domain.people.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class EmployeeDAO extends PersonDAO<Employee> implements IEmployeeDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void create(Employee object) {

    }

    @Override
    public Employee getById(Long id) {
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return null;
    }
}
