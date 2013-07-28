package com.hengyi.japp.foreign.domain.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.google.common.collect.ImmutableSet;
import com.hengyi.japp.foreign.domain.Invoice;
import com.hengyi.japp.foreign.domain.Likp;

public interface LikpRepository extends JpaRepository<Likp, String>,
		JpaSpecificationExecutor<Likp> {

	class FindByInvoice implements Specification<Likp> {
		private final Iterable<String> numbers;

		@Override
		public Predicate toPredicate(Root<Likp> root, CriteriaQuery<?> query,
				CriteriaBuilder cb) {
			return root.<Invoice> get("invoice").<String> get("number")
					.in(numbers);
		}

		public FindByInvoice(final Iterable<String> numbers) {
			super();
			this.numbers = numbers;
		}

		public FindByInvoice(final String number) {
			this(ImmutableSet.of(number));
		}

		public FindByInvoice(final Invoice invoice) {
			this(ImmutableSet.of(invoice.getNumber()));
		}

	}
}
