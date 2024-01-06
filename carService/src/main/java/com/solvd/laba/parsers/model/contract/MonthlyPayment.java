package com.solvd.laba.parsers.model.contract;

import com.solvd.laba.parsers.jaxb.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class MonthlyPayment {
    private Long id;
    private Double amount;
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate paymentDate;
    @XmlElementWrapper(name = "bonusPayments")
    @XmlElement(name = "bonusPayment")
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
