package com.hengyi.japp.foreign.domain.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.google.common.collect.ImmutableSet;
import com.hengyi.japp.common.sap.dto.VbapPK;
import com.hengyi.japp.foreign.domain.StockPrepare;
import com.hengyi.japp.foreign.domain.StockPrepareItem;
import com.hengyi.japp.foreign.domain.Vbap;

public interface VbapRepository extends JpaRepository<Vbap, VbapPK>,
		JpaSpecificationExecutor<Vbap> {

	class FindByStockPrepare implements Specification<Vbap> {
		private final Iterable<String> numbers;

		@Override
		public Predicate toPredicate(Root<Vbap> root, CriteriaQuery<?> query,
				CriteriaBuilder cb) {
			SetJoin<Vbap, StockPrepareItem> join = root
					.joinSet("stockPrepareItems");
			return join.<StockPrepare> get("stockPrepare")
					.<String> get("number").in(numbers);
		}

		public FindByStockPrepare(final Iterable<String> numbers) {
			super();
			this.numbers = numbers;
		}

		public FindByStockPrepare(final String number) {
			this(ImmutableSet.of(number));
		}

		public FindByStockPrepare(final StockPrepare stockPrepare) {
			this(ImmutableSet.of(stockPrepare.getNumber()));
		}

	}
}
