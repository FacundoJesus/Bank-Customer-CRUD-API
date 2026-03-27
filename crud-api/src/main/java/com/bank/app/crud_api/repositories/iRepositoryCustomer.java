package com.bank.app.crud_api.repositories;

import com.bank.app.crud_api.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iRepositoryCustomer extends JpaRepository<Customer,Long> {
    boolean existsByEmail(String email);
}
