package com.solvd.laba.domain.order;

import com.solvd.laba.domain.stock.Product;

import java.sql.Timestamp;
import java.util.List;

public class Invoice {
    private Long id;
    private Timestamp date_time;
    private Double totalPrice;
    private List<Product> products;

}
