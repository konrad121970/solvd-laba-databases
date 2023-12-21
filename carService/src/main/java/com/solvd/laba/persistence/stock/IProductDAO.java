package com.solvd.laba.persistence.stock;

import com.solvd.laba.domain.stock.Product;
import com.solvd.laba.persistence.CommonDAO;

public interface IProductDAO extends CommonDAO<Product> {
    void create(Product product);

    void update(Product product);

    void delete(Long id);
}
