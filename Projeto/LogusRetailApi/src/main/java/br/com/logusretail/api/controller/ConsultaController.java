package br.com.logusretail.api.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

import br.com.logusretail.api.service.ConsultaService;
import br.com.logusretail.api.service.MedicoService;
import br.com.logusretail.model.entities.Consulta;
import br.com.logusretail.model.entities.Medico;

@RestController
@RequestMapping("/api/v1/consulta")
@CrossOrigin(origins = "*")
public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;

	@Autowired
	private MedicoService medicoService;

	@GetMapping("/read-all")
	public ResponseEntity<List<Consulta>> readAll(Map<String, Object> criteria) {
		List<Consulta> listaConsultas = consultaService.readAll(criteria);

		if (listaConsultas == null || listaConsultas.size() == 0) {
			return ResponseEntity.ok(null);
		} else {
			return ResponseEntity.ok(listaConsultas);
		}
	}

	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Consulta> readById(@PathVariable("id") Long id) {
		Consulta consulta = consultaService.readById(id);

		if (consulta == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(consulta);
	}

	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody Consulta entity) {
		boolean response = consultaService.update(entity);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/create")
	public ResponseEntity<Map<String, String>> create(@RequestBody Consulta entity) {

		Map<String, String> response = new LinkedHashMap<>();

		Map<String, String> erros = new LinkedHashMap<>();
		if (entity.getDataHora() == null) {
			entity = validarHoraDataFormato(entity);
		}
		erros = validarCreateConsulta(entity);

		if (erros.isEmpty()) {
			response = consultaService.create(entity);
		} else {
			response = erros;
		}

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
		boolean response = consultaService.deleteById(id);

		return ResponseEntity.ok(response);
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

	public Consulta validarHoraDataFormato(Consulta consulta) {

		System.out.println(consulta.getDataHoraFormatada());
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

	private Map<String, String> validarCreateConsulta(Consulta consulta) {
		Map<String, String> erros = new HashMap<>();
		Map<String, Object> criteria = new HashMap<>();
		List<Consulta> listaConsultas = consultaService.readAll(criteria);

		List<Medico> listaMedicos = medicoService.readAll(criteria);
		consulta = atribuirInformacoes(consulta, listaMedicos);

		if (consulta.getPaciente().getId() == null) {
			erros.put("pacienteCampo", "Campo paciente em branco.");
		}

		if (consulta.getConsultorio().getId() == null) {
			erros.put("consultorioCampo", "Campo consultorio em branco.");
		}

		if (consulta.getListaMedico().get(0).getId() == null && consulta.getListaMedico().get(1).getId() == null) {
			erros.put("medicosCampo", "Nenhum médico selecionado para a consulta.");
		}

		if (consulta.getDataHora() == null) {
			erros.put("consultaCampo", "Nenhuma data e hora escolhida para a consulta.");
		}
		Long idMedico1 = consulta.getListaMedico().get(0).getId();
		Long idMedico2 = consulta.getListaMedico().get(1).getId();
		if (idMedico1 != null && idMedico2 != null) {
			if (idMedico1 == idMedico2) {
				erros.put("mesmoMedicoConsulta", "Não pode escolher duas vezes o mesmo médico.");
			}
		}

		if (erros.isEmpty()) {

			String especialidadeMedico1 = consulta.getListaMedico().get(0).getEspecialidade();
			String especialidadeMedico2 = consulta.getListaMedico().get(1).getEspecialidade();

			if (!especialidadeMedico1.equals("Cirurgiao")) {
				if (!especialidadeMedico2.equals("")) {
					if (!especialidadeMedico1.equals("")) {
						erros.put("medicosEspecialidade",
								"Dois médicos só podem atender no mesmo consultório e na mesma data e hora, caso forem cirurgião.");
					}

				}
			} else if (!especialidadeMedico2.equals("Cirurgiao")) {
				if (!especialidadeMedico1.equals("")) {
					if (!especialidadeMedico2.equals("")) {
						erros.put("medicosEspecialidade",
								"Dois médicos só podem atender no mesmo consultório e na mesma data e hora, caso forem cirurgião.");
					}
				}
			} else if (!especialidadeMedico1.equals("Cirurgiao") && !especialidadeMedico2.equals("Cirurgiao")) {
				erros.put("medicosEspecialidade",
						"Dois médicos só podem atender no mesmo consultório e na mesma data e hora, caso forem cirurgião.");
			}

			int contador = 0;
			for (Consulta consultaAux : listaConsultas) {
				if (consultaAux.getDataHora().getDate() == consulta.getDataHora().getDate()
						&& consulta.getPaciente().getId() == consultaAux.getPaciente().getId()) {

					erros.put("consultaPacienteConsultorio",
							"O mesmo paciente não pode marcar duas ou mais consultas no mesmo dia.");

				}

				if (consultaAux.getDataHora().getDate() == consulta.getDataHora().getDate()) {

					if (consultaAux.getConsultorio().getId() == consulta.getConsultorio().getId()) {
						erros.putAll(
								verificarHoraConsultaNoConsultorio(consulta.getDataHora(), consultaAux.getDataHora()));
						contador++;

					} else {
						for (int i = 0; i < consulta.getListaMedico().size(); i++) {
							if (consulta.getListaMedico().get(i).getId() != null) {
								if (consulta.getListaMedico().get(i).getId() == consultaAux.getListaMedico().get(i)
										.getId()) {
									erros.put("consultaMedicoConsultorio",
											"O médico deve atender todas as consultas em um mesmo consultório");
									break;
								}
							}
						}
					}
				}

			}

			if (contador > 12) {
				erros.put("limiteConsulta",
						"O número máximo de consultas no mesmo consultório por dia (12) atingidos.");
			}
		}

		return erros;
	}

	private Map<String, String> verificarHoraConsultaNoConsultorio(Timestamp dataHoraConsulta,
			Timestamp dataHoraConsultaLista) {
		Map<String, String> erros = new LinkedHashMap<>();

		SimpleDateFormat sdfConvert = new SimpleDateFormat("HH:mm");

		String horaConsulta = sdfConvert.format(dataHoraConsulta.getTime());
		String horaConsultaLista = sdfConvert.format(dataHoraConsultaLista.getTime());

		Date dataConsultaLista = null;
		Date dataConsulta = null;
		try {
			dataConsulta = sdfConvert.parse(horaConsulta);
			dataConsultaLista = sdfConvert.parse(horaConsultaLista);
		} catch (ParseException ex) {
			System.out.println("Erro: " + ex);
		}

		GregorianCalendar gcConsulta = new GregorianCalendar();
		gcConsulta.setTime(dataConsulta);

		GregorianCalendar gcConsultaLista = new GregorianCalendar();
		gcConsultaLista.setTime(dataConsultaLista);

		long tempAgoraConsultaLista = gcConsultaLista.getTimeInMillis();

		gcConsulta.add(Calendar.MINUTE, 15);

		long tempDepois = gcConsulta.getTimeInMillis();

		gcConsulta.add(Calendar.MINUTE, 2 * -15);

		long tempAntes = gcConsulta.getTimeInMillis();

		if ((tempAgoraConsultaLista > tempAntes) && (tempAgoraConsultaLista < tempDepois)) {
			erros.put("intervaloConsulta",
					"O intervalo de uma consulta para outra deve respeitar um tempo mínimo de 15 minutos.");
		}
		return erros;
	}

}
