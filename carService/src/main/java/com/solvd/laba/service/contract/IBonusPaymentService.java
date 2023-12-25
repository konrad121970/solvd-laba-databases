package com.solvd.laba.service.contract;

import com.solvd.laba.domain.contract.BonusPayment;

import java.util.List;

public interface IBonusPaymentService {
    void createBonusPayment(BonusPayment bonusPayment, Long monthlyPaymentId);

    BonusPayment getBonusPaymentById(Long id);


    void updateBonusPayment(BonusPayment bonusPayment);

    void deleteBonusPayment(Long id);

    List<BonusPayment> getBonusPaymentsAssignedToMonthlyPayment(Long id);
}
