package com.solvd.laba.service.contract.impl;

import com.solvd.laba.config.Config;
import com.solvd.laba.domain.contract.BonusPayment;
import com.solvd.laba.persistence.contract.IBonusPaymentDAO;
import com.solvd.laba.persistence.contract.impl.BonusPaymentDAO;
import com.solvd.laba.persistence.contract.impl.mybatis.BonusPaymentMyBatisImpl;
import com.solvd.laba.service.contract.IBonusPaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class BonusPaymentService implements IBonusPaymentService {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private final IBonusPaymentDAO bonusPaymentDAO;

    public BonusPaymentService() {

        if (Config.IMPL.getValue().equals("jdbc")) {
            bonusPaymentDAO = new BonusPaymentDAO();
        } else if (Config.IMPL.getValue().equals("myBatis")) {
            bonusPaymentDAO = new BonusPaymentMyBatisImpl();
        } else {
            LOGGER.info("{}: Data source was not specified or is invalid. Defaulting to JDBC implementation", this.getClass().getSimpleName());
            bonusPaymentDAO = new BonusPaymentDAO();
        }
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
