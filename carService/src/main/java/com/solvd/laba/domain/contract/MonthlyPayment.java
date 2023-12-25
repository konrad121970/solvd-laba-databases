package com.solvd.laba.domain.contract;

import java.sql.Date;
import java.util.List;

public class MonthlyPayment {
    private Long id;
    private Double amount;
    private Date paymentDate;
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

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public List<BonusPayment> getBonusPaymentList() {
        return bonusPaymentList;
    }

    public void setBonusPaymentList(List<BonusPayment> bonusPaymentList) {
        this.bonusPaymentList = bonusPaymentList;
    }
}
