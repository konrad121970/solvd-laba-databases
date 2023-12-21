package com.solvd.laba.persistence.contract;

import com.solvd.laba.domain.contract.BonusPayment;
import com.solvd.laba.domain.contract.MonthlyPayment;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;

public interface IMonthlyPaymentDAO extends CommonDAO<MonthlyPayment> {
    void update(MonthlyPayment monthlyPayment);

    void delete(Long id);

    List<BonusPayment> getBonusPaymentsByMonthlyPaymentId(Long monthlyPaymentId);
}
