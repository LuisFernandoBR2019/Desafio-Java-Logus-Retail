package br.com.logusretail.api.entitiesService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.logusretail.api.service.PacienteService;
import br.com.logusretail.database.entitiesDao.PacienteDao;
import br.com.logusretail.model.entities.Paciente;

@Service
public class PacienteServiceDao implements PacienteService {

	@Autowired
	private PacienteDao pacienteDao;

	@Override
	public List<Paciente> readAll(Map<String, Object> criteria) {
		return pacienteDao.readAll(criteria);
	}

	@Override
	public Map<String, String> create(Paciente entity) {
		return pacienteDao.create(entity);
	}

	@Override
	public Paciente readById(long id) {
		return pacienteDao.readById(id);
	}

	@Override
	public boolean update(Paciente entity) {
		return pacienteDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		return pacienteDao.deleteById(id);
	}

}
