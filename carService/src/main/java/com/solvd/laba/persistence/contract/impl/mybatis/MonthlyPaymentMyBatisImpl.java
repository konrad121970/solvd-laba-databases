package com.solvd.laba.persistence.contract.impl.mybatis;

import com.solvd.laba.domain.contract.MonthlyPayment;
import com.solvd.laba.persistence.contract.IMonthlyPaymentDAO;

import java.util.List;

public class MonthlyPaymentMyBatisImpl implements IMonthlyPaymentDAO {
    @Override
    public MonthlyPayment getById(Long id) {
        return null;
    }

    @Override
    public List<MonthlyPayment> getAllMonthlyPaymentsByEmployeeId(Long employeeId) {
        return null;
    }

    @Override
    public void create(MonthlyPayment monthlyPayment, Long employeesId) {

    }

    @Override
    public void update(MonthlyPayment monthlyPayment) {

    }

    @Override
    public void delete(Long id) {

    }
}
