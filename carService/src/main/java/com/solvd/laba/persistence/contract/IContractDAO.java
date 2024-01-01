package com.solvd.laba.persistence.contract;

import com.solvd.laba.domain.contract.Contract;
import com.solvd.laba.persistence.CommonDAO;

public interface IContractDAO extends CommonDAO<Contract> {


    void create(Contract contract, Long employeeId);

    void update(Contract contract);

    void delete(Long id);
}
