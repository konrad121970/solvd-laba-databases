package com.solvd.laba.persistence.workshop;

import com.solvd.laba.domain.workshop.Workshop;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;

public interface IWorkshopDAO extends CommonDAO<Workshop> {
    void create(Workshop workshop);

    List<Workshop> getAll();

    void update(Workshop workshop);

    void delete(Long id);
}
