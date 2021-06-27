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

import br.com.logusretail.client.entitiesService.ConsultorioService;
import br.com.logusretail.model.entities.Consultorio;

@Controller
@RequestMapping("/consultorio")
public class ConsultorioController {

	@Autowired
	private ConsultorioService consultorioService;

	@PostMapping("/create")
	public String postCreatePage(Consultorio consultorio, Model model) {
		Map<String, String> resposta = consultorioService.create(consultorio);
		if (!resposta.containsKey("criado")) {
			if (resposta.containsKey("consultorioCampo")) {

				model.addAttribute("erros", "Campo em branco");

			}
		} else {
			model.addAttribute("criado", "Consultório cadastrado com sucesso.");
		}
		return "consultorio/create";
	}

	@GetMapping("/create")
	public String getCreatePage(Consultorio consultorio, Model model) {
		return "consultorio/create";
	}

	@GetMapping("/list")
	public String getListPage(Model model) {
		Map<String, Object> criteria = new HashMap<>();
		List<Consultorio> listaConsultorio = consultorioService.readAll(criteria);

		if (listaConsultorio != null && listaConsultorio.size() != 0) {
			model.addAttribute("listasConsultorio", listaConsultorio);

		} else {
			model.addAttribute("listasConsultorio", new ArrayList<Consultorio>());

		}
		return "consultorio/list";
	}

	@GetMapping("/edit")
	public String getEditPage() {
		return "consultorio/edit";
	}

	@GetMapping("/detail")
	public String getDetailPage() {
		return "consultorio/detail";
	}

}
