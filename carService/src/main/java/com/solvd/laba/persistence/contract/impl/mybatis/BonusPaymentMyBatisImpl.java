package com.solvd.laba.persistence.contract.impl.mybatis;

import com.solvd.laba.config.PersistenceConfig;
import com.solvd.laba.domain.contract.BonusPayment;
import com.solvd.laba.persistence.contract.IBonusPaymentDAO;
import org.apache.ibatis.session.SqlSession;

public class BonusPaymentMyBatisImpl implements IBonusPaymentDAO {
    @Override
    public BonusPayment getById(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IBonusPaymentDAO bonusPaymentDAO = sqlSession.getMapper(IBonusPaymentDAO.class);
            return bonusPaymentDAO.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(BonusPayment bonusPayment, Long monthlyPaymentId) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IBonusPaymentDAO bonusPaymentDAO = sqlSession.getMapper(IBonusPaymentDAO.class);
            bonusPaymentDAO.create(bonusPayment, monthlyPaymentId);
        }
    }

    @Override
    public void update(BonusPayment bonusPayment) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IBonusPaymentDAO bonusPaymentDAO = sqlSession.getMapper(IBonusPaymentDAO.class);
            bonusPaymentDAO.update(bonusPayment);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IBonusPaymentDAO bonusPaymentDAO = sqlSession.getMapper(IBonusPaymentDAO.class);
            bonusPaymentDAO.delete(id);
        }
    }
}
