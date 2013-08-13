package com.hengyi.japp.crm.domain.node.customer;

import java.io.Serializable;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.hengyi.japp.crm.domain.node.AbstractCrm;

@NodeEntity
public class Customer extends AbstractCrm implements Serializable {
	private static final long serialVersionUID = -831678558917732185L;
	public static final String ASSOCIATE_CUSTOMER = "ASSOCIATE_CUSTOMER";
	@RelatedTo(type = ASSOCIATE_CUSTOMER, elementClass = Customer.class, direction = Direction.BOTH)
	private Set<Customer> associateCustomers;

	public Set<Customer> getAssociateCustomers() {
		return associateCustomers;
	}

	public void setAssociateCustomers(Set<Customer> associateCustomers) {
		this.associateCustomers = associateCustomers;
	}
}
