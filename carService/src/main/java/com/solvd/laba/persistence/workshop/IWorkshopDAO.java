package com.solvd.laba.persistence.workshop;

import com.solvd.laba.domain.workshop.Workshop;
import com.solvd.laba.persistence.CommonDAO;
import org.apache.ibatis.annotations.Param;

public interface IWorkshopDAO extends CommonDAO<Workshop> {
    void create(@Param("workshop") Workshop workshop, @Param("addressId") Long addressId);

    void update(Workshop workshop);

    void delete(Long id);
}
