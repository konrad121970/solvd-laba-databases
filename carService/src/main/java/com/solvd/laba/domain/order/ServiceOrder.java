package com.solvd.laba.domain.order;

import java.sql.Date;
import java.util.Optional;

public class ServiceOrder {
    private Long id;
    private Date date;
    private boolean completed;
    private Invoice invoice;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Optional<Invoice> getInvoice() {
        return Optional.ofNullable(invoice);
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
