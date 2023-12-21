package com.solvd.laba.domain.contract;

import java.sql.Date;
import java.util.List;

public class MonthlyPayment {
    private Long id;
    private Double amount;
    private Date payment_date;
    private List<BonusPayment> bonusPaymentList;

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

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public List<BonusPayment> getBonusPaymentList() {
        return bonusPaymentList;
    }

    public void setBonusPaymentList(List<BonusPayment> bonusPaymentList) {
        this.bonusPaymentList = bonusPaymentList;
    }
}
