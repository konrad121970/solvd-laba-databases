package com.solvd.laba.persistence.workshop.impl.mybatis;

import com.solvd.laba.config.PersistenceConfig;
import com.solvd.laba.domain.workshop.Workshop;
import com.solvd.laba.persistence.workshop.IWorkshopDAO;
import org.apache.ibatis.session.SqlSession;

public class WorkshopMyBatisImpl implements IWorkshopDAO {
    @Override
    public Workshop getById(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IWorkshopDAO workshopDAO = sqlSession.getMapper(IWorkshopDAO.class);
            return workshopDAO.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(Workshop workshop, Long addressId) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IWorkshopDAO workshopDAO = sqlSession.getMapper(IWorkshopDAO.class);
            workshopDAO.create(workshop, addressId);
        }
    }

    @Override
    public void update(Workshop workshop) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IWorkshopDAO workshopDAO = sqlSession.getMapper(IWorkshopDAO.class);
            workshopDAO.update(workshop);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IWorkshopDAO workshopDAO = sqlSession.getMapper(IWorkshopDAO.class);
            workshopDAO.delete(id);
        }
    }
}
