package com.solvd.laba.service.contract.impl;

import com.solvd.laba.domain.contract.BonusPayment;
import com.solvd.laba.persistence.contract.IBonusPaymentDAO;
import com.solvd.laba.persistence.contract.impl.mybatis.BonusPaymentMyBatisImpl;
import com.solvd.laba.service.contract.IBonusPaymentService;

public class BonusPaymentService implements IBonusPaymentService {

    private final IBonusPaymentDAO bonusPaymentDAO;

    public BonusPaymentService() {
        // this.bonusPaymentDAO = new BonusPaymentDAO();

        this.bonusPaymentDAO = new BonusPaymentMyBatisImpl();
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


}
