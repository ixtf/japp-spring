package com.hengyi.japp.common.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.ApplicationEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.google.common.collect.Lists;
import com.hengyi.japp.common.event.EventPublisher;

public abstract class AbstractCommonCrudService<T, ID extends Serializable> {

	public abstract <R extends Repository<T, ID>> R getRepository();

	public abstract String getNewPath();

	@Transactional
	public void publish(final EventPublisher eventPublisher,
			final ApplicationEvent event) {
		if (TransactionSynchronizationManager.isActualTransactionActive())
			TransactionSynchronizationManager
					.registerSynchronization(new TransactionSynchronizationAdapter() {
						@Override
						public void afterCommit() {
							eventPublisher.publish(event);
						}
					});
		else
			eventPublisher.publish(event);
	}

	public T findOne(ID id) {
		return id == null ? null : _crudRepository().findOne(id);
	}

	// public void save(T t) throws Exception {
	// _crudRepository().save(t);
	// }
	//
	// public void save(Iterable<T> ts) throws Exception {
	// _crudRepository().save(ts);
	// }
	//
	// public void delete(ID id) throws Exception {
	// _crudRepository().delete(id);
	// }
	//
	// public void delete(T t) throws Exception {
	// _crudRepository().delete(t);
	// }

	public List<T> findAll() {
		return Lists.newArrayList(_crudRepository().findAll());
	}

	public List<T> findAll(Pageable pageable) {
		return Lists.newArrayList(_pagingAndSortingRepository().findAll(
				pageable));
	}

	public long count() {
		return _crudRepository().count();
	}

	public String getUpdatePath(ID id) {
		return getManagePath() + "/" + id;
	}

	public String getNewView() {
		return getUpdateView();
	}

	public String getUpdatePath() {
		return getManagePath() + "/{id}";
	}

	public String getUpdateView() {
		return getViewPrefix() + "/update.jsf";
	}

	public String getManagePath() {
		return getNewPath() + "s";
	}

	public String getManageView() {
		return getViewPrefix() + "/list.jsf";
	}

	protected String getViewPrefix() {
		return "/faces" + getNewPath();
	}

	private CrudRepository<T, ID> _crudRepository() {
		return getRepository();
	}

	private PagingAndSortingRepository<T, ID> _pagingAndSortingRepository() {
		return getRepository();
	}
}
