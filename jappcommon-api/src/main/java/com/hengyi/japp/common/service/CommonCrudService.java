package com.hengyi.japp.common.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface CommonCrudService<T, ID extends Serializable> {
	T findOne(ID id);

	void save(T t) throws Exception;

	void save(Iterable<T> ts) throws Exception;

	void delete(ID id) throws Exception;

	void delete(T t) throws Exception;

	List<T> findAll();

	List<T> findAll(Pageable pageRequest);

	long count();

	String getNewPath();

	String getNewView();

	String getUpdatePath();

	String getUpdatePath(ID id);

	String getUpdateView();

	String getManagePath();

	String getManageView();

	<RP extends Repository<T, ID>> RP getRepository();
}
