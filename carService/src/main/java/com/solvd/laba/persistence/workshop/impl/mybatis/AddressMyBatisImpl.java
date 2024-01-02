package com.solvd.laba.persistence.workshop.impl.mybatis;

import com.solvd.laba.config.PersistenceConfig;
import com.solvd.laba.domain.workshop.Address;
import com.solvd.laba.persistence.workshop.IAddressDAO;
import org.apache.ibatis.session.SqlSession;

public class AddressMyBatisImpl implements IAddressDAO {

    @Override
    public Address getById(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IAddressDAO addressDAO = sqlSession.getMapper(IAddressDAO.class);
            return addressDAO.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(Address address) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IAddressDAO addressDAO = sqlSession.getMapper(IAddressDAO.class);
            addressDAO.create(address);
        }
    }


    @Override
    public void update(Address address) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IAddressDAO addressDAO = sqlSession.getMapper(IAddressDAO.class);
            addressDAO.update(address);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IAddressDAO addressDAO = sqlSession.getMapper(IAddressDAO.class);
            addressDAO.delete(id);
        }
    }
}
