package com.solvd.laba.service.contract.impl;

import com.solvd.laba.domain.contract.BonusPayment;
import com.solvd.laba.persistence.contract.IBonusPaymentDAO;
import com.solvd.laba.persistence.contract.impl.BonusPaymentDAO;
import com.solvd.laba.service.contract.IBonusPaymentService;

import java.util.List;

public class BonusPaymentService implements IBonusPaymentService {

    private final IBonusPaymentDAO bonusPaymentDAO;

    public BonusPaymentService() {
        this.bonusPaymentDAO = new BonusPaymentDAO();
    }

    @Override
    public void createBonusPayment(BonusPayment bonusPayment, Long monthlyPaymentId) {
        bonusPaymentDAO.create(bonusPayment, monthlyPaymentId);
    }

    @Override

    public BonusPayment getBonusPaymentById(Long id) {
        return bonusPaymentDAO.getById(id);
    }


    @Override

    public void updateBonusPayment(BonusPayment bonusPayment) {
        bonusPaymentDAO.update(bonusPayment);
    }

    @Override

    public void deleteBonusPayment(Long id) {
        bonusPaymentDAO.delete(id);
    }

    @Override
    public List<BonusPayment> getBonusPaymentsAssignedToMonthlyPayment(Long id) {
        return bonusPaymentDAO.getBonusPaymentsByMonthlyPaymentId(id);
    }

}
