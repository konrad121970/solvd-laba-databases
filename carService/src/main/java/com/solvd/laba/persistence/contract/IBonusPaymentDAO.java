package com.solvd.laba.persistence.contract;

import com.solvd.laba.domain.contract.BonusPayment;
import com.solvd.laba.persistence.CommonDAO;

public interface IBonusPaymentDAO extends CommonDAO<BonusPayment> {
    void update(BonusPayment bonusPayment);

    void delete(Long id);
}
