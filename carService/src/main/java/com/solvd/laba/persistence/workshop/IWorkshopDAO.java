package com.solvd.laba.persistence.workshop;

import com.solvd.laba.persistence.CommonDAO;
import com.solvd.laba.domain.workshop.Workshop;

public interface IWorkshopDAO extends CommonDAO<Workshop> {
    void update(Workshop workshop);

    void delete(Long id);
}
