package com.solvd.laba.persistence.contract;

import com.solvd.laba.domain.contract.MonthlyPayment;
import com.solvd.laba.persistence.CommonDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IMonthlyPaymentDAO extends CommonDAO<MonthlyPayment> {
    List<MonthlyPayment> getAllMonthlyPaymentsByEmployeeId(Long employeeId);

    void create(@Param("monthlyPayment") MonthlyPayment monthlyPayment, @Param("employeeId") Long id);

    void update(MonthlyPayment monthlyPayment);

    void delete(Long id);

}
