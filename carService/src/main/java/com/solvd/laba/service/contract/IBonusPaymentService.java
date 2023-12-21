package com.solvd.laba.service.contract;

import com.solvd.laba.domain.contract.BonusPayment;

import java.util.List;

public interface IBonusPaymentService {
    void createBonusPayment(BonusPayment bonusPayment);

    BonusPayment getBonusPaymentById(Long id);

    List<BonusPayment> getAllBonusPayments();

    void updateBonusPayment(BonusPayment bonusPayment);

    void deleteBonusPayment(Long id);
}
