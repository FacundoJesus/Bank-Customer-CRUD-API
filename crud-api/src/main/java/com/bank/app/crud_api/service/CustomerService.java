package com.bank.app.crud_api.service;

import com.bank.app.crud_api.models.Customer;
import com.bank.app.crud_api.repositories.iRepositoryCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements iCustomerService {

    @Autowired
    private iRepositoryCustomer repositoryCustomer;

    @Override
    public List<Customer> getAllCustomers() {
        return repositoryCustomer.findAll();
    }

    @Override
    public void createCustomer(Customer customer) {
        repositoryCustomer.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        Customer savedCustomer = repositoryCustomer.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        return savedCustomer;
    }

    @Override
    public Customer updateCustomer(Customer customer, Long customerId) {

        Customer savedCustomer = repositoryCustomer.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        savedCustomer.setEmail(customer.getEmail());
        savedCustomer.setFirstName(customer.getFirstName());
        savedCustomer.setLastName(customer.getLastName());
        savedCustomer.setPhoneNumber(customer.getPhoneNumber());

        return repositoryCustomer.save(savedCustomer);
    }


    @Override
    public String deleteCustomer(Long id) {

        if (!repositoryCustomer.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        repositoryCustomer.deleteById(id);
        return "Customer with customerId: " + id + " deleted succesfully!";
    }
}
