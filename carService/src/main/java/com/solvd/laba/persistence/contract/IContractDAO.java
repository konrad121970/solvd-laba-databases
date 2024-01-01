package com.solvd.laba.persistence.contract;

import com.solvd.laba.domain.contract.Contract;
import com.solvd.laba.persistence.CommonDAO;
import org.apache.ibatis.annotations.Param;

public interface IContractDAO extends CommonDAO<Contract> {


    void create(@Param("contract") Contract contract, @Param("employeeId") Long id);

    void update(Contract contract);

    void delete(Long id);
}
