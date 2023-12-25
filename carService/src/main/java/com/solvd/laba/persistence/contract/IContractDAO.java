package com.solvd.laba.persistence.contract;

import com.solvd.laba.domain.contract.Contract;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;

public interface IContractDAO extends CommonDAO<Contract> {
    void create(Contract contract);

    List<Contract> getAll();

    void update(Contract contract);

    void delete(Long id);
}
