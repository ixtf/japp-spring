package com.hengyi.japp.report.service;

import java.io.Serializable;
import java.util.List;

import com.hengyi.japp.report.domain.ReportMenu;

public interface ReportMenuService extends Serializable {
	List<ReportMenu> findAll();
}
