package com.solvd.laba.persistence.contract.impl.mybatis;

import com.solvd.laba.config.PersistenceConfig;
import com.solvd.laba.domain.contract.Contract;
import com.solvd.laba.persistence.contract.IContractDAO;
import org.apache.ibatis.session.SqlSession;

public class ContractMyBatisImpl implements IContractDAO {
    @Override
    public Contract getById(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IContractDAO contractDAO = sqlSession.getMapper(IContractDAO.class);
            return contractDAO.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(Contract contract, Long employeeId) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IContractDAO contractDAO = sqlSession.getMapper(IContractDAO.class);
            contractDAO.create(contract, employeeId);
        }
    }

    @Override
    public void update(Contract contract) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IContractDAO contractDAO = sqlSession.getMapper(IContractDAO.class);
            contractDAO.update(contract);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IContractDAO contractDAO = sqlSession.getMapper(IContractDAO.class);
            contractDAO.delete(id);
        }
    }
}
