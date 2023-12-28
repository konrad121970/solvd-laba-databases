package com.solvd.laba.persistence.order;

import com.solvd.laba.domain.order.Invoice;
import com.solvd.laba.domain.stock.Product;
import com.solvd.laba.persistence.CommonDAO;

import java.util.List;

public interface IInvoiceDAO extends CommonDAO<Invoice> {

    void create(Invoice invoice, Long serviceOrderId);

    List<Invoice> getAll();

    void update(Invoice invoice);

    void delete(Long id);

    void addProductToInvoice(Invoice invoice, Product product);

    void deleteProductFromInvoice(Invoice invoice, Product product);

    List<Product> getProductsByInvoice(Long invoiceId);
}
