package com.hengyi.japp.crm.domain.customer;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.crm.domain.Indicator;

@NodeEntity
public class CustomerIndicator extends Indicator {
	private static final long serialVersionUID = 1470516342958682889L;

	public static final class Indicator {
		public static final String SALEINCOME = "saleIncome";
		public static final String REGISTERCAPITAL = "registerCapital";
		public static final String COUNTRY = "country";
		public static final String DURATIONYEARS = "durationYears";
		public static final String CRMPROPERTY = "crmProperty";
		public static final String QUALITYCERTIFICATION = "qualityCertification";
		public static final String BRAND = "brand";
		public static final String BUSINESSSTABILITY = "businessStability";
		public static final String STAFFQUALITY = "staffQuality";
		public static final String PRODUCTDEPENDENCE = "productDependence";
		public static final String CONTACTCOMPLIANCE = "contactCompliance";
		public static final String MARKETVISIBILITY = "marketVisibility";
		public static final String BUSINESSTREND = "businessTrend";
	}
}
