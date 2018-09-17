package com.gmail.muhammad.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gmail.muhammad.backend.data.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
