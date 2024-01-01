package com.solvd.laba.persistence.contract.impl.mybatis;

import com.solvd.laba.domain.contract.BonusPayment;
import com.solvd.laba.persistence.contract.IBonusPaymentDAO;

public class BonusPaymentMyBatisImpl implements IBonusPaymentDAO {
    @Override
    public BonusPayment getById(Long id) {
        return null;
    }

    @Override
    public void create(BonusPayment bonusPayment, Long monthlyPaymentId) {

    }

    @Override
    public void update(BonusPayment bonusPayment) {

    }

    @Override
    public void delete(Long id) {

    }
}
