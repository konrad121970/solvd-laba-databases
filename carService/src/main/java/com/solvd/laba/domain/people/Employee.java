package com.solvd.laba.domain.people;

import com.solvd.laba.domain.account.Account;
import com.solvd.laba.domain.contract.Contract;
import com.solvd.laba.domain.contract.MonthlyPayment;
import com.solvd.laba.domain.order.ServiceOrder;

import java.util.ArrayList;
import java.util.List;

public class Employee extends Person {
    private String position;
    private Account account;
    private List<ServiceOrder> serviceOrders;
    private List<MonthlyPayment> monthlyPayments;
    private List<Contract> contracts;

    public Employee() {
        this.serviceOrders = new ArrayList<>();
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

    public List<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(List<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
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
