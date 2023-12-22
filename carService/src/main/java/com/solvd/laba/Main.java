package com.solvd.laba;

import com.solvd.laba.domain.contract.BonusPayment;
import com.solvd.laba.domain.contract.MonthlyPayment;
import com.solvd.laba.service.contract.IMonthlyPaymentsService;
import com.solvd.laba.service.contract.impl.MonthlyPaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Date;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    static IMonthlyPaymentsService monthlyPaymentService = new MonthlyPaymentService();

    public static void main(String[] args) {


        MonthlyPayment monthlyPayment = new MonthlyPayment();
        monthlyPayment.setAmount(1000.0);
        monthlyPayment.setPaymentDate(new Date(2023, 11, 11));
        monthlyPaymentService.createMonthlyPayment(monthlyPayment, 1L);

        BonusPayment bonusPayment = new BonusPayment();
        bonusPayment.setAmount(200.0);
        bonusPayment.setDescription("Bonus payment for good performance");
        monthlyPaymentService.addBonusPaymentToMonthlyPayment(monthlyPayment, bonusPayment);

        List<BonusPayment> retrievedMonthlyPaymentBonusList = monthlyPaymentService.getBonusPaymentsByMonthlyPayment(monthlyPayment);

        LOGGER.info("MonthlyPayment ID: {}", monthlyPayment.getId());
        LOGGER.info("Amount: {}", monthlyPayment.getAmount());
        LOGGER.info("BonusPayments:");

        retrievedMonthlyPaymentBonusList.forEach(bp -> {
            LOGGER.info("  - BonusPayment ID: {}", bp.getId());
            LOGGER.info("    Amount: {}", bp.getAmount());
            LOGGER.info("    Description: {}", bp.getDescription());
        });

    }
}