package com.hengyi.japp.crm.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

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
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.template.Neo4jOperations;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@NodeEntity
public abstract class Crm extends Modifiable {
	private static final long serialVersionUID = -2000360302877825388L;
	// public static final String FIELD_SALEINCOME = "saleIncome";
	// public static final String FIELD_REGISTERCAPITAL = "registerCapital";
	// public static final String FIELD_DURATIONYEARS = "durationYears";

	public static final String CORPORATION_TYPE = "CORPORATION_TYPE";
	public static final String CERTIFICATE = "CERTIFICATE";
	public static final String COMMUNICATEE = "CHIEF_COMMUNICATEE";
	public static final String COMMUNICATEES = "COMMUNICATEE";
	public static final String INDICATORVALUE = "INDICATOR_VALUE";
	public static final String CRMFILE = "CRMFILE";
	@NotBlank
	@Indexed(level = Level.INSTANCE)
	protected String name;
	@NotBlank
	@Indexed(unique = true)
	protected String registerNumber;
	@NotBlank
	protected String registerPlace;
	@NotNull
	@Past
	protected Date registerDate;
	@NotNull
	@Future
	protected Date registerInvalidDate;
	@NotNull
	@Min(0)
	protected BigDecimal registerCapital;
	@NotBlank
	protected String represent;
	@NotBlank
	protected String addressName;
	protected String lifnr;
	protected String kunnr;
	// @Pattern(regexp = "^[1-9][0-9]{5}$")
	protected String zipCode;
	@NotNull
	@Min(0)
	protected BigDecimal saleIncome;
	@RelatedTo(type = CORPORATION_TYPE, elementClass = CorporationType.class)
	@Fetch
	protected Set<CorporationType> corporationTypes;
	@RelatedTo(type = CERTIFICATE, elementClass = Certificate.class)
	@Fetch
	protected Set<Certificate> certificates;
	@RelatedTo(type = COMMUNICATEE)
	@Fetch
	protected Communicatee communicatee;
	@RelatedTo(type = COMMUNICATEES, elementClass = Communicatee.class)
	protected Set<Communicatee> communicatees;
	@RelatedToVia(type = Associate.RELATIONSHIP, elementClass = Associate.class, direction = Direction.BOTH)
	@Fetch
	protected Set<Associate> associates;
	@RelatedTo(type = INDICATORVALUE, elementClass = IndicatorValue.class)
	protected Set<IndicatorValue> indicatorValues;
	@RelatedToVia(type = IndicatorScore.RELATIONSHIP, elementClass = IndicatorScore.class)
	protected Set<IndicatorScore> indicatorScores;

	// @RelatedTo(type = UPLOADFILE, elementClass = UploadFile.class)
	// protected Set<UploadFile> uploadFiles;

	public void indicatorScore(Neo4jTemplate template, Indicator indicator) {
		IndicatorScore indicatorScore = template.createRelationshipBetween(
				this, indicator, IndicatorScore.class,
				IndicatorScore.RELATIONSHIP, false);
		indicatorScore.setScore(indicator.calculateScorePercent(this));
		template.save(indicatorScore);
	}

	public int getDurationYears() {
		return DateTime.now().getYear()
				- new DateTime(getRegisterDate()).getYear();
	}

	public Iterable<Associate> getAssociates() {
		if (associates == null)
			associates = Sets.newHashSet();
		for (Associate associate : associates) {
			Crm end = associate.getAssociate(this);
			associate.setEnd(end);
			associate.setStart(this);
		}
		return associates;
	}

	public List<Associate> getAssociatesAsList() {
		return Lists.newArrayList(getAssociates());
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

	public Iterable<IndicatorScore> getIndicatorScores() {
		if (indicatorScores == null)
			indicatorScores = Sets.newHashSet();
		return indicatorScores;
	}

	public Iterable<IndicatorScore> getIndicatorScores(Neo4jOperations template) {
		return template.fetch(getIndicatorScores());
	}

	public void setIndicatorScores(Iterable<IndicatorScore> indicatorScores) {
		this.indicatorScores = indicatorScores == null ? null : Sets
				.newHashSet(indicatorScores);
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

	public Iterable<CorporationType> getCorporationTypes() {
		if (corporationTypes == null)
			corporationTypes = Sets.newHashSet();
		return corporationTypes;
	}

	public Iterable<Certificate> getCertificates() {
		if (certificates == null)
			certificates = Sets.newHashSet();
		return certificates;
	}

	public void setCorporationTypes(Iterable<CorporationType> corporationTypes) {
		this.corporationTypes = Sets.newHashSet(corporationTypes);
	}

	public void setCertificates(Iterable<Certificate> certificates) {
		this.certificates = Sets.newHashSet(certificates);
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

	public String getLifnr() {
		return lifnr;
	}

	public void setLifnr(String lifnr) {
		this.lifnr = StringUtils.trim(lifnr);
	}

	public String getKunnr() {
		return kunnr;
	}

	public void setKunnr(String kunnr) {
		this.kunnr = StringUtils.trim(kunnr);
	}

	public void setZipCode(String zipCode) {
		this.zipCode = StringUtils.trim(zipCode);
	}

	public Date getRegisterInvalidDate() {
		return registerInvalidDate;
	}

	public void setRegisterInvalidDate(Date registerInvalidDate) {
		this.registerInvalidDate = registerInvalidDate;
	}

	// public Iterable<UploadFile> getUploadFiles() {
	// if (uploadFiles == null)
	// uploadFiles = Sets.newHashSet();
	// return uploadFiles;
	// }
	//
	// public Iterable<UploadFile> getUploadFiles(Neo4jOperations template) {
	// return template.fetch(getUploadFiles());
	// }
	//
	// public void setUploadFiles(Iterable<UploadFile> uploadFiles) {
	// this.uploadFiles = Sets.newHashSet(uploadFiles);
	// }

	@Override
	public String toString() {
		return getName();
	}
}
