package com.hengyi.japp.crm.domain.storage;

import java.util.List;

import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.Lists;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.Indicator;

@NodeEntity
public class StorageIndicator extends Indicator {
	private static final long serialVersionUID = 8640666656378277929L;

	public static final List<StorageIndicator> getCalculateIndicators() {
		List<StorageIndicator> result = Lists.newArrayList();
		result.add(new StorageIndicatorDurationYears());
		result.add(new StorageIndicatorRegisterCapcital());
		result.add(new StorageIndicatorCapcital());
		return result;
	}

	public StorageIndicator() {
		super();
	}

	public StorageIndicator(String name, double percent) {
		super(name, percent);
	}

	@Override
	protected double calculateScoreByCalculate(Crm crm, Neo4jOperations template)
			throws Exception {
		throw new UnsupportedOperationException();
	}

	public static final class Indicator {
		public static final String REGISTERCAPITAL = "registerCapital";
		public static final String COUNTRY = "country";
		public static final String DURATIONYEARS = "durationYears";
		public static final String CRMPROPERTY = "crmProperty";
		public static final String BUSINESSSTABILITY = "businessStability";
		public static final String STAFFQUALITY = "staffQuality";
		public static final String STORAGECAPACITY = "storageCapacity";
		public static final String PRODUCTDEPENDENCE = "productDependence";
		public static final String MARKETVISIBILITY = "marketVisibility";
		public static final String BUSINESSTREND = "businessTrend";
		public static final String STORAGEMANAGE = "storageManage";
		public static final String STORAGEBASICSUPPORTING = "storageBasicSupporting";
		public static final String STORAGEPRODUCTCIRCULATION = "storageProductCirculation";
	}
}
