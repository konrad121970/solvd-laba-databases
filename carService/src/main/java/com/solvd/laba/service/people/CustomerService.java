package com.solvd.laba.service.people;

import com.solvd.laba.domain.people.Customer;
import com.solvd.laba.persistence.people.ICustomerDAO;
import com.solvd.laba.persistence.people.impl.CustomerDAO;

public class CustomerService implements ICustomerService {
    private final ICustomerDAO customerDAO;

    public CustomerService() {
        this.customerDAO = new CustomerDAO();
    }

    @Override
    public void createCustomer(Customer customer) {
        customerDAO.create(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerDAO.getById(id);
    }


    @Override
    public void updateCustomer(Customer customer) {
        customerDAO.update(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerDAO.delete(id);
    }
}
