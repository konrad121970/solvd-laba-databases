package com.solvd.laba.service.people;

import com.solvd.laba.domain.people.Customer;

public interface ICustomerService {
    void createCustomer(Customer customer);

    Customer getCustomerById(Long id);


    void updateCustomer(Customer customer);

    void deleteCustomer(Long id);
}
