package com.solvd.laba.dao.stock;

import com.solvd.laba.dao.CommonDAO;
import com.solvd.laba.domain.stock.Product;

public interface IProductDAO extends CommonDAO<Product> {
    void update(Product product);

    void delete(Long id);
}
