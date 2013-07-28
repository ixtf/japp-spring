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
import com.hengyi.japp.foreign.domain.CreditPost;
import com.hengyi.japp.foreign.domain.Vbak;

public interface VbakRepository extends JpaRepository<Vbak, String>,
		JpaSpecificationExecutor<Vbak> {

	class FindByCrediPost implements Specification<Vbak> {
		private final Iterable<String> numbers;

		@Override
		public Predicate toPredicate(Root<Vbak> root, CriteriaQuery<?> query,
				CriteriaBuilder cb) {
			SetJoin<Vbak, CreditPost> join = root.joinSet("creditPosts");
			return join.<String> get("number").in(numbers);
		}

		public FindByCrediPost(final Iterable<String> numbers) {
			super();
			this.numbers = numbers;
		}

		public FindByCrediPost(final String number) {
			this(ImmutableSet.of(number));
		}

		public FindByCrediPost(final CreditPost creditPost) {
			this(ImmutableSet.of(creditPost.getNumber()));
		}

	}
}
