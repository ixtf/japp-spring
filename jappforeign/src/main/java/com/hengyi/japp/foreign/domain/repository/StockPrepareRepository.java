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

public interface StockPrepareRepository extends
		JpaRepository<StockPrepare, String>,
		JpaSpecificationExecutor<StockPrepare> {

	class FindByVbeln implements Specification<StockPrepare> {
		private final Iterable<String> vbelns;

		@Override
		public Predicate toPredicate(Root<StockPrepare> root,
				CriteriaQuery<?> query, CriteriaBuilder cb) {
			SetJoin<StockPrepare, StockPrepareItem> join = root
					.joinSet("items");
			return join.<Vbap> get("vbap").<VbapPK> get("pk")
					.<String> get("vbeln").in(vbelns);
		}

		public FindByVbeln(Iterable<String> vbelns) {
			super();
			this.vbelns = vbelns;
		}

		public FindByVbeln(String vbeln) {
			this(ImmutableSet.of(vbeln));
		}

	}

	class FindByVbap implements Specification<StockPrepare> {
		private final VbapPK pk;

		@Override
		public Predicate toPredicate(Root<StockPrepare> root,
				CriteriaQuery<?> query, CriteriaBuilder cb) {
			SetJoin<StockPrepare, StockPrepareItem> join = root
					.joinSet("items");
			return cb.equal(join.<Vbap> get("vbap").<VbapPK> get("pk"), pk);
		}

		public FindByVbap(VbapPK pk) {
			super();
			this.pk = pk;
		}

		public FindByVbap(Vbap vbap) {
			this(vbap.getPk());
		}

	}
}
