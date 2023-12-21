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

    public static void main(String[] args) {
        IMonthlyPaymentsService monthlyPaymentService = new MonthlyPaymentService();

        MonthlyPayment monthlyPayment = new MonthlyPayment();
        monthlyPayment.setAmount(1000.0);
        monthlyPayment.setPaymentDate(new Date(2023, 11, 11));

        BonusPayment bonusPayment = new BonusPayment();
        bonusPayment.setAmount(200.0);
        bonusPayment.setDescription("Bonus payment for good performance");

        monthlyPaymentService.createMonthlyPayment(monthlyPayment, 1L);
        monthlyPaymentService.addBonusPayment(monthlyPayment, bonusPayment);

        MonthlyPayment retrievedMonthlyPayment = monthlyPaymentService.getMonthlyPaymentById(monthlyPayment.getId());
        List<BonusPayment> retrievedMonthlyPaymentBonusList = monthlyPaymentService.getBonusPaymentsByMonthlyPaymentId(monthlyPayment.getId());

        LOGGER.info("MonthlyPayment ID: {}", retrievedMonthlyPayment.getId());
        LOGGER.info("Amount: {}", retrievedMonthlyPayment.getAmount());
        LOGGER.info("BonusPayments:");
        for (BonusPayment bp : retrievedMonthlyPaymentBonusList) {
            LOGGER.info("  - BonusPayment ID: {}", bp.getId());
            LOGGER.info("    Amount: {}", bp.getAmount());
            LOGGER.info("    Description: {}", bp.getDescription());
        }
    }
}