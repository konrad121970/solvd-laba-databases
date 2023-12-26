package com.solvd.laba;

import com.solvd.laba.domain.people.Employee;
import com.solvd.laba.service.contract.IContractService;
import com.solvd.laba.service.contract.IMonthlyPaymentsService;
import com.solvd.laba.service.contract.impl.ContractService;
import com.solvd.laba.service.contract.impl.MonthlyPaymentService;
import com.solvd.laba.service.people.IEmployeeService;
import com.solvd.laba.service.people.impl.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    public static void main(String[] args) {

        IMonthlyPaymentsService monthlyPaymentService = new MonthlyPaymentService();
        IContractService contractService = new ContractService();
        IEmployeeService employeeService = new EmployeeService();

/*
        MonthlyPayment monthlyPayment1 = new MonthlyPayment();
        monthlyPayment1.setAmount(1000.0);
        monthlyPayment1.setPaymentDate(new Date(2023 - 1900, 0, 9));
        monthlyPaymentService.createMonthlyPayment(monthlyPayment1, 1L);

        BonusPayment bonusPayment2 = new BonusPayment();
        bonusPayment2.setAmount(200.0);
        bonusPayment2.setDescription("Bonus payment for good performance");
        monthlyPaymentService.addBonusPaymentToMonthlyPayment(monthlyPayment1, bonusPayment2);*/


        Employee employee = employeeService.getEmployeeById(1L);





/*        Long employeeId = 1L;
        System.out.println("Monthly Payments with Bonuses for Employee ID " + employeeId + ":");
        monthlyPaymentService.getAllMonthlyPaymentsByEmployeeId(employeeId)
                .forEach(monthlyPayment -> {
                    System.out.println("\nMonthly Payment ID: " + monthlyPayment.getId()
                            + ", Amount: " + monthlyPayment.getAmount()
                            + ", Payment Date: " + monthlyPayment.getPaymentDate());

                    if (!monthlyPayment.getBonusPaymentList().isEmpty()) {
                        monthlyPayment.getBonusPaymentList().forEach(bonusPayment ->
                                System.out.println("  - Bonus Payment ID: " + bonusPayment.getId()
                                        + ", Amount: " + bonusPayment.getAmount()
                                        + ", Description: " + bonusPayment.getDescription()));
                    }
                });*/

    }
}