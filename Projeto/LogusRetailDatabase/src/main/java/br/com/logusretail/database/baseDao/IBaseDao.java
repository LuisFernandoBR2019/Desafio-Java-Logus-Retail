package br.com.logusretail.database.baseDao;

import java.util.List;
import java.util.Map;

public interface IBaseDao<T> {
	List<T> readAll(Map<String, Object> criteria);

	Map<String,String> create(T entity);

	T readById(long id);

	boolean update(T entity);

	boolean deleteById(Long id);
}
