package com.solvd.laba.service.people.impl;

import com.solvd.laba.domain.contract.Contract;
import com.solvd.laba.domain.people.Employee;
import com.solvd.laba.persistence.people.IEmployeeDAO;
import com.solvd.laba.persistence.people.impl.EmployeeDAO;
import com.solvd.laba.service.contract.IContractService;
import com.solvd.laba.service.contract.IMonthlyPaymentsService;
import com.solvd.laba.service.contract.impl.ContractService;
import com.solvd.laba.service.contract.impl.MonthlyPaymentService;
import com.solvd.laba.service.order.IServiceOrderService;
import com.solvd.laba.service.order.impl.ServiceOrderService;
import com.solvd.laba.service.people.IEmployeeService;

public class EmployeeService implements IEmployeeService {

    private final IEmployeeDAO employeeDAO;
    private final IContractService contractService;
    private final IMonthlyPaymentsService monthlyPaymentsService;
    private final IServiceOrderService serviceOrderService;

    public EmployeeService() {
        employeeDAO = new EmployeeDAO();
        contractService = new ContractService();
        monthlyPaymentsService = new MonthlyPaymentService();
        serviceOrderService = new ServiceOrderService();
    }

    @Override
    public void createEmployee(Employee employee, Long workshopId) {
        employeeDAO.create(employee, workshopId);

        if (!employee.getContracts().isEmpty()) {
            employee.getContracts().forEach(contract -> {
                contractService.createContract(contract, employee.getId());
            });
        }
        if (!employee.getMonthlyPayments().isEmpty()) {
            employee.getMonthlyPayments().forEach(monthlyPayment -> {
                monthlyPaymentsService.createMonthlyPayment(monthlyPayment, employee.getId());
            });
        }
        if (!employee.getServiceOrders().isEmpty()) {
            employee.getServiceOrders().forEach(serviceOrder -> {
                serviceOrderService.createServiceOrder(serviceOrder);
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
