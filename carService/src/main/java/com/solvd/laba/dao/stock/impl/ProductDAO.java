package com.solvd.laba.dao.stock.impl;

import com.solvd.laba.dao.stock.IProductDAO;
import com.solvd.laba.domain.stock.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class ProductDAO implements IProductDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void create(Product object) {

    }

    @Override
    public Product getById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }
}
