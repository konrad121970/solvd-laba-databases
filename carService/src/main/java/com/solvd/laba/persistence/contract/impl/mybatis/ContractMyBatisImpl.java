package com.solvd.laba.persistence.contract.impl.mybatis;

import com.solvd.laba.domain.contract.Contract;
import com.solvd.laba.persistence.contract.IContractDAO;

public class ContractMyBatisImpl implements IContractDAO {
    @Override
    public Contract getById(Long id) {
        return null;
    }

    @Override
    public void create(Contract contract, Long employeeId) {

    }

    @Override
    public void update(Contract contract) {

    }

    @Override
    public void delete(Long id) {

    }
}
