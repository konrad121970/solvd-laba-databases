package com.solvd.laba.service.stock.impl;

import com.solvd.laba.domain.stock.Product;
import com.solvd.laba.persistence.stock.IProductDAO;
import com.solvd.laba.persistence.stock.impl.ProductDAO;
import com.solvd.laba.service.stock.IProductService;

import java.util.List;

public class ProductService implements IProductService {

    private final IProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    @Override
    public void createProduct(Product product) {
        productDAO.create(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productDAO.getById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.getAll();
    }

    @Override
    public void updateProduct(Product product) {
        productDAO.update(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productDAO.delete(id);
    }
}
