package com.solvd.laba.service.contract.impl;

import com.solvd.laba.domain.contract.BonusPayment;
import com.solvd.laba.domain.contract.MonthlyPayment;
import com.solvd.laba.persistence.contract.IMonthlyPaymentDAO;
import com.solvd.laba.persistence.contract.impl.MonthlyPaymentDAO;
import com.solvd.laba.service.contract.IBonusPaymentService;
import com.solvd.laba.service.contract.IMonthlyPaymentsService;

import java.util.List;

public class MonthlyPaymentService implements IMonthlyPaymentsService {

    private final IMonthlyPaymentDAO monthlyPaymentDAO;
    private final IBonusPaymentService bonusPaymentService;


    public MonthlyPaymentService() {
        this.monthlyPaymentDAO = new MonthlyPaymentDAO();
        bonusPaymentService = new BonusPaymentService();
    }

    @Override
    public void createMonthlyPayment(MonthlyPayment monthlyPayment, Long employeeId) {
        monthlyPaymentDAO.create(monthlyPayment, employeeId);

        if (!monthlyPayment.getBonusPaymentList().isEmpty()) {
            monthlyPayment.getBonusPaymentList()
                    .forEach(bonusPayment -> {
                        bonusPaymentService.createBonusPayment(bonusPayment, monthlyPayment.getId());
                    });
        }
    }

    @Override

    public MonthlyPayment getMonthlyPaymentById(Long id) {
        return monthlyPaymentDAO.getById(id);
    }


    @Override

    public void updateMonthlyPayment(MonthlyPayment monthlyPayment) {
        monthlyPaymentDAO.update(monthlyPayment);
    }

    @Override

    public void deleteMonthlyPayment(Long id) {
        monthlyPaymentDAO.delete(id);
    }

    public List<MonthlyPayment> getAllMonthlyPaymentsByEmployeeId(Long employeeId) {
        return monthlyPaymentDAO.getAllMonthlyPaymentsByEmployeeId(employeeId);
    }


    @Override
    public List<BonusPayment> getBonusPaymentsByMonthlyPayment(MonthlyPayment monthlyPayment) {
        return bonusPaymentService.getBonusPaymentsAssignedToMonthlyPayment(monthlyPayment.getId());
    }

    @Override
    public void addBonusPaymentToMonthlyPayment(MonthlyPayment monthlyPayment, BonusPayment bonusPayment) {
        bonusPaymentService.createBonusPayment(bonusPayment, monthlyPayment.getId());
    }
}
