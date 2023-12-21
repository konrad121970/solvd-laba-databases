package com.solvd.laba.service.workshop;

import com.solvd.laba.domain.workshop.Workshop;

import java.util.List;

public interface IWorkshopService {
    void createWorkshop(Workshop workshop);

    Workshop getWorkshopById(Long id);

    List<Workshop> getAllWorkshops();
}
