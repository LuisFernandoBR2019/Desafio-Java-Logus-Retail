package br.com.logusretail.api.entitiesService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.logusretail.api.service.ConsultorioService;
import br.com.logusretail.database.entitiesDao.ConsultorioDao;
import br.com.logusretail.model.entities.Consultorio;

@Service
public class ConsultorioServiceDao implements ConsultorioService{

	@Autowired
	private ConsultorioDao consultorioDao;
	
	@Override
	public List<Consultorio> readAll(Map<String, Object> criteria) {
		return consultorioDao.readAll(criteria);
	}

	@Override
	public Map<String,String> create(Consultorio entity) {
		return consultorioDao.create(entity);
	}

	@Override
	public Consultorio readById(long id) {
		return consultorioDao.readById(id);
	}

	@Override
	public boolean update(Consultorio entity) {
		return consultorioDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		return consultorioDao.deleteById(id);
	}

}
