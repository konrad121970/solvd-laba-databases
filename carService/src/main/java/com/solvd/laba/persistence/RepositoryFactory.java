package com.solvd.laba.persistence;

import com.solvd.laba.persistence.contract.IBonusPaymentDAO;
import com.solvd.laba.persistence.contract.IContractDAO;
import com.solvd.laba.persistence.contract.IMonthlyPaymentDAO;
import com.solvd.laba.persistence.contract.impl.BonusPaymentDAO;
import com.solvd.laba.persistence.contract.impl.ContractDAO;
import com.solvd.laba.persistence.contract.impl.MonthlyPaymentDAO;
import com.solvd.laba.persistence.contract.impl.mybatis.BonusPaymentMyBatisImpl;
import com.solvd.laba.persistence.contract.impl.mybatis.ContractMyBatisImpl;
import com.solvd.laba.persistence.contract.impl.mybatis.MonthlyPaymentMyBatisImpl;
import com.solvd.laba.persistence.people.IEmployeeDAO;
import com.solvd.laba.persistence.people.impl.EmployeeDAO;
import com.solvd.laba.persistence.people.impl.mybatis.EmployeeMyBatisImpl;
import com.solvd.laba.persistence.workshop.IAddressDAO;
import com.solvd.laba.persistence.workshop.IWorkshopDAO;
import com.solvd.laba.persistence.workshop.impl.AddressDAO;
import com.solvd.laba.persistence.workshop.impl.WorkshopDAO;
import com.solvd.laba.persistence.workshop.impl.mybatis.AddressMyBatisImpl;
import com.solvd.laba.persistence.workshop.impl.mybatis.WorkshopMyBatisImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class RepositoryFactory {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    public static IWorkshopDAO createWorkshopRepository(String type) {
        IWorkshopDAO result;
        switch (type) {
            case "jdbc":
                result = new WorkshopDAO();
                break;
            case "mybatis":
                result = new WorkshopMyBatisImpl();
                break;
            default:
                result = new WorkshopDAO();
                LOGGER.info("Data source was not specified or is invalid. Defaulting to JDBC implementation");
                break;
        }
        return result;
    }

    public static IAddressDAO createAddressRepository(String type) {
        IAddressDAO result;
        switch (type) {
            case "jdbc":
                result = new AddressDAO();
                break;
            case "mybatis":
                result = new AddressMyBatisImpl();
                break;
            default:
                result = new AddressDAO();
                LOGGER.info("Data source was not specified or is invalid. Defaulting to JDBC implementation");
                break;
        }
        return result;
    }

    public static IEmployeeDAO createEmployeeRepository(String type) {
        IEmployeeDAO result;
        switch (type) {
            case "jdbc":
                result = new EmployeeDAO();
                break;
            case "mybatis":
                result = new EmployeeMyBatisImpl();
                break;
            default:
                result = new EmployeeDAO();
                LOGGER.info("Data source was not specified or is invalid. Defaulting to JDBC implementation");
                break;
        }
        return result;
    }

    public static IContractDAO createContractRepository(String type) {
        IContractDAO result;
        switch (type) {
            case "jdbc":
                result = new ContractDAO();
                break;
            case "mybatis":
                result = new ContractMyBatisImpl();
                break;
            default:
                result = new ContractDAO();
                LOGGER.info("Data source was not specified or is invalid. Defaulting to JDBC implementation");
                break;
        }
        return result;
    }

    public static IMonthlyPaymentDAO createMonthlyPaymentRepository(String type) {
        IMonthlyPaymentDAO result;
        switch (type) {
            case "jdbc":
                result = new MonthlyPaymentDAO();
                break;
            case "mybatis":
                result = new MonthlyPaymentMyBatisImpl();
                break;
            default:
                result = new MonthlyPaymentDAO();
                LOGGER.info("Data source was not specified or is invalid. Defaulting to JDBC implementation");
                break;
        }
        return result;
    }

    public static IBonusPaymentDAO createBonusPaymentRepository(String type) {
        IBonusPaymentDAO result;
        switch (type) {
            case "jdbc":
                result = new BonusPaymentDAO();
                break;
            case "mybatis":
                result = new BonusPaymentMyBatisImpl();
                break;
            default:
                result = new BonusPaymentDAO();
                LOGGER.info("Data source was not specified or is invalid. Defaulting to JDBC implementation");
                break;
        }
        return result;
    }


}
