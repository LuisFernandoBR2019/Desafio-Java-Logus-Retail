package br.com.logusretail.api.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.logusretail.api.service.MedicoService;
import br.com.logusretail.model.entities.Medico;

@RestController
@RequestMapping("/api/v1/medico")
@CrossOrigin(origins = "*")
public class MedicoController {

	@Autowired
	private MedicoService medicoService;

	@GetMapping("/read-all")
	public ResponseEntity<List<Medico>> readAll(Map<String, Object> criteria) {
		List<Medico> listaMedico = medicoService.readAll(criteria);

		if (listaMedico == null || listaMedico.size() == 0) {
			return ResponseEntity.ok(null);
		} else {
			return ResponseEntity.ok(listaMedico);
		}
	}

	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Medico> readById(@PathVariable("id") Long id) {
		Medico medico = medicoService.readById(id);

		if (medico == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(medico);
	}

	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody Medico entity) {
		boolean response = medicoService.update(entity);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/create")
	public ResponseEntity<Map<String, String>> create(@RequestBody Medico entity) {
		Map<String, String> erros = new LinkedHashMap<>();
		Map<String, String> response = new LinkedHashMap<>();
		erros = validarCreateMedico(entity);

		if (erros.isEmpty()) {
			response = medicoService.create(entity);
		} else {
			response = erros;
		}

		return ResponseEntity.ok(response);
	}

	private Map<String, String> validarCreateMedico(Medico entity) {
		Map<String, String> erros = new LinkedHashMap<>();
		if (entity.getNome() == null || entity.getNome().isEmpty()) {
			erros.put("medicoCampoNome", "Campo nome em branco.");
		}

		if (entity.getEspecialidade() == null || entity.getEspecialidade().isEmpty()) {
			erros.put("medicoCampoEspecialidade", "Campo especialidade em branco.");
		}

		if (entity.getCrm() == null || entity.getCrm().isEmpty()) {
			erros.put("medicoCampoCrm", "Campo CRM em branco.");
		}

		if (entity.getIdade() == null) {
			erros.put("medicoCampoIdade", "Campo idade em branco.");
		}
		return erros;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
		boolean response = medicoService.deleteById(id);

		return ResponseEntity.ok(response);
	}

}
