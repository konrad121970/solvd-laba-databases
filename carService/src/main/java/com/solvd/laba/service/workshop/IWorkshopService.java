package com.solvd.laba.service.workshop;

import com.solvd.laba.domain.workshop.Workshop;

public interface IWorkshopService {
    void createWorkshop(Workshop workshop, Long id);

    Workshop getWorkshopById(Long id);

}
