package br.com.logusretail.api.entitiesService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.logusretail.api.service.ConsultaService;
import br.com.logusretail.database.entitiesDao.ConsultaDao;
import br.com.logusretail.model.entities.Consulta;

@Service
public class ConsultaServiceDao implements ConsultaService{

	@Autowired
	private ConsultaDao consultaDao;
	
	@Override
	public List<Consulta> readAll(Map<String, Object> criteria) {
		return consultaDao.readAll(criteria);
	}

	@Override
	public Map<String,String> create(Consulta entity) {
		return consultaDao.create(entity);
	}

	@Override
	public Consulta readById(long id) {
		return consultaDao.readById(id);
	}

	@Override
	public boolean update(Consulta entity) {
		return consultaDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		return consultaDao.deleteById(id);
	}

}
