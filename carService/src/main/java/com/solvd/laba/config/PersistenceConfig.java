package com.solvd.laba.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class PersistenceConfig {

    private static SqlSessionFactory sessionFactory;

    static {
        try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
                    .build(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SqlSessionFactory getSessionFactory() {
        return sessionFactory;
    }

}