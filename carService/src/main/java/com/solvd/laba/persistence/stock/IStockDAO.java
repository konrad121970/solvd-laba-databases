package com.solvd.laba.persistence.stock;

import com.solvd.laba.domain.stock.Stock;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;

public interface IStockDAO extends CommonDAO<Stock> {
    void create(Stock stock);

    List<Stock> getAll();

    void update(Stock stock);

    void delete(Long id);

    void addProductToStock(Long stockId, Long productId);

    void removeProductFromStock(Long stockId, Long productId);
}
