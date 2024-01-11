package com.solvd.laba.service.workshop.impl;

import com.solvd.laba.config.Config;
import com.solvd.laba.domain.workshop.Workshop;
import com.solvd.laba.persistence.RepositoryFactory;
import com.solvd.laba.persistence.workshop.IWorkshopDAO;
import com.solvd.laba.service.workshop.IWorkshopService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class WorkshopService implements IWorkshopService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private final IWorkshopDAO workshopDAO;

    public WorkshopService() {
        workshopDAO = RepositoryFactory.createWorkshopRepository(Config.IMPL.getValue());
    }

    @Override
    public void createWorkshop(Workshop workshop, Long addressId) {
        workshopDAO.create(workshop, addressId);
    }

    @Override
    public Workshop getWorkshopById(Long id) {
        return workshopDAO.getById(id);
    }


}
