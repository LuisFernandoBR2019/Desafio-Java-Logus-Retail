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

import br.com.logusretail.api.service.ConsultorioService;
import br.com.logusretail.model.entities.Consultorio;

@RestController
@RequestMapping("/api/v1/consultorio")
@CrossOrigin(origins = "*")
public class ConsultorioController {

	@Autowired
	private ConsultorioService consultorioService;

	@GetMapping("/read-all")
	public ResponseEntity<List<Consultorio>> readAll(Map<String, Object> criteria) {
		List<Consultorio> listaConsultorio = consultorioService.readAll(criteria);

		if (listaConsultorio == null || listaConsultorio.size() == 0) {
			return ResponseEntity.ok(null);
		} else {
			return ResponseEntity.ok(listaConsultorio);
		}
	}

	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Consultorio> readById(@PathVariable("id") Long id) {
		Consultorio consultorio = consultorioService.readById(id);

		if (consultorio == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(consultorio);
	}

	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody Consultorio entity) {
		boolean response = consultorioService.update(entity);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/create")
	public ResponseEntity<Map<String, String>> create(@RequestBody Consultorio entity) {
		Map<String, String> erros = new HashMap<>();
		Map<String, String> response = new HashMap<>();
		erros = validarCreateConsulta(entity);

		if (erros.isEmpty()) {
			response = consultorioService.create(entity);
		} else {
			response = erros;
		}

		return ResponseEntity.ok(response);
	}

	private Map<String, String> validarCreateConsulta(Consultorio entity) {
		Map<String, String> erros = new HashMap<>();
		if (entity.getNumeroConsultorio() == null) {
			erros.put("consultorioCampo", "Campo consultorio em branco.");
		}
		return erros;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
		boolean response = consultorioService.deleteById(id);

		return ResponseEntity.ok(response);
	}

}
