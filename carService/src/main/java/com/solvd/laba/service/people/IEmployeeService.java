package com.solvd.laba.service.people;

import com.solvd.laba.domain.contract.Contract;
import com.solvd.laba.domain.people.Employee;

public interface IEmployeeService {
    void createEmployee(Employee employee, Long workshopId);

    Employee getEmployeeById(Long id);

    void updateEmployee(Employee employee);

    void deleteEmployee(Long id);

    /*    public List<Employee> getAllEmployeesByWorkshopId(Long workshopId) {
                return employeeDAO.getAllEmployeesByWorkshopId(workshopId);
            }

            public List<Contract> getContractsByEmployee(Employee employee) {
                return contractDAO.getContractsByEmployeeId(employee.getId());*/
    //}
    void addContractToEmployee(Employee employee, Contract contract);
}
