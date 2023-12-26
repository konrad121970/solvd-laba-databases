package com.solvd.laba.service.contract;

import com.solvd.laba.domain.contract.Contract;

import java.util.List;

public interface IContractService {
    void createContract(Contract contract, Long employeeId);

    Contract getContractById(Long id);

    List<Contract> getAllContracts();

    void updateContract(Contract contract);

    void deleteContract(Long id);
}
