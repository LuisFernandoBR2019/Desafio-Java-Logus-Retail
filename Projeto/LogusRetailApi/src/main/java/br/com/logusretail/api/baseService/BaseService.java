package br.com.logusretail.api.baseService;

import java.util.List;
import java.util.Map;

public abstract interface BaseService<T> {
	List<T> readAll(Map<String, Object> criteria);

	Map<String,String> create(T entity);

	T readById(long id);

	boolean update(T entity);

	boolean deleteById(Long id);
}