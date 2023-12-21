package com.solvd.laba.dao.stock.impl;

import com.solvd.laba.dao.stock.IStockDAO;
import com.solvd.laba.domain.stock.Stock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class StockDAO implements IStockDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void create(Stock object) {

    }

    @Override
    public Stock getById(Long id) {
        return null;
    }

    @Override
    public List<Stock> getAll() {
        return null;
    }
}
