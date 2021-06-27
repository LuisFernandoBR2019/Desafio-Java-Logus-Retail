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

import br.com.logusretail.client.entitiesService.MedicoService;
import br.com.logusretail.model.entities.Medico;

@Controller
@RequestMapping("/medico")
public class MedicoController {

	@Autowired
	private MedicoService medicoService;

	@PostMapping("/create")
	public String postCreatePage(Medico medico, Model model) {
		Map<String, String> resposta = medicoService.create(medico);
		if (!resposta.containsKey("criado")) {
			if (resposta.containsKey("medicoCampoNome") || resposta.containsKey("medicoCampoEspecialidade")
					|| resposta.containsKey("medicoCampoCrm") || resposta.containsKey("medicoCampoIdade")) {

				model.addAttribute("erros", "Campos em branco");

			}else if (resposta.containsKey("medicoCampoCrmUnique")) {
				model.addAttribute("erros", "Este CRM já está cadastrado.");
			}
		} else {
			model.addAttribute("criado", "Médico cadastrado com sucesso.");
		}
		return "medico/create";
	}

	@GetMapping("/create")
	public String getCreatePage(Medico medico, Model model) {
		return "medico/create";
	}

	@GetMapping("/list")
	public String getListPage(Model model) {
		Map<String, Object> criteria = new HashMap<>();
		List<Medico> listaMedicos = medicoService.readAll(criteria);

		if (listaMedicos != null && listaMedicos.size() != 0) {
			model.addAttribute("listaMedicos", listaMedicos);

		} else {
			model.addAttribute("listaMedicos", new ArrayList<Medico>());

		}
		return "medico/list";
	}

	@GetMapping("/edit")
	public String getEditPage() {
		return "medico/edit";
	}

	@GetMapping("/detail")
	public String getDetailPage() {
		return "medico/detail";
	}

}
