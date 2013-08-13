package com.hengyi.japp.crm.domain.node;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

import com.hengyi.japp.common.domain.shared.AbstractNeo4j;
import com.hengyi.japp.crm.domain.node.customer.indicator.Brand;
import com.hengyi.japp.crm.domain.node.customer.indicator.BusinessTrend;
import com.hengyi.japp.crm.domain.node.customer.indicator.ContactRate;
import com.hengyi.japp.crm.domain.node.customer.indicator.Country;
import com.hengyi.japp.crm.domain.node.customer.indicator.MarketVisibility;
import com.hengyi.japp.crm.domain.node.customer.indicator.OperateStability;
import com.hengyi.japp.crm.domain.node.customer.indicator.Propety;
import com.hengyi.japp.crm.domain.node.customer.indicator.QualityCertification;
import com.hengyi.japp.crm.domain.node.customer.indicator.StaffQuality;
import com.hengyi.japp.crm.domain.node.customer.indicator.Type;

@NodeEntity
public abstract class AbstractCrm extends AbstractNeo4j {
	public static final String NAME_SEARCH = "crmNameSearch";
	public static final String REGISTER_PLACE_SEARCH = "registerPlaceSearch";
	public static final String CRM_COUNTRY = "COUNTRY";
	public static final String CRM_CHIEF_COMMUNICATION = "CHIEF_COMMUNICATION";
	public static final String CRM_COMMUNICATION = "COMMUNICATION";
	public static final String CRM_TYPE = "CRM_TYPE";
	public static final String CRM_PROPETY = "CRM_PROPETY";
	public static final String CRM_QUALITY_CERTIFICATION = "QUALITY_CERTIFICATION";
	public static final String CRM_BRAND = "BRAND";
	public static final String CRM_OPERATE_STABILITY = "OPERATE_STABILITY";
	public static final String CRM_STAFF_QUALITY = "STAFF_QUALITY";
	public static final String CRM_CONTACT_RATE = "CONTACT_RATE";
	public static final String CRM_MARKET_VISIBILITY = "MARKET_VISIBILITY";
	public static final String CRM_BUSINESS_TREND = "BUSINESS_TREND";

	@NotBlank
	@Indexed(indexType = IndexType.FULLTEXT, indexName = NAME_SEARCH)
	protected String name;
	@RelatedTo(type = CRM_COUNTRY)
	@Fetch
	protected Country country;
	@NotBlank
	@Indexed(unique = true)
	protected String registerNumber;
	@Indexed(indexType = IndexType.FULLTEXT, indexName = REGISTER_PLACE_SEARCH)
	protected String registerPlace;
	@NotNull
	protected Date registerDate;
	@NotNull
	protected BigDecimal registerCapital;
	@NotBlank
	protected String represent;
	@NotBlank
	protected String addressName;
	@NotBlank
	protected String zipCode;
	@RelatedTo(type = CRM_CHIEF_COMMUNICATION)
	@Fetch
	protected CommunicateInfo communicateInfo;
	@RelatedTo(type = CRM_COMMUNICATION, elementClass = CommunicateInfo.class)
	@Fetch
	protected Set<CommunicateInfo> communicateInfos;
	@RelatedTo(type = CRM_TYPE)
	@Fetch
	protected Type crmType;
	@RelatedTo(type = CRM_PROPETY)
	@Fetch
	protected Set<Propety> crmPropeties;
	@NotBlank
	protected String mainBusiness;
	@NotBlank
	protected String coBusiness;
	@NotNull
	protected BigDecimal saleIncome;
	@RelatedTo(type = CRM_QUALITY_CERTIFICATION)
	@Fetch
	protected QualityCertification qualityCertification;
	@RelatedTo(type = CRM_BRAND)
	@Fetch
	protected Brand brand;
	@RelatedTo(type = CRM_OPERATE_STABILITY)
	@Fetch
	protected OperateStability operateStability;
	@RelatedTo(type = CRM_STAFF_QUALITY)
	@Fetch
	protected StaffQuality staffQuality;
	protected int interDependencyRate;
	@RelatedTo(type = CRM_CONTACT_RATE)
	@Fetch
	protected ContactRate contactRate;
	@RelatedTo(type = CRM_MARKET_VISIBILITY)
	@Fetch
	protected MarketVisibility marketVisibility;
	@RelatedTo(type = CRM_BUSINESS_TREND)
	@Fetch
	protected BusinessTrend businessTrend;

}
