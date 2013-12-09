package com.hengyi.japp.common.service;

public interface CommonCrudNeo4jService<T> extends CommonCrudService<T, Long> {
	T findOne(Long id);
	// void delete(Long id) throws Exception;
}
