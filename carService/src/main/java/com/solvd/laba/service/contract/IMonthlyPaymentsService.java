package com.solvd.laba.service.contract;

import com.solvd.laba.domain.contract.BonusPayment;
import com.solvd.laba.domain.contract.MonthlyPayment;

import java.util.List;

public interface IMonthlyPaymentsService {
    void createMonthlyPayment(MonthlyPayment monthlyPayment, Long employeeId);

    MonthlyPayment getMonthlyPaymentById(Long id);


    void updateMonthlyPayment(MonthlyPayment monthlyPayment);

    void deleteMonthlyPayment(Long id);
    
    List<MonthlyPayment> getAllMonthlyPaymentsByEmployeeId(Long employeeId);

    void addBonusPaymentToMonthlyPayment(MonthlyPayment monthlyPayment, BonusPayment bonusPayment);
}
