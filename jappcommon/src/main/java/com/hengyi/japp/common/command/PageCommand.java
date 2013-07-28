package com.hengyi.japp.common.command;

import org.springframework.data.domain.PageRequest;

public class PageCommand extends PageRequest {
	private static final long serialVersionUID = 5640600492749543726L;
	private Long total;

	public PageCommand(int page, int size) {
		super(page, size);
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
