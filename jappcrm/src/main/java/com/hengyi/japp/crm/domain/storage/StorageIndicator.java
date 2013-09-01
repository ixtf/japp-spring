package com.hengyi.japp.crm.domain.storage;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.crm.domain.Indicator;

@NodeEntity
public class StorageIndicator extends Indicator {
	private static final long serialVersionUID = 8640666656378277929L;

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
