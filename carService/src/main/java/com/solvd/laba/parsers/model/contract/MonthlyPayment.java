package com.solvd.laba.parsers.model.contract;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MonthlyPayment {
    private Long id;
    private Double amount;
    private LocalDate paymentDate;
    private List<BonusPayment> bonusPaymentList;

    public MonthlyPayment() {
        bonusPaymentList = new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public List<BonusPayment> getBonusPaymentList() {
        return bonusPaymentList;
    }

    public void setBonusPaymentList(List<BonusPayment> bonusPaymentList) {
        this.bonusPaymentList = bonusPaymentList;
    }
}
