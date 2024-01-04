package com.solvd.laba.service.contract.impl;

import com.solvd.laba.config.Config;
import com.solvd.laba.domain.contract.BonusPayment;
import com.solvd.laba.domain.contract.MonthlyPayment;
import com.solvd.laba.persistence.contract.IMonthlyPaymentDAO;
import com.solvd.laba.persistence.contract.impl.MonthlyPaymentDAO;
import com.solvd.laba.persistence.contract.impl.mybatis.MonthlyPaymentMyBatisImpl;
import com.solvd.laba.service.contract.IBonusPaymentService;
import com.solvd.laba.service.contract.IMonthlyPaymentsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class MonthlyPaymentService implements IMonthlyPaymentsService {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private final IMonthlyPaymentDAO monthlyPaymentDAO;
    private final IBonusPaymentService bonusPaymentService;


    public MonthlyPaymentService() {

        if (Config.IMPL.getValue().equals("jdbc")) {
            monthlyPaymentDAO = new MonthlyPaymentDAO();
            bonusPaymentService = new BonusPaymentService();
        } else if (Config.IMPL.getValue().equals("myBatis")) {
            monthlyPaymentDAO = new MonthlyPaymentMyBatisImpl();
            bonusPaymentService = new BonusPaymentService();
        } else {
            LOGGER.info("{}: Data source was not specified or is invalid. Defaulting to JDBC implementation", this.getClass().getSimpleName());
            monthlyPaymentDAO = new MonthlyPaymentDAO();
            bonusPaymentService = new BonusPaymentService();
        }
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
    public void addBonusPaymentToMonthlyPayment(MonthlyPayment monthlyPayment, BonusPayment bonusPayment) {
        bonusPaymentService.createBonusPayment(bonusPayment, monthlyPayment.getId());
    }
}
