package com.solvd.laba.persistence.people;

import com.solvd.laba.domain.people.Employee;
import org.apache.ibatis.annotations.Param;

public interface IEmployeeDAO extends IPersonDAO<Employee> {
    void create(@Param("employee") Employee employee, @Param("workshopId") Long workshopId);

    void update(Employee employee);

    void delete(Long id);
}
