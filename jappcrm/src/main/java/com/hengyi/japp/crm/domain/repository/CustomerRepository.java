package com.hengyi.japp.crm.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hengyi.japp.crm.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
