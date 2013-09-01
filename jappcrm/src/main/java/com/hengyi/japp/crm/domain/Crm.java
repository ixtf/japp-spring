package com.hengyi.japp.crm.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.Indexed.Level;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.Sets;
import com.hengyi.japp.common.domain.shared.AbstractNeo4j;

@NodeEntity
public abstract class Crm extends AbstractNeo4j implements Serializable {
	private static final long serialVersionUID = -2000360302877825388L;
	public static final String CRM_COMMUNICATEE = "CHIEF_COMMUNICATEE";
	public static final String CRM_COMMUNICATEES = "COMMUNICATEE";
	public static final String CRM_INDICATORVALUE = "INDICATOR_VALUE";
	@NotBlank
	@Indexed(level = Level.INSTANCE)
	protected String name;
	@NotBlank
	@Indexed(unique = true)
	protected String registerNumber;
	@NotBlank
	protected String registerPlace;
	@NotNull
	protected Date registerDate;
	@NotNull
	@Min(0)
	protected BigDecimal registerCapital;
	@NotBlank
	protected String represent;
	@NotBlank
	protected String addressName;
	@NotBlank
	@Pattern(regexp = "^[1-9][0-9]{5}$")
	protected String zipCode;
	@RelatedTo(type = CRM_COMMUNICATEE)
	@Fetch
	protected Communicatee communicatee;
	@RelatedTo(type = CRM_COMMUNICATEES, elementClass = Communicatee.class)
	protected Set<Communicatee> communicatees;
	@NotNull
	@Min(0)
	protected BigDecimal saleIncome;
	public static final String CRM_TYPE = "CRM_TYPE";
	@RelatedTo(type = CRM_TYPE)
	@Fetch
	protected CrmType crmType;
	@RelatedToVia(type = Associate.RELATIONSHIP, elementClass = Associate.class, direction = Direction.BOTH)
	protected Set<Associate> associates;
	@RelatedTo(type = CRM_INDICATORVALUE, elementClass = IndicatorValue.class)
	protected Set<IndicatorValue> indicatorValues;

	public int getDurationYears() {
		Date startDate = getRegisterDate();
		if (startDate == null)
			return 0;
		return DateTime.now().getYear() - new DateTime(startDate).getYear();
	}

	public Iterable<Associate> getAssociates() {
		if (associates == null)
			associates = Sets.newHashSet();
		return associates;
	}

	public Iterable<Associate> getAssociates(Neo4jOperations template) {
		return template.fetch(getAssociates());
	}

	public void setAssociates(Iterable<Associate> associates) {
		this.associates = Sets.newHashSet(associates);
	}

	public Iterable<IndicatorValue> getIndicatorValues() {
		if (indicatorValues == null)
			indicatorValues = Sets.newHashSet();
		return indicatorValues;
	}

	public Iterable<IndicatorValue> getIndicatorValues(Neo4jOperations template) {
		return template.fetch(getIndicatorValues());
	}

	public void setIndicatorValues(Iterable<IndicatorValue> indicatorValues) {
		this.indicatorValues = indicatorValues == null ? null : Sets
				.newHashSet(indicatorValues);
	}

	public BigDecimal getSaleIncome() {
		return saleIncome;
	}

	public void setSaleIncome(BigDecimal saleIncome) {
		this.saleIncome = saleIncome;
	}

	public Communicatee getCommunicatee() {
		return communicatee;
	}

	public Iterable<Communicatee> getCommunicatees() {
		if (communicatees == null)
			communicatees = Sets.newHashSet();
		return communicatees;
	}

	public Iterable<Communicatee> getCommunicatees(Neo4jOperations template) {
		return template.fetch(getCommunicatees());
	}

	public void setCommunicatee(Communicatee communicatee) {
		this.communicatee = communicatee;
	}

	public void setCommunicatees(Iterable<Communicatee> communicatees) {
		this.communicatees = Sets.newHashSet(communicatees);
	}

	public CrmType getCrmType() {
		return crmType;
	}

	public void setCrmType(CrmType crmType) {
		this.crmType = crmType;
	}

	public String getName() {
		return name;
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public String getRegisterPlace() {
		return registerPlace;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public BigDecimal getRegisterCapital() {
		return registerCapital;
	}

	public String getRepresent() {
		return represent;
	}

	public String getAddressName() {
		return addressName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setName(String name) {
		this.name = StringUtils.trim(name);
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = StringUtils.trim(registerNumber);
	}

	public void setRegisterPlace(String registerPlace) {
		this.registerPlace = StringUtils.trim(registerPlace);
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public void setRegisterCapital(BigDecimal registerCapital) {
		this.registerCapital = registerCapital;
	}

	public void setRepresent(String represent) {
		this.represent = StringUtils.trim(represent);
	}

	public void setAddressName(String addressName) {
		this.addressName = StringUtils.trim(addressName);
	}

	public void setZipCode(String zipCode) {
		this.zipCode = StringUtils.trim(zipCode);
	}

	@Override
	public String toString() {
		return getName();
	}
}
