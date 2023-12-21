package com.solvd.laba.persistence.order;

import com.solvd.laba.persistence.CommonDAO;
import com.solvd.laba.domain.order.Invoice;
import com.solvd.laba.domain.stock.Product;

import java.util.List;

public interface IInvoiceDAO extends CommonDAO<Invoice> {
    void update(Invoice invoice);

    void delete(Long id);

    void addProductToInvoice(Invoice invoice, Product product);

    void deleteProductFromInvoice(Invoice invoice, Product product);

    List<Product> getProductsByInvoice(Long invoiceId);
}
