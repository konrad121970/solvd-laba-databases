package com.solvd.laba.service.workshop;

import com.solvd.laba.domain.workshop.Workshop;

public interface IWorkshopService {
    void createWorkshop(Workshop workshop);

    Workshop getWorkshopById(Long id);

}
