package com.solvd.laba.dao.workshop.impl;

import com.solvd.laba.dao.workshop.IWorkshopDAO;
import com.solvd.laba.domain.workshop.Workshop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class WorkshopDAO implements IWorkshopDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    @Override
    public void create(Workshop object) {

    }

    @Override
    public Workshop getById(Long id) {
        return null;
    }

    @Override
    public List<Workshop> getAll() {
        return null;
    }
}
