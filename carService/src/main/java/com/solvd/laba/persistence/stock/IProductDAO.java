package com.solvd.laba.persistence.stock;

import com.solvd.laba.persistence.CommonDAO;
import com.solvd.laba.domain.stock.Product;

public interface IProductDAO extends CommonDAO<Product> {
    void update(Product product);

    void delete(Long id);
}
