package com.solvd.laba.service.people.impl;

import com.solvd.laba.domain.contract.Contract;
import com.solvd.laba.domain.people.Employee;
import com.solvd.laba.persistence.people.IEmployeeDAO;
import com.solvd.laba.persistence.people.impl.EmployeeDAO;
import com.solvd.laba.service.contract.IContractService;
import com.solvd.laba.service.contract.impl.ContractService;
import com.solvd.laba.service.people.IEmployeeService;

public class EmployeeService implements IEmployeeService {

    private final IEmployeeDAO employeeDAO;
    private final IContractService contractService;

    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
        this.contractService = new ContractService();
    }

    @Override
    public void createEmployee(Employee employee, Long workshopId) {
        employeeDAO.create(employee, workshopId);

        if (!employee.getContracts().isEmpty()) {
            employee.getContracts().forEach(contract -> {
                contractService.createContract(contract, employee.getId());
            });
        }
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeDAO.getById(id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDAO.update(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeDAO.delete(id);
    }

    /*    public List<Employee> getAllEmployeesByWorkshopId(Long workshopId) {
            return employeeDAO.getAllEmployeesByWorkshopId(workshopId);
        }

        public List<Contract> getContractsByEmployee(Employee employee) {
            return contractDAO.getContractsByEmployeeId(employee.getId());*/
    //}
    @Override
    public void addContractToEmployee(Employee employee, Contract contract) {

        contractService.createContract(contract, employee.getId());

    }
}
