package com.solvd.laba.service.stock;

import com.solvd.laba.domain.stock.Product;
import com.solvd.laba.domain.stock.Stock;

import java.util.List;

public interface IStockService {
    void createStock(Stock stock);

    Stock getStockById(Long id);

    List<Stock> getAllStocks();

    void updateStock(Stock stock);

    void deleteStock(Long id);

    List<Product> getProductsByStockId(Long stockId);

    void addProductToStock(Long stockId, Long productId);

    void removeProductFromStock(Long stockId, Long productId);
}
