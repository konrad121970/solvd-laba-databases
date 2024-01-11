package com.solvd.laba.service.contract.impl;

import com.solvd.laba.config.Config;
import com.solvd.laba.domain.contract.Contract;
import com.solvd.laba.persistence.RepositoryFactory;
import com.solvd.laba.persistence.contract.IContractDAO;
import com.solvd.laba.service.contract.IContractService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class ContractService implements IContractService {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private final IContractDAO contractDAO;

    public ContractService() {

        contractDAO = RepositoryFactory.createContractRepository(Config.IMPL.getValue());
//        if (Config.IMPL.getValue().equals("jdbc")) {
//            contractDAO = new ContractDAO();
//        } else if (Config.IMPL.getValue().equals("myBatis")) {
//            contractDAO = new ContractMyBatisImpl();
//        } else {
//            LOGGER.info("{}: Data source was not specified or is invalid. Defaulting to JDBC implementation", this.getClass().getSimpleName());
//            contractDAO = new ContractDAO();
//        }
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
