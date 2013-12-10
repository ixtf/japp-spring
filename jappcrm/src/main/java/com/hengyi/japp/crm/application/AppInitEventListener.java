package com.hengyi.japp.crm.application;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import jxl.Workbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.hengyi.japp.crm.data.CrmFieldType;
import com.hengyi.japp.crm.domain.Certificate;
import com.hengyi.japp.crm.domain.CorporationType;
import com.hengyi.japp.crm.domain.CrmField;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValue;
import com.hengyi.japp.crm.domain.IndicatorValueScore;
import com.hengyi.japp.crm.domain.customer.CustomerIndicator;
import com.hengyi.japp.crm.domain.repository.CertificateRepository;
import com.hengyi.japp.crm.domain.repository.CorporationTypeRepository;
import com.hengyi.japp.crm.domain.repository.CrmFieldRepository;
import com.hengyi.japp.crm.domain.repository.CrmRepository;
import com.hengyi.japp.crm.domain.repository.IndicatorRepository;
import com.hengyi.japp.crm.domain.repository.IndicatorValueRepository;
import com.hengyi.japp.crm.domain.storage.StorageIndicator;
import com.hengyi.japp.crm.event.AppInitEvent;

@Named
@Singleton
@Transactional
public class AppInitEventListener implements ApplicationListener<AppInitEvent> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Inject
	private IndicatorRepository indicatorRepository;
	@Inject
	private CrmRepository crmRepository;
	@Inject
	private IndicatorValueRepository indicatorValueRepository;
	@Inject
	private CorporationTypeRepository corporationTypeRepository;
	@Inject
	private CertificateRepository certificateRepository;
	@Inject
	private CrmFieldRepository crmFieldRepository;

	@Override
	public void onApplicationEvent(AppInitEvent event) {
		if (indicatorRepository.count() > 0)
			return;
		try {
			System.out.println("=============初始化开始=============");
			initIndicator();
			initCrmType();
			initCertificate();
			System.out.println("=============初始化完成=============");
		} catch (Exception e) {
			log.error("=============初始化失败=============", e);
		}
	}

	private void initIndicator() throws Exception {
		importFromXls();
		List<CrmField> crmFileds = Lists.newArrayList();
		List<Indicator> indicators = Lists.newArrayList();
		CrmField crmField = new CrmField("durationYears");
		crmFileds.add(crmField);
		indicators.add(new CustomerIndicator("已经营年限", 0.06, crmField));
		indicators.add(new StorageIndicator("已经营年限", 0.04, crmField));
		crmField = new CrmField("registerCapcital");
		crmFileds.add(crmField);
		indicators.add(new CustomerIndicator("注册资本", 0.12, crmField));
		indicators.add(new StorageIndicator("注册资本", 0.1, crmField));
		crmField = new CrmField("saleIncome");
		crmFileds.add(crmField);
		indicators.add(new CustomerIndicator("销售收入", 0.12, crmField));
		crmField = new CrmField(CrmFieldType.STORAGE, "capcital");
		crmFileds.add(crmField);
		indicators.add(new CustomerIndicator("仓储容量", 0.08, crmField));
		crmFileds.add(new CrmField("name"));
		crmFileds.add(new CrmField("registerNumber"));
		crmFileds.add(new CrmField("registerPlace"));
		crmFileds.add(new CrmField("registerDate"));
		crmFileds.add(new CrmField("represent"));
		crmFileds.add(new CrmField("addressName", "address"));
		crmFileds.add(new CrmField("communicatee", "chiefCommunicatee"));
		crmFileds.add(new CrmField("communicatees", "communicatee"));
		crmFileds.add(new CrmField("associates", "associate"));
		crmFileds.add(new CrmField("certificates", "certificate"));
		crmFileds.add(new CrmField("zipCode"));
		crmFileds.add(new CrmField("crmType"));
		crmFileds.add(new CrmField(CrmFieldType.CUSTOMER, "mainBusiness"));
		crmFileds.add(new CrmField(CrmFieldType.CUSTOMER, "coBusiness"));
		crmFieldRepository.save(crmFileds);
		indicatorRepository.save(indicators);
	}

	private void importFromXls() throws Exception {
		Map<String, Indicator> indicatorMap = Maps.newHashMap();
		Multimap<String, IndicatorValueScore> indicatorValueScoreMap = ArrayListMultimap
				.create();
		File file = ResourceUtils.getFile("classpath:indicators.xls");
		jxl.Workbook workbook = Workbook.getWorkbook(file);
		jxl.Sheet sheet = workbook.getSheet(0);
		for (int i = 1; i < sheet.getRows(); i++) {
			String indicatorClass = sheet.getCell(0, i).getContents();
			String indicatorName = sheet.getCell(1, i).getContents();
			String indicatorPercent = sheet.getCell(2, i).getContents();
			String indicatorValueName = sheet.getCell(3, i).getContents();
			String indicatorValueNote = sheet.getCell(4, i).getContents();
			String score = sheet.getCell(5, i).getContents();
			String key = indicatorClass + indicatorName;

			Indicator indicator = (Indicator) Class.forName(indicatorClass)
					.newInstance();
			indicator.setName(indicatorName);
			indicator.setPercent(Double.valueOf(indicatorPercent));
			indicatorMap.put(key, indicator);

			IndicatorValue indicatorValue = new IndicatorValue();
			indicatorValue.setName(indicatorValueName);
			indicatorValue.setNote(indicatorValueNote);
			indicatorValueRepository.save(indicatorValue);

			IndicatorValueScore indicatorValueScore = new IndicatorValueScore();
			indicatorValueScore.setEnd(indicatorValue);
			indicatorValueScore.setScore(Double.valueOf(score));
			indicatorValueScoreMap.put(key, indicatorValueScore);
		}
		indicatorRepository.save(indicatorMap.values());
		for (Entry<String, Indicator> entry : indicatorMap.entrySet()) {
			Indicator indicator = entry.getValue();
			List<IndicatorValueScore> indicatorValueScores = Lists
					.newArrayList();
			for (IndicatorValueScore indicatorValueScore : indicatorValueScoreMap
					.get(entry.getKey())) {
				indicatorValueScore.setStart(indicator);
				indicatorValueScores.add(indicatorValueScore);
			}
			indicator.setIndicatorValueScores(indicatorValueScores);
			indicatorRepository.save(indicator);
		}
	}

	private void initCrmType() throws Exception {
		List<CorporationType> list = Lists.newArrayList();
		list.add(new CorporationType("国有企业"));
		list.add(new CorporationType("集体企业"));
		list.add(new CorporationType("有限责任公司"));
		list.add(new CorporationType("股份有限公司"));
		list.add(new CorporationType("私营企业"));
		list.add(new CorporationType("中外合资企业"));
		list.add(new CorporationType("外商投资企业"));
		corporationTypeRepository.save(list);
	}

	private void initCertificate() throws Exception {
		List<Certificate> list = Lists.newArrayList();
		list.add(new Certificate("组织机构代码证"));
		list.add(new Certificate("税务登记证"));
		list.add(new Certificate("营业执造"));
		list.add(new Certificate("一般纳税人资格证"));
		list.add(new Certificate("商业登记证"));
		list.add(new Certificate("离岸账户证明"));
		certificateRepository.save(list);
	}
}
