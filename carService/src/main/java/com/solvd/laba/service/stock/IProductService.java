package com.solvd.laba.service.stock;

import com.solvd.laba.domain.stock.Product;

import java.util.List;

public interface IProductService {
    void createProduct(Product product);

    Product getProductById(Long id);

    List<Product> getAllProducts();

    void updateProduct(Product product);

    void deleteProduct(Long id);
}
