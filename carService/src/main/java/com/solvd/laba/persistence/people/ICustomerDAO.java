package com.solvd.laba.persistence.people;

import com.solvd.laba.domain.people.Customer;

public interface ICustomerDAO extends IPersonDAO<Customer> {
    void create(Customer object);

    void update(Customer customer);

    void delete(Long id);
}
