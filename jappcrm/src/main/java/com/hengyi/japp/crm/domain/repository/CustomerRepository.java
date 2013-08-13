package com.hengyi.japp.crm.domain.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.hengyi.japp.crm.domain.node.customer.Customer;

public interface CustomerRepository extends GraphRepository<Customer>,
		NamedIndexRepository<Customer>,
		RelationshipOperationsRepository<Customer> {
}
