package com.solvd.laba.service.contract;

import com.solvd.laba.domain.contract.BonusPayment;

public interface IBonusPaymentService {
    void createBonusPayment(BonusPayment bonusPayment, Long monthlyPaymentId);

    BonusPayment getBonusPaymentById(Long id);


    void updateBonusPayment(BonusPayment bonusPayment);

    void deleteBonusPayment(Long id);

}
