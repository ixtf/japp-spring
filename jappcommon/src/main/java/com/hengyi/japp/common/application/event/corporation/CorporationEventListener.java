package com.hengyi.japp.common.application.event.corporation;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import com.hengyi.japp.common.data.BindCorporationType;
import com.hengyi.japp.common.domain.node.bind.corporation.AbstractBindCorporation;
import com.hengyi.japp.common.domain.node.bind.corporation.HrCorporation;
import com.hengyi.japp.common.service.CorporationService;

@Transactional
@Component
public class CorporationEventListener implements
		ApplicationListener<CorporationEvent> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private CorporationService corporationService;

	@Override
	public void onApplicationEvent(CorporationEvent event) {
		CorporationEvent.Type type = event.getType();
		try {
			switch (type) {
			case OA_CORPORATION_IMPORTED:
				mapOaHrCorporation(BindCorporationType.OA1,
						"classpath:oa1_corporation.xls");
				mapOaHrCorporation(BindCorporationType.OA2,
						"classpath:oa2_corporation.xls");
				break;
			default:
				break;
			}
		} catch (BiffException e) {
			log.error("", e);
		} catch (IOException e) {
			log.error("", e);
		}
	}

	private void mapOaHrCorporation(BindCorporationType bindCorporationType,
			String filePath) throws BiffException, IOException {
		File file = ResourceUtils.getFile(filePath);
		jxl.Workbook workbook = Workbook.getWorkbook(file);
		jxl.Sheet sheet = workbook.getSheet(0);
		for (int i = 0; i < sheet.getRows(); i++) {
			String oa = sheet.getCell(0, i).getContents();
			String hr = sheet.getCell(1, i).getContents();
			HrCorporation hrCorporation = corporationService
					.findOneBindCorporation(BindCorporationType.HR, hr);
			if (hrCorporation == null)
				continue;
			AbstractBindCorporation oaCorporation = corporationService
					.findOneBindCorporation(bindCorporationType, oa);
			oaCorporation.setCorporation(hrCorporation.getCorporation());
			corporationService.save(oaCorporation);
		}
	}
}
