package com.solvd.laba.parsers.model;


import com.solvd.laba.parsers.model.contract.Contract;
import com.solvd.laba.parsers.model.contract.MonthlyPayment;

import java.util.ArrayList;
import java.util.List;

public class Employee extends Person {
    private String position;
    private Account account;
    private List<MonthlyPayment> monthlyPayments;
    private List<Contract> contracts;

    public Employee() {
        this.monthlyPayments = new ArrayList<>();
        this.contracts = new ArrayList<>();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<MonthlyPayment> getMonthlyPayments() {
        return monthlyPayments;
    }

    public void setMonthlyPayments(List<MonthlyPayment> monthlyPayments) {
        this.monthlyPayments = monthlyPayments;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }
}
