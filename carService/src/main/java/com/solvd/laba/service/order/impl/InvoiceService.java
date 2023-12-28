package com.solvd.laba.service.order.impl;

import com.solvd.laba.domain.order.Invoice;
import com.solvd.laba.domain.order.ServiceOrder;
import com.solvd.laba.domain.stock.Product;
import com.solvd.laba.persistence.order.IInvoiceDAO;
import com.solvd.laba.persistence.order.impl.InvoiceDAO;
import com.solvd.laba.service.order.IInvoiceService;

import java.util.List;

public class InvoiceService implements IInvoiceService {

    private final IInvoiceDAO invoiceDAO;

    public InvoiceService() {
        this.invoiceDAO = new InvoiceDAO();
    }

    @Override
    public void createInvoice(Invoice invoice, ServiceOrder serviceOrder) {
        invoiceDAO.create(invoice, serviceOrder.getId());
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        return invoiceDAO.getById(id);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceDAO.getAll();
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        invoiceDAO.update(invoice);
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceDAO.delete(id);
    }

    @Override
    public void addProductToInvoice(Invoice invoice, Product product) {
        invoiceDAO.addProductToInvoice(invoice, product);
    }

    @Override
    public void deleteProductFromInvoice(Invoice invoice, Product product) {
        invoiceDAO.deleteProductFromInvoice(invoice, product);
    }

    @Override
    public List<Product> getProductsByInvoice(Long invoiceId) {
        return invoiceDAO.getProductsByInvoice(invoiceId);
    }
}
