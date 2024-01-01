package com.solvd.laba.service.contract.impl;

import com.solvd.laba.domain.contract.Contract;
import com.solvd.laba.persistence.contract.IContractDAO;
import com.solvd.laba.persistence.contract.impl.mybatis.ContractMyBatisImpl;
import com.solvd.laba.service.contract.IContractService;

public class ContractService implements IContractService {

    private final IContractDAO contractDAO;

    public ContractService() {
        // contractDAO = new ContractDAO();
        contractDAO = new ContractMyBatisImpl();
    }

    @Override
    public void createContract(Contract contract, Long employeeId) {
        contractDAO.create(contract, employeeId);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractDAO.getById(id);
    }


    @Override

    public void updateContract(Contract contract) {
        contractDAO.update(contract);
    }

    @Override
    public void deleteContract(Long id) {
        contractDAO.delete(id);
    }
}
