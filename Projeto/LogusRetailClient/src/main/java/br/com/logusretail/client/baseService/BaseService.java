package br.com.logusretail.client.baseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BaseService<T> {
	List<T> readAll(Map<String, Object> criteria);

	Map<String,String> create(T entity);

	T readById(Long id);

	boolean update(T entity);

	boolean deleteById(Long id);
}