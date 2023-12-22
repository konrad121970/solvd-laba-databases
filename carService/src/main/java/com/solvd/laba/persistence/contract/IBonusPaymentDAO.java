package com.solvd.laba.persistence.contract;

import com.solvd.laba.domain.contract.BonusPayment;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;

public interface IBonusPaymentDAO extends CommonDAO<BonusPayment> {
    void create(BonusPayment bonusPayment, Long monthlyPaymentId);

    void update(BonusPayment bonusPayment);

    void delete(Long id);

    List<BonusPayment> getBonusPaymentsByMonthlyPaymentId(Long monthlyPaymentId);
}
