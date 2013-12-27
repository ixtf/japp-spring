package com.hengyi.japp.crm.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hengyi.japp.common.service.AbstractCommonCrudNeo4jService;
import com.hengyi.japp.crm.data.CrmFieldType;
import com.hengyi.japp.crm.domain.Associate;
import com.hengyi.japp.crm.domain.Certificate;
import com.hengyi.japp.crm.domain.Communicatee;
import com.hengyi.japp.crm.domain.CorporationType;
import com.hengyi.japp.crm.domain.Crm;
import com.hengyi.japp.crm.domain.CrmField;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValue;
import com.hengyi.japp.crm.domain.IndicatorValueScore;
import com.hengyi.japp.crm.domain.UploadFile;
import com.hengyi.japp.crm.domain.repository.CrmFieldRepository;
import com.hengyi.japp.crm.domain.repository.CrmRepository;
import com.hengyi.japp.crm.domain.repository.UploadFileRepository;
import com.hengyi.japp.crm.event.publisher.EventPublisher;
import com.hengyi.japp.crm.event.publisher.SyncEventPublisher;
import com.hengyi.japp.crm.service.CacheService;
import com.hengyi.japp.crm.service.CrmService;
import com.hengyi.japp.crm.service.SapService;

public abstract class CrmServiceImpl<CRM extends Crm> extends
		AbstractCommonCrudNeo4jService<CRM> implements CrmService<CRM> {
	@Inject
	private Neo4jTemplate template;
	@Inject
	private CrmRepository crmRepository;
	@Inject
	protected CrmFieldRepository crmFieldRepository;
	@Inject
	protected UploadFileRepository uploadFileRepository;
	@Inject
	protected EventPublisher eventPublisher;
	@Inject
	protected SyncEventPublisher syncEventPublisher;
	@Inject
	protected CacheService cacheService;
	@Inject
	protected SapService sapService;

	// @Inject
	// private EventBus eventBus;

	public void save(CRM crm,
			Map<Indicator, List<IndicatorValueScore>> indicatorMap,
			Iterable<CorporationType> corporationTypes,
			Iterable<Certificate> certificates, Communicatee communicatee,
			Iterable<Communicatee> communicatees, Iterable<Associate> associates)
			throws Exception {
		checkSave(crm, indicatorMap, corporationTypes, certificates,
				communicatee, communicatees, associates);

		crm.setKunnr(sapService.convertKunnr(crm.getKunnr()));
		crm.setLifnr(sapService.convertLifnr(crm.getLifnr()));

		crm.setCorporationTypes(corporationTypes);
		crm.setCertificates(certificates);
		crm.setCommunicatee(communicatee);
		crm.setCommunicatees(communicatees);
		crm.setAssociates(associates);
		Set<IndicatorValue> indicatorValues = Sets.newHashSet();
		for (Entry<Indicator, List<IndicatorValueScore>> entry : indicatorMap
				.entrySet())
			for (IndicatorValueScore indicatorValueScore : entry.getValue())
				indicatorValues.add(indicatorValueScore.getEnd());
		crm.setIndicatorValues(indicatorValues);
		crmRepository.save(crm);
		for (Associate associate : associates)
			template.save(associate);

		// for (Indicator indicator : findAllIndicator())
		// crm.indicatorScore(template, indicator);
	}

	private void checkSave(CRM crm,
			Map<Indicator, List<IndicatorValueScore>> indicatorMap,
			Iterable<CorporationType> crmTypes,
			Iterable<Certificate> certificates, Communicatee communicatee,
			Iterable<Communicatee> communicatees, Iterable<Associate> associates)
			throws Exception {
		// if (communicatee == null)
		// throw new NoChiefCommunicateeException();

		// List<Indicator> noValueIndicators = Lists.newArrayList();
		// for (Entry<Indicator, List<IndicatorValueScore>> entry : indicatorMap
		// .entrySet())
		// if (entry.getValue().isEmpty())
		// noValueIndicators.add(entry.getKey());
		// if (!noValueIndicators.isEmpty())
		// throw new NoIndicatorValueException(noValueIndicators);
	}

	// 取Crm已经选中的indicatorValue，在和indicator关联的indicatorValue中选
	public Map<Indicator, List<IndicatorValueScore>> getIndicatorMap(CRM crm,
			Iterable<Indicator> indicators) {
		ImmutableMap.Builder<Indicator, List<IndicatorValueScore>> builder = ImmutableMap
				.builder();
		Set<IndicatorValue> indicatorValues = ImmutableSet.copyOf(crm
				.getIndicatorValues(template));
		for (Indicator indicator : indicators)
			builder.put(indicator,
					getIndicatorValueScores(indicator, indicatorValues));
		return builder.build();
	}

	private List<IndicatorValueScore> getIndicatorValueScores(
			Indicator indicator, Set<IndicatorValue> indicatorValues) {
		List<IndicatorValueScore> result = Lists.newArrayList();
		for (IndicatorValueScore indicatorValueScore : indicator
				.getIndicatorValueScores())
			if (indicatorValues.contains(indicatorValueScore.getEnd()))
				result.add(indicatorValueScore);
		return result;
	}

	@Override
	public List<UploadFile> findAllUploadFile(Crm crm) {
		return Lists.newArrayList(uploadFileRepository.findAllByCrm(crm));
	}

	@Override
	public void removeUploadFile(UploadFile uploadFile) {
		uploadFileRepository.delete(uploadFile);
	}

	@Override
	public List<CrmField> findAllCrmField(CrmFieldType crmFieldType) {
		return Lists.newArrayList(crmFieldRepository.findAllByPropertyValue(
				"crmFieldType", crmFieldType));
	}
}
