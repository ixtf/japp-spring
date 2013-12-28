package com.hengyi.japp.crm.data;

import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.customer.Customer;
import com.hengyi.japp.crm.domain.storage.Storage;
import com.hengyi.japp.crm.dto.CrmDTO;
import com.hengyi.japp.crm.dto.CustomerDTO;
import com.hengyi.japp.crm.dto.StorageDTO;

public enum CrmType {
	CUSTOMER("客户") {
		@Override
		public Crm newEntity() {
			return new Customer();
		}

		@Override
		public CrmDTO newDTO() {
			return new CustomerDTO();
		}
	},
	STORAGE("仓储") {
		@Override
		public Crm newEntity() {
			return new Storage();
		}

		@Override
		public CrmDTO newDTO() {
			return new StorageDTO();
		}
	};
	private CrmType(String name) {
		this.name = name;
	}

	private final String name;

	public String getName() {
		return name;
	}

	public abstract Crm newEntity();

	public abstract CrmDTO newDTO();

	@Override
	public String toString() {
		return getName();
	}
}
