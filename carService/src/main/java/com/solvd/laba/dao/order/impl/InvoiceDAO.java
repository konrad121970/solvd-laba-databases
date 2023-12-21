package com.solvd.laba.dao.order.impl;

import com.solvd.laba.dao.order.IInvoiceDAO;
import com.solvd.laba.domain.order.Invoice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class InvoiceDAO implements IInvoiceDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void create(Invoice object) {

    }

    @Override
    public Invoice getById(Long id) {
        return null;
    }

    @Override
    public List<Invoice> getAll() {
        return null;
    }
}
