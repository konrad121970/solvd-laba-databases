package com.solvd.laba.domain.people;

import com.solvd.laba.domain.ServiceOrder;

import java.util.List;

public class Customer extends Person{

    private String email;

    private List<ServiceOrder> serviceOrders;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(List<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }
}


