package com.hengyi.japp.crm.event.init;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
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
import com.hengyi.japp.crm.domain.Certificate;
import com.hengyi.japp.crm.domain.CrmType;
import com.hengyi.japp.crm.domain.Indicator;
import com.hengyi.japp.crm.domain.IndicatorValue;
import com.hengyi.japp.crm.domain.IndicatorValueScore;
import com.hengyi.japp.crm.domain.customer.CustomerIndicator;
import com.hengyi.japp.crm.domain.repository.IndicatorRepository;
import com.hengyi.japp.crm.domain.storage.StorageIndicator;
import com.hengyi.japp.crm.service.CertificateService;
import com.hengyi.japp.crm.service.CrmTypeService;
import com.hengyi.japp.crm.service.IndicatorValueService;
import com.hengyi.japp.crm.service.customer.CustomerIndicatorService;
import com.hengyi.japp.crm.service.storage.StorageIndicatorService;

@Named
@Singleton
@Transactional
public class AppInitListener implements ApplicationListener<AppInitEvent> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private IndicatorRepository indicatorRepository;
	@Inject
	private CustomerIndicatorService customerIndicatorService;
	@Inject
	private StorageIndicatorService storageIndicatorService;
	@Inject
	private IndicatorValueService indicatorValueService;
	@Inject
	private CrmTypeService crmTypeService;
	@Inject
	private CertificateService certificateService;

	@Override
	public void onApplicationEvent(AppInitEvent event) {
		if (indicatorRepository.count() > 0)
			return;
		try {
			log.info("=============开始初始化=============");
			initIndicator();
			initCrmType();
			initCertificate();
			log.info("=============开始完成=============");
		} catch (Exception e) {
			log.error("=============初始化失败=============", e);
		}
	}

	private void initIndicator() throws Exception {
		importFromXls();
		for (Indicator indicator : CustomerIndicator.getCalculateIndicators())
			indicatorRepository.save(indicator);
		for (Indicator indicator : StorageIndicator.getCalculateIndicators())
			indicatorRepository.save(indicator);
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
			indicatorValueService.save(indicatorValue);

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
			if (indicator instanceof CustomerIndicator)
				customerIndicatorService.save((CustomerIndicator) indicator,
						indicatorValueScores);
			else
				storageIndicatorService.save((StorageIndicator) indicator,
						indicatorValueScores);
		}
	}

	private void initCrmType() throws Exception {
		crmTypeService.save(new CrmType("国有企业"));
		crmTypeService.save(new CrmType("集体企业"));
		crmTypeService.save(new CrmType("有限责任公司"));
		crmTypeService.save(new CrmType("股份有限公司"));
		crmTypeService.save(new CrmType("私营企业"));
		crmTypeService.save(new CrmType("中外合资企业"));
		crmTypeService.save(new CrmType("外商投资企业"));
	}

	private void initCertificate() throws Exception {
		certificateService.save(new Certificate("组织机构代码证"));
		certificateService.save(new Certificate("税务登记证"));
		certificateService.save(new Certificate("营业执造"));
		certificateService.save(new Certificate("一般纳税人资格证"));
		certificateService.save(new Certificate("商业登记证"));
		certificateService.save(new Certificate("离岸账户证明"));
	}
}
