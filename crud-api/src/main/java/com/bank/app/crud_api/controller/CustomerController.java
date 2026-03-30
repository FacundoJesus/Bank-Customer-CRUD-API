package com.bank.app.crud_api.controller;


import com.bank.app.crud_api.models.Customer;
import com.bank.app.crud_api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerbyId(@PathVariable Long id) {
        try {
            Customer customer = customerService.getCustomerById(id);
            return new ResponseEntity<Customer>(customer,HttpStatus.OK);
        }catch(ResponseStatusException ex) {
            return new ResponseEntity<>(ex.getReason(),ex.getStatusCode());
        }

    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        try {
            customerService.createCustomer(customer);
            return new ResponseEntity<>("Customer created succesfully!", HttpStatus.CREATED);
        }
        catch(ResponseStatusException ex) {
            return new ResponseEntity<>(ex.getReason(),ex.getStatusCode());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id){
        try {
            String status = customerService.deleteCustomer(id);
            return new ResponseEntity<>(status,HttpStatus.OK);

        }
        catch(ResponseStatusException ex) {
            return new ResponseEntity<>(ex.getReason(),ex.getStatusCode());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
        try {
            Customer savedCustomer = customerService.updateCustomer(customer,id);
            return new ResponseEntity<Customer>(savedCustomer,HttpStatus.OK);
        }
        catch(ResponseStatusException ex) {
            return new ResponseEntity<>(ex.getReason(),ex.getStatusCode());
        }
    }








}
