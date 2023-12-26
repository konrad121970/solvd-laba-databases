package com.solvd.laba.service.contract.impl;

import com.solvd.laba.domain.contract.Contract;
import com.solvd.laba.persistence.contract.IContractDAO;
import com.solvd.laba.persistence.contract.impl.ContractDAO;
import com.solvd.laba.service.contract.IContractService;

import java.util.List;

public class ContractService implements IContractService {

    private final IContractDAO contractDAO;

    public ContractService() {
        this.contractDAO = new ContractDAO();
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
    public List<Contract> getAllContracts() {
        return contractDAO.getAll();
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
