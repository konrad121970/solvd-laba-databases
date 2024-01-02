package com.solvd.laba.persistence.people.impl.mybatis;

import com.solvd.laba.config.PersistenceConfig;
import com.solvd.laba.domain.people.Employee;
import com.solvd.laba.persistence.people.IEmployeeDAO;
import org.apache.ibatis.session.SqlSession;

public class EmployeeMyBatisImpl implements IEmployeeDAO {
    @Override
    public Employee getById(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IEmployeeDAO employeeDAO = sqlSession.getMapper(IEmployeeDAO.class);
            return employeeDAO.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(Employee employee, Long workshopId) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IEmployeeDAO employeeDAO = sqlSession.getMapper(IEmployeeDAO.class);
            employeeDAO.create(employee, workshopId);
        }
    }

    @Override
    public void update(Employee employee) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IEmployeeDAO employeeDAO = sqlSession.getMapper(IEmployeeDAO.class);
            employeeDAO.update(employee);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            IEmployeeDAO employeeDAO = sqlSession.getMapper(IEmployeeDAO.class);
            employeeDAO.delete(id);
        }
    }
}
