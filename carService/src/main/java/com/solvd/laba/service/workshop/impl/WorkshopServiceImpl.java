package com.solvd.laba.service.workshop.impl;

import com.solvd.laba.domain.workshop.Workshop;
import com.solvd.laba.persistence.workshop.IWorkshopDAO;
import com.solvd.laba.persistence.workshop.impl.WorkshopDAO;
import com.solvd.laba.service.workshop.IWorkshopService;

import java.util.List;

public class WorkshopServiceImpl implements IWorkshopService {

    private final IWorkshopDAO workshopDAO;

    public WorkshopServiceImpl() {
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

    @Override
    public List<Workshop> getAllWorkshops() {
        return workshopDAO.getAll();
    }
}
