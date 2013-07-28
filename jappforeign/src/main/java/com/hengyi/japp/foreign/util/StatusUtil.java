package com.hengyi.japp.foreign.util;

import java.util.List;

import com.google.common.collect.Lists;
import com.hengyi.japp.foreign.domain.data.Status;
import com.hengyi.japp.foreign.domain.data.Statusable;

public final class StatusUtil {
	@SafeVarargs
	public static Status getStatus(
			final Iterable<? extends Statusable>... array) {
		List<Status> statuss = Lists.newArrayList();
		for (Iterable<? extends Statusable> statusables : array)
			statuss.add(getStatus(statusables));
		return getStatus(statuss.toArray(new Status[] {}));
	}

	public static Status getStatus(final Iterable<? extends Statusable> i) {
		boolean allFinish = true;
		boolean nonFinish = true;
		for (Statusable statusable : i) {
			// statusable.updateStatus();
			Status status = statusable.getStatus();
			allFinish = allFinish && status.equals(Status.ALL_FINISH);
			nonFinish = nonFinish && status.equals(Status.INIT);
		}
		return getStatus(nonFinish, allFinish);
	}

	public static Status getStatus(Status... array) {
		boolean allFinish = true;
		boolean nonFinish = true;
		for (Status status : array) {
			allFinish = allFinish && status.equals(Status.ALL_FINISH);
			nonFinish = nonFinish && status.equals(Status.INIT);
		}
		return getStatus(nonFinish, allFinish);
	}

	public static Status getStatus(boolean nonFinish, boolean allFinish) {
		if (nonFinish)
			return Status.INIT;
		else if (allFinish)
			return Status.ALL_FINISH;
		else
			return Status.APART_FINISH;
	}
}
