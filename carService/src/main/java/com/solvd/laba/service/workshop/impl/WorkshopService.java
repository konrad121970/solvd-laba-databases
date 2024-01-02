package com.solvd.laba.service.workshop.impl;

import com.solvd.laba.domain.workshop.Workshop;
import com.solvd.laba.persistence.workshop.IWorkshopDAO;
import com.solvd.laba.persistence.workshop.impl.mybatis.WorkshopMyBatisImpl;
import com.solvd.laba.service.workshop.IWorkshopService;

public class WorkshopService implements IWorkshopService {

    private final IWorkshopDAO workshopDAO;

    public WorkshopService() {
        // workshopDAO = new WorkshopDAO();

        workshopDAO = new WorkshopMyBatisImpl();
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
