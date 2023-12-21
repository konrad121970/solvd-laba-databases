package com.solvd.laba.domain.stock;

import com.solvd.laba.domain.order.Invoice;

import java.util.List;

public class Product {
    private Long id;
    private String productNumber;
    private String name;
    private Double price;
    private List<Invoice> invoices;
    private List<Stock> stocks;
}
