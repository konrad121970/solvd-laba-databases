package com.solvd.laba.service.workshop.impl;

import com.solvd.laba.domain.workshop.Workshop;
import com.solvd.laba.persistence.workshop.IWorkshopDAO;
import com.solvd.laba.persistence.workshop.impl.WorkshopDAO;
import com.solvd.laba.service.workshop.IWorkshopService;

public class WorkshopService implements IWorkshopService {

    private final IWorkshopDAO workshopDAO;

    public WorkshopService() {
        this.workshopDAO = new WorkshopDAO();
    }

    @Override
    public void createWorkshop(Workshop workshop) {
        workshopDAO.create(workshop);
    }

    @Override
    public Workshop getWorkshopById(Long id) {
        return workshopDAO.getById(id);
    }


}
