package com.solvd.laba.service.contract.impl;

import com.solvd.laba.domain.contract.BonusPayment;
import com.solvd.laba.domain.contract.MonthlyPayment;
import com.solvd.laba.persistence.contract.IMonthlyPaymentDAO;
import com.solvd.laba.persistence.contract.impl.MonthlyPaymentDAO;
import com.solvd.laba.service.contract.IMonthlyPaymentsService;

import java.util.List;

public class MonthlyPaymentService implements IMonthlyPaymentsService {

    private final IMonthlyPaymentDAO monthlyPaymentDAO;

    public MonthlyPaymentService() {
        this.monthlyPaymentDAO = new MonthlyPaymentDAO();
    }

    @Override
    public void createMonthlyPayment(MonthlyPayment monthlyPayment, Long employeeId) {
        monthlyPaymentDAO.create(monthlyPayment, employeeId);
    }

    @Override

    public MonthlyPayment getMonthlyPaymentById(Long id) {
        return monthlyPaymentDAO.getById(id);
    }

    @Override

    public List<MonthlyPayment> getAllMonthlyPayments() {
        return monthlyPaymentDAO.getAll();
    }

    @Override

    public void updateMonthlyPayment(MonthlyPayment monthlyPayment) {
        monthlyPaymentDAO.update(monthlyPayment);
    }

    @Override

    public void deleteMonthlyPayment(Long id) {
        monthlyPaymentDAO.delete(id);
    }

    @Override
    public List<BonusPayment> getBonusPaymentsByMonthlyPaymentId(Long monthlyPaymentId) {
        return monthlyPaymentDAO.getBonusPaymentsByMonthlyPaymentId(monthlyPaymentId);
    }

    @Override
    public void addBonusPayment(MonthlyPayment monthlyPayment, BonusPayment bonusPayment) {
        monthlyPaymentDAO.addBonusPayment(monthlyPayment, bonusPayment);
    }
}
