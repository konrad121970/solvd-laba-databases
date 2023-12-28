package com.solvd.laba.service.order;

import com.solvd.laba.domain.order.Invoice;
import com.solvd.laba.domain.order.ServiceOrder;
import com.solvd.laba.domain.stock.Product;

import java.util.List;

public interface IInvoiceService {

    void createInvoice(Invoice invoice, ServiceOrder serviceOrder);

    Invoice getInvoiceById(Long id);

    List<Invoice> getAllInvoices();

    void updateInvoice(Invoice invoice);

    void deleteInvoice(Long id);

    void addProductToInvoice(Invoice invoice, Product product);

    void deleteProductFromInvoice(Invoice invoice, Product product);

    List<Product> getProductsByInvoice(Long invoiceId);
}
