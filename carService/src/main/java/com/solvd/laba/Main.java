package com.solvd.laba;

import com.solvd.laba.domain.contract.BonusPayment;
import com.solvd.laba.domain.contract.MonthlyPayment;
import com.solvd.laba.service.contract.IMonthlyPaymentsService;
import com.solvd.laba.service.contract.impl.MonthlyPaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Date;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    public static void main(String[] args) {

        IMonthlyPaymentsService monthlyPaymentService = new MonthlyPaymentService();


        MonthlyPayment monthlyPayment1 = new MonthlyPayment();
        monthlyPayment1.setAmount(1000.0);
        monthlyPayment1.setPaymentDate(new Date(2023 - 1900, 0, 9));
        monthlyPaymentService.createMonthlyPayment(monthlyPayment1, 1L);

        BonusPayment bonusPayment2 = new BonusPayment();
        bonusPayment2.setAmount(200.0);
        bonusPayment2.setDescription("Bonus payment for good performance");
        monthlyPaymentService.addBonusPaymentToMonthlyPayment(monthlyPayment1, bonusPayment2);


        Long employeeId = 1L;
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
                });
    }

}