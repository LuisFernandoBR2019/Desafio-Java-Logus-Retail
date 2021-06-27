package br.com.logusretail.client.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.logusretail.client.entitiesService.ConsultaService;
import br.com.logusretail.client.entitiesService.ConsultorioService;
import br.com.logusretail.client.entitiesService.MedicoService;
import br.com.logusretail.client.entitiesService.PacienteService;
import br.com.logusretail.model.entities.Consulta;
import br.com.logusretail.model.entities.Consultorio;
import br.com.logusretail.model.entities.Medico;
import br.com.logusretail.model.entities.Paciente;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private MedicoService medicoService;

	@Autowired
	private ConsultorioService consultorioService;

	@PostMapping("/create")
	public String postCreatePage(Consulta consulta, Model model) {
		Map<String, Object> criteria = new HashMap<>();
		List<Paciente> listaPacientes = pacienteService.readAll(criteria);
		List<Medico> listaMedicos = medicoService.readAll(criteria);
		List<Consultorio> listaConsultorio = consultorioService.readAll(criteria);

		consulta = validarHoraDataFormato(consulta);
		consulta = atribuirInformacoes(consulta, listaMedicos);
		if (listaPacientes != null && listaPacientes.size() != 0) {
			model.addAttribute("listaPacientes", listaPacientes);

		} else {
			model.addAttribute("listaPacientes", new ArrayList<Consulta>());

		}

		if (listaMedicos != null && listaMedicos.size() != 0) {
			for (int i = 0; i < listaMedicos.size(); i++) {
				if (listaMedicos.get(i).getEspecialidade().equals("Cirurgiao")) {
					listaMedicos.get(i).setEspecialidade("Cirurgião");
				}
			}
			model.addAttribute("listaMedicos", listaMedicos);

		} else {
			model.addAttribute("listaMedicos", new ArrayList<Consulta>());

		}

		if (listaConsultorio != null && listaConsultorio.size() != 0) {
			model.addAttribute("listaConsultorio", listaConsultorio);

		} else {
			model.addAttribute("listaConsultorio", new ArrayList<Consulta>());

		}

		Map<String, String> resposta = consultaService.create(consulta);
		if (!resposta.containsKey("criado")) {

			if (resposta.containsKey("pacienteCampo") || resposta.containsKey("consultorioCampo")
					|| resposta.containsKey("medicosCampo") || resposta.containsKey("consultaCampo")) {

				model.addAttribute("erros", "Campos em branco");

			} else if (resposta.containsKey("consultaPacienteConsultorio")) {

				model.addAttribute("erros", "O mesmo paciente não pode marcar duas ou mais consultas no mesmo dia.");

			} else if (resposta.containsKey("limiteConsulta")) {

				model.addAttribute("erros",
						"O número máximo de consultas no mesmo consultório por dia (12) atingidos.");

			} else if (resposta.containsKey("consultaMedicoConsultorio")) {

				model.addAttribute("erros",
						"O médico deve atender todas as consultas do mesmo dia em um mesmo consultório.");

			} else if (resposta.containsKey("intervaloConsulta")) {

				model.addAttribute("erros",
						"O intervalo de uma consulta para outra deve respeitar um tempo mínimo de 15 minutos.");

			} else if (resposta.containsKey("medicosEspecialidade")) {

				model.addAttribute("erros",
						"Dois médicos só podem atender no mesmo consultório e na mesma data e hora, caso forem cirurgião.");

			} else if (resposta.containsKey("mesmoMedicoConsulta")) {

				model.addAttribute("erros", "Não pode escolher duas vezes o mesmo médico.");

			}

		} else {
			model.addAttribute("criado", "Consulta cadastrada com sucesso.");
		}
		return "consulta/create";
	}

	@GetMapping("/create")
	public String getCreatePage(Consulta consulta, Model model) {
		Map<String, Object> criteria = new HashMap<>();
		List<Paciente> listaPacientes = pacienteService.readAll(criteria);
		List<Medico> listaMedicos = medicoService.readAll(criteria);
		List<Consultorio> listaConsultorio = consultorioService.readAll(criteria);

		if (listaPacientes != null && listaPacientes.size() != 0) {
			model.addAttribute("listaPacientes", listaPacientes);

		} else {
			model.addAttribute("listaPacientes", new ArrayList<Consulta>());

		}

		if (listaMedicos != null && listaMedicos.size() != 0) {
			for (int i = 0; i < listaMedicos.size(); i++) {
				if (listaMedicos.get(i).getEspecialidade().equals("Cirurgiao")) {
					listaMedicos.get(i).setEspecialidade("Cirurgião");
				}
			}
			model.addAttribute("listaMedicos", listaMedicos);

		} else {
			model.addAttribute("listaMedicos", new ArrayList<Consulta>());

		}

		if (listaConsultorio != null && listaConsultorio.size() != 0) {
			model.addAttribute("listaConsultorio", listaConsultorio);

		} else {
			model.addAttribute("listaConsultorio", new ArrayList<Consulta>());

		}

		return "consulta/create";
	}

	@GetMapping("/list")
	public String getListPage(Model model) {
		Map<String, Object> criteria = new HashMap<>();
		List<Consulta> listaConsultas = consultaService.readAll(criteria);

		if (listaConsultas != null && listaConsultas.size() != 0) {
			for (int i = 0; i < listaConsultas.size(); i++) {
				for (int j = 0; j < listaConsultas.get(i).getListaMedico().size(); j++) {
					if (listaConsultas.get(i).getListaMedico().get(j).getEspecialidade().equals("Cirurgiao")) {
						listaConsultas.get(i).getListaMedico().get(j).setEspecialidade("Cirurgião");
					}
				}
				formatarDataHoraLista(listaConsultas.get(i));

			}
			model.addAttribute("listaConsultas", listaConsultas);

		} else {
			model.addAttribute("listaConsultas", new ArrayList<Consulta>());

		}
		return "consulta/list";
	}


	@GetMapping("/edit")
	public String getEditPage() {
		return "consulta/edit";
	}

	@GetMapping("/detail")
	public String getDetailPage() {
		return "consulta/detail";
	}

	public Consulta validarHoraDataFormato(Consulta consulta) {

		//System.out.println(consulta.getDataHoraFormatada());
		if (!consulta.getDataHoraFormatada().isEmpty() && consulta.getDataHoraFormatada() != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"),
						sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				
				Date parse = sdf.parse(consulta.getDataHoraFormatada().replace("T", " "));
				String dateTime = sdf2.format(parse);
				
				Timestamp timestamp = Timestamp.valueOf(dateTime);
				consulta.setDataHora(timestamp);
				
			} catch (Exception e) {
				System.out.println("Erro: " + e);
			}
		}

		return consulta;
	}

	public void formatarDataHoraLista(Consulta consulta) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		consulta.setDataHoraFormatada(sdf.format(consulta.getDataHora()));
	}

	public Consulta atribuirInformacoes(Consulta consulta, List<Medico> listaMedico) {
		for (int i = 0; i < consulta.getListaMedico().size(); i++) {

			if (consulta.getListaMedico().get(i).getId() != null) {
				for (int j = 0; j < listaMedico.size(); j++) {
					if (consulta.getListaMedico().get(i).getId() == listaMedico.get(j).getId()) {
						consulta.getListaMedico().get(i).setEspecialidade(listaMedico.get(j).getEspecialidade());
					}
				}
			} else {
				consulta.getListaMedico().get(i).setEspecialidade("");
			}
		}
		return consulta;
	}

}
