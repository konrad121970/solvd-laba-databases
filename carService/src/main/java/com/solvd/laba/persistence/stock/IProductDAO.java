package com.solvd.laba.persistence.stock;

import com.solvd.laba.domain.stock.Product;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;

public interface IProductDAO extends CommonDAO<Product> {
    void create(Product product);

    List<Product> getAll();

    void update(Product product);

    void delete(Long id);
}
