package br.com.logusretail.api.entitiesService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.logusretail.api.service.MedicoService;
import br.com.logusretail.database.entitiesDao.MedicoDao;
import br.com.logusretail.model.entities.Medico;

@Service
public class MedicoServiceDao implements MedicoService{

	@Autowired
	private MedicoDao medicoDao;
	
	@Override
	public List<Medico> readAll(Map<String, Object> criteria) {
		return medicoDao.readAll(criteria);
	}

	@Override
	public Map<String,String> create(Medico entity) {
		return medicoDao.create(entity);
	}

	@Override
	public Medico readById(long id) {
		return medicoDao.readById(id);
	}

	@Override
	public boolean update(Medico entity) {
		return medicoDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		return medicoDao.deleteById(id);
	}

}
