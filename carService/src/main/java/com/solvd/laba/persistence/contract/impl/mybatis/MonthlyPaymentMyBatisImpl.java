package com.solvd.laba.persistence.contract.impl.mybatis;

import com.solvd.laba.config.PersistenceConfig;
import com.solvd.laba.domain.contract.MonthlyPayment;
import com.solvd.laba.persistence.contract.IMonthlyPaymentDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MonthlyPaymentMyBatisImpl implements IMonthlyPaymentDAO {
    @Override
    public MonthlyPayment getById(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IMonthlyPaymentDAO monthlyPaymentDAO = sqlSession.getMapper(IMonthlyPaymentDAO.class);
            return monthlyPaymentDAO.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<MonthlyPayment> getAllMonthlyPaymentsByEmployeeId(Long employeeId) {
        return null;
    }

    @Override
    public void create(MonthlyPayment monthlyPayment, Long employeesId) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IMonthlyPaymentDAO monthlyPaymentDAO = sqlSession.getMapper(IMonthlyPaymentDAO.class);
            monthlyPaymentDAO.create(monthlyPayment, employeesId);
        }
    }

    @Override
    public void update(MonthlyPayment monthlyPayment) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IMonthlyPaymentDAO monthlyPaymentDAO = sqlSession.getMapper(IMonthlyPaymentDAO.class);
            monthlyPaymentDAO.update(monthlyPayment);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IMonthlyPaymentDAO monthlyPaymentDAO = sqlSession.getMapper(IMonthlyPaymentDAO.class);
            monthlyPaymentDAO.delete(id);
        }
    }
}
