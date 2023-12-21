package com.solvd.laba.service.stock.impl;

import com.solvd.laba.domain.stock.Product;
import com.solvd.laba.domain.stock.Stock;
import com.solvd.laba.persistence.stock.IStockDAO;
import com.solvd.laba.persistence.stock.impl.StockDAO;
import com.solvd.laba.service.stock.IStockService;

import java.util.List;

public class StockService implements IStockService {

    private final IStockDAO stockDAO;

    public StockService() {
        this.stockDAO = new StockDAO();
    }

    @Override
    public void createStock(Stock stock) {
        stockDAO.create(stock);
    }

    @Override
    public Stock getStockById(Long id) {
        return stockDAO.getById(id);
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockDAO.getAll();
    }

    @Override
    public void updateStock(Stock stock) {
        stockDAO.update(stock);
    }

    @Override
    public void deleteStock(Long id) {
        stockDAO.delete(id);
    }

    @Override
    public List<Product> getProductsByStockId(Long stockId) {
        return stockDAO.getProductsByStockId(stockId);
    }

    @Override
    public void addProductToStock(Long stockId, Long productId) {
        stockDAO.addProductToStock(stockId, productId);
    }

    @Override
    public void removeProductFromStock(Long stockId, Long productId) {
        stockDAO.removeProductFromStock(stockId, productId);
    }
}
