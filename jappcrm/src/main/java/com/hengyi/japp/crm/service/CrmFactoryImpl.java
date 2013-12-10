package com.hengyi.japp.crm.service;

import javax.inject.Named;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.hengyi.japp.crm.data.CrmType;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.domain.storage.Storage;
import com.hengyi.japp.crm.dto.CrmDTO;
import com.hengyi.japp.crm.dto.CustomerDTO;
import com.hengyi.japp.crm.dto.StorageDTO;

@Named
public class CrmFactoryImpl implements CrmFactory {

	@Override
	public Crm newCrm(CrmType crmType) {
		switch (crmType) {
		case CUSTOMER:
			return new Customer();
		case STORAGE:
			return new Storage();
		}
		return null;
	}

	@Override
	public CrmDTO newCrmDTO(CrmType crmType) {
		switch (crmType) {
		case CUSTOMER:
			return new CustomerDTO();
		case STORAGE:
			return new StorageDTO();
		}
		return null;
	}

	@Override
	public GraphRepository<? extends Crm> graphRepository(CrmType crmType) {
		// TODO Auto-generated method stub
		return null;
	}
}
