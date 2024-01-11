package com.solvd.laba.persistence;

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


    public static IWorkshopDAO createWorkshopDAO(String type) {
        IWorkshopDAO result;
        switch (type) {
            case "JDBC":
                result = new WorkshopDAO();
                break;
            case "MYBATIS":
                result = new WorkshopMyBatisImpl();
                break;
            default:
                result = new WorkshopDAO();
                LOGGER.info("Data source was not specified or is invalid. Defaulting to JDBC implementation");
                break;
        }
        return result;
    }

    public static IAddressDAO createAddressDao(String type) {
        IAddressDAO result;
        switch (type) {
            case "JDBC":
                result = new AddressDAO();
                break;
            case "MYBATIS":
                result = new AddressMyBatisImpl();
                break;
            default:
                result = new AddressDAO();
                LOGGER.info("Data source was not specified or is invalid. Defaulting to JDBC implementation");
                break;
        }
        return result;
    }

    public static IEmployeeDAO createEmployeeDao(String type) {
        IEmployeeDAO result;
        switch (type) {
            case "JDBC":
                result = new EmployeeDAO();
                break;
            case "MYBATIS":
                result = new EmployeeMyBatisImpl();
                break;
            default:
                result = new EmployeeDAO();
                LOGGER.info("Data source was not specified or is invalid. Defaulting to JDBC implementation");
                break;
        }
        return result;
    }


}
