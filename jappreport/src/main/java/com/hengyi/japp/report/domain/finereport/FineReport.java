package com.hengyi.japp.report.domain.finereport;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.hengyi.japp.report.domain.Report;

//lin0878950
@NodeEntity
public class FineReport extends Report {
	private static final long serialVersionUID = -5182667801659880331L;
	@Indexed(unique = true)
	private String cpt;
	// 默认，分析，填报
	@NotNull
	private OpType opType;
	// 默认，横不分页，纵分页
	@NotNull
	private Boolean bypagesize;
	@NotNull
	private Boolean pi;
	@NotNull
	private Boolean showtoolbar;
	// TODO 缺少备注
	private String cutpage;

	public String getParms() {
		StringBuilder sb = new StringBuilder(getCpt());
		if (!getBypagesize())
			sb.append("&__bypagesize__=").append(bypagesize);
		if (!OpType.DEFAULT.equals(getOpType()))
			sb.append("&op=").append(getOpType().getValue());
		if (!getPi())
			sb.append("&__pi__=").append(getPi());
		if (!getShowtoolbar())
			sb.append("&__showtoolbar__=").append(getShowtoolbar());
		if (getCutpage() != null)
			sb.append("&__cutpage__=").append("v");
		return sb.toString();
	}

	@NotBlank
	public String getCpt() {
		return cpt;
	}

	public void setCpt(String cpt) {
		this.cpt = StringUtils.trim(cpt);
	}

	public Boolean getBypagesize() {
		if (bypagesize == null)
			bypagesize = true;
		return bypagesize;
	}

	public void setBypagesize(Boolean bypagesize) {
		this.bypagesize = bypagesize;
	}

	public OpType getOpType() {
		if (opType == null)
			opType = OpType.DEFAULT;
		return opType;
	}

	public void setOpType(OpType opType) {
		this.opType = opType;
	}

	public Boolean getPi() {
		if (pi == null)
			pi = true;
		return pi;
	}

	public void setPi(Boolean pi) {
		this.pi = pi;
	}

	public Boolean getShowtoolbar() {
		if (showtoolbar == null)
			showtoolbar = true;
		return showtoolbar;
	}

	public void setShowtoolbar(Boolean showtoolbar) {
		this.showtoolbar = showtoolbar;
	}

	public String getCutpage() {
		return cutpage;
	}

	public void setCutpage(String cutpage) {
		this.cutpage = cutpage;
	}
}
