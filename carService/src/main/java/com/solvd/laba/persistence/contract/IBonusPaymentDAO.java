package com.solvd.laba.persistence.contract;

import com.solvd.laba.domain.contract.BonusPayment;
import com.solvd.laba.persistence.CommonDAO;
import org.apache.ibatis.annotations.Param;

public interface IBonusPaymentDAO extends CommonDAO<BonusPayment> {
    void create(@Param("bonusPayment") BonusPayment bonusPayment, @Param("monthlyPaymentId") Long monthlyPaymentId);

    void update(BonusPayment bonusPayment);

    void delete(Long id);

}
