package com.solvd.laba.service.contract;

import com.solvd.laba.domain.contract.Contract;

public interface IContractService {
    void createContract(Contract contract, Long employeeId);

    Contract getContractById(Long id);


    void updateContract(Contract contract);

    void deleteContract(Long id);
}
