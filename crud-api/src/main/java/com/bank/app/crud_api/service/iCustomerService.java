package com.bank.app.crud_api.service;

import com.bank.app.crud_api.models.Customer;

import java.util.List;

public interface iCustomerService {
    List<Customer> getAllCustomers();
    void createCustomer(Customer customer);
    Customer getCustomerById(Long id);
    Customer updateCustomer(Customer customer, Long id);
    String deleteCustomer(Long id);

}
