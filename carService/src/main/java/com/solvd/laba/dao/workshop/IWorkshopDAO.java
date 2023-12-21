package com.solvd.laba.dao.workshop;

import com.solvd.laba.dao.CommonDAO;
import com.solvd.laba.domain.workshop.Workshop;

public interface IWorkshopDAO extends CommonDAO<Workshop> {
    void update(Workshop workshop);

    void delete(Long id);
}
