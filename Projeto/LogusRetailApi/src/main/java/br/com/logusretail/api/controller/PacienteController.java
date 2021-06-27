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

import br.com.logusretail.api.service.PacienteService;
import br.com.logusretail.model.entities.Paciente;

@RestController
@RequestMapping("/api/v1/paciente")
@CrossOrigin(origins = "*")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;

	@GetMapping("/read-all")
	public ResponseEntity<List<Paciente>> readAll(Map<String, Object> criteria) {
		List<Paciente> listaPacientes = pacienteService.readAll(criteria);

		if (listaPacientes == null || listaPacientes.size() == 0) {
			return ResponseEntity.ok(null);
		} else {
			return ResponseEntity.ok(listaPacientes);
		}
	}

	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Paciente> readById(@PathVariable("id") Long id) {
		Paciente paciente = pacienteService.readById(id);

		if (paciente == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(paciente);
	}

	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody Paciente entity) {
		boolean response = pacienteService.update(entity);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/create")
	public ResponseEntity<Map<String, String>> create(@RequestBody Paciente entity) {
		Map<String, String> erros = new HashMap<>();
		Map<String, String> response = new HashMap<>();
		erros = validarCreatePaciente(entity);

		if (erros.isEmpty()) {
			response = pacienteService.create(entity);
		} else {
			response = erros;
		}

		return ResponseEntity.ok(response);
	}

	private Map<String, String> validarCreatePaciente(Paciente entity) {
		Map<String, String> erros = new HashMap<>();
		if (entity.getNome() == null || entity.getNome().isEmpty()) {
			erros.put("pacienteCampo", "Campo paciente em branco.");
		}
		return erros;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
		boolean response = pacienteService.deleteById(id);

		return ResponseEntity.ok(response);
	}
}
