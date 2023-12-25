package com.solvd.laba.persistence.contract;

import com.solvd.laba.domain.contract.MonthlyPayment;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;

public interface IMonthlyPaymentDAO extends CommonDAO<MonthlyPayment> {
    List<MonthlyPayment> getAllMonthlyPaymentsByEmployeeId(Long employeeId);

    void create(MonthlyPayment monthlyPayment, Long employeesId);

    void update(MonthlyPayment monthlyPayment);

    void delete(Long id);

}
