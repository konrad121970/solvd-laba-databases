package com.solvd.laba.persistence.contract;

import com.solvd.laba.domain.contract.MonthlyPayment;
import com.solvd.laba.persistence.CommonDAO;

public interface IMonthlyPaymentDAO extends CommonDAO<MonthlyPayment> {
    void create(MonthlyPayment monthlyPayment, Long employeesId);

    void update(MonthlyPayment monthlyPayment);

    void delete(Long id);

    /*    List<BonusPayment> getBonusPaymentsByMonthlyPaymentId(Long monthlyPaymentId);*/
/*
    void addBonusPayment(MonthlyPayment monthlyPayment, BonusPayment bonusPayment);*/
}
