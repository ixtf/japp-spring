package com.hengyi.japp.common.sap.dto;

import java.util.Collection;

public class RangeTableDTO<T> {
	private Collection<RangeDTO<T>> ranges;

	public Collection<RangeDTO<T>> getRanges() {
		return ranges;
	}

	public void setRanges(Collection<RangeDTO<T>> ranges) {
		this.ranges = ranges;
	}
}
