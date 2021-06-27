package br.com.logusretail.client.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.logusretail.client.entitiesService.PacienteService;
import br.com.logusretail.model.entities.Paciente;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;

	@PostMapping("/create")
	public String postCreatePage(Paciente paciente, Model model) {
		Map<String, String> resposta = pacienteService.create(paciente);
		if (!resposta.containsKey("criado")) {
			if (resposta.containsKey("pacienteCampo")) {
				
				model.addAttribute("erros", "Campo em branco");
				
			} 
		} else {
			model.addAttribute("criado", "Paciente cadastrado com sucesso.");
		}
		return "paciente/create";
	}

	@GetMapping("/create")
	public String getCreatePage(Paciente paciente, Model model) {
		return "paciente/create";
	}

	@GetMapping("/list")
	public String getListPage(Model model) {
		Map<String, Object> criteria = new HashMap<>();
		List<Paciente> listaPacientes = pacienteService.readAll(criteria);

		if (listaPacientes != null && listaPacientes.size() != 0) {
			model.addAttribute("listaPacientes", listaPacientes);

		} else {
			model.addAttribute("listaPacientes", new ArrayList<Paciente>());

		}
		return "paciente/list";
	}

	@GetMapping("/edit")
	public String getEditPage() {
		return "paciente/edit";
	}

	@GetMapping("/detail")
	public String getDetailPage() {
		return "paciente/detail";
	}

}
