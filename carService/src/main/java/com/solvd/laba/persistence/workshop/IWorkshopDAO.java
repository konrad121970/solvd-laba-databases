package com.solvd.laba.persistence.workshop;

import com.solvd.laba.domain.workshop.Workshop;
import com.solvd.laba.persistence.CommonDAO;

public interface IWorkshopDAO extends CommonDAO<Workshop> {
    void create(Workshop workshop);


    void update(Workshop workshop);

    void delete(Long id);
}
