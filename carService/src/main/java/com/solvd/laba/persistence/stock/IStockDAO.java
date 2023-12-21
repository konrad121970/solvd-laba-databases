package com.solvd.laba.persistence.stock;

import com.solvd.laba.persistence.CommonDAO;
import com.solvd.laba.domain.stock.Product;
import com.solvd.laba.domain.stock.Stock;

import java.util.List;

public interface IStockDAO extends CommonDAO<Stock> {
    void update(Stock stock);

    void delete(Long id);

    List<Product> getProductsByStockId(Long stockId);
}
