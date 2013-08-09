package com.hengyi.japp.crm.service.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.hengyi.japp.crm.domain.Customer;
import com.hengyi.japp.crm.domain.repository.CustomerRepository;
import com.hengyi.japp.crm.service.CustomerService;

@Named
@Transactional
public class CustomerServiceImpl implements CustomerService {
	@PersistenceContext
	EntityManager em;
	@Inject
	private CustomerRepository customerRepository;

	@Override
	public Customer findOne(String uuid) {
		return em.find(Customer.class, uuid);
	}

	@Override
	public void save(Customer customer) throws Exception {
		if (em.contains(customer))
			em.merge(customer);
		else
			em.persist(customer);
	}

	public Iterable<Customer> findAll(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}
}
