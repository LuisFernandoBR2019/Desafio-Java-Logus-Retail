package br.com.logusretail.api.testeConsulta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.logusretail.model.entities.Consulta;
import br.com.logusretail.model.entities.Consultorio;
import br.com.logusretail.model.entities.Medico;
import br.com.logusretail.model.entities.Paciente;

class ConsultaTeste {

	private void cadastroMais11Consultas() {
		Map<String, String> resultadoEsperado = new LinkedHashMap<>();

		resultadoEsperado.put("criado", "sucesso");

		Consulta entity = new Consulta();

		Paciente paciente = new Paciente();
		paciente.setId(2L);

		List<Medico> listaMedico = new ArrayList<Medico>();

		Medico medico = new Medico();
		medico.setId(1L);
		listaMedico.add(medico);

		medico = new Medico();
		listaMedico.add(medico);

		Consultorio consultorio = new Consultorio();
		consultorio.setId(1L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-18T16:13");
		entity = validarHoraDataFormato(entity);

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/consulta/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// ------------------------------------------------------------------------
		entity = new Consulta();

		paciente = new Paciente();
		paciente.setId(3L);

		listaMedico = new ArrayList<Medico>();

		medico = new Medico();
		medico.setId(1L);
		listaMedico.add(medico);

		medico = new Medico();
		listaMedico.add(medico);

		consultorio = new Consultorio();
		consultorio.setId(1L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-18T17:13");
		entity = validarHoraDataFormato(entity);

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// ------------------------------------------------------------------------
		entity = new Consulta();

		paciente = new Paciente();
		paciente.setId(4L);

		listaMedico = new ArrayList<Medico>();

		medico = new Medico();
		medico.setId(1L);
		listaMedico.add(medico);

		medico = new Medico();
		listaMedico.add(medico);

		consultorio = new Consultorio();
		consultorio.setId(1L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-18T18:13");
		entity = validarHoraDataFormato(entity);

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// ------------------------------------------------------------------------
		entity = new Consulta();

		paciente = new Paciente();
		paciente.setId(5L);

		listaMedico = new ArrayList<Medico>();

		medico = new Medico();
		medico.setId(1L);
		listaMedico.add(medico);

		medico = new Medico();
		listaMedico.add(medico);

		consultorio = new Consultorio();
		consultorio.setId(1L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-18T19:13");
		entity = validarHoraDataFormato(entity);

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// ------------------------------------------------------------------------
		entity = new Consulta();

		paciente = new Paciente();
		paciente.setId(6L);

		listaMedico = new ArrayList<Medico>();

		medico = new Medico();
		medico.setId(1L);
		listaMedico.add(medico);

		medico = new Medico();
		listaMedico.add(medico);

		consultorio = new Consultorio();
		consultorio.setId(1L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-18T20:13");
		entity = validarHoraDataFormato(entity);

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// ------------------------------------------------------------------------
		entity = new Consulta();

		paciente = new Paciente();
		paciente.setId(7L);

		listaMedico = new ArrayList<Medico>();

		medico = new Medico();
		medico.setId(1L);
		listaMedico.add(medico);

		medico = new Medico();
		listaMedico.add(medico);

		consultorio = new Consultorio();
		consultorio.setId(1L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-18T21:13");
		entity = validarHoraDataFormato(entity);

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// ------------------------------------------------------------------------
		entity = new Consulta();

		paciente = new Paciente();
		paciente.setId(8L);

		listaMedico = new ArrayList<Medico>();

		medico = new Medico();
		medico.setId(1L);
		listaMedico.add(medico);

		medico = new Medico();
		listaMedico.add(medico);

		consultorio = new Consultorio();
		consultorio.setId(1L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-18T22:13");
		entity = validarHoraDataFormato(entity);

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// ------------------------------------------------------------------------
		entity = new Consulta();

		paciente = new Paciente();
		paciente.setId(9L);

		listaMedico = new ArrayList<Medico>();

		medico = new Medico();
		medico.setId(1L);
		listaMedico.add(medico);

		medico = new Medico();
		listaMedico.add(medico);

		consultorio = new Consultorio();
		consultorio.setId(1L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-18T23:13");
		entity = validarHoraDataFormato(entity);

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// ------------------------------------------------------------------------
		entity = new Consulta();

		paciente = new Paciente();
		paciente.setId(10L);

		listaMedico = new ArrayList<Medico>();

		medico = new Medico();
		medico.setId(1L);
		listaMedico.add(medico);

		medico = new Medico();
		listaMedico.add(medico);

		consultorio = new Consultorio();
		consultorio.setId(1L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-18T15:33");
		entity = validarHoraDataFormato(entity);

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// ------------------------------------------------------------------------
		entity = new Consulta();

		paciente = new Paciente();
		paciente.setId(11L);

		listaMedico = new ArrayList<Medico>();

		medico = new Medico();
		medico.setId(1L);
		listaMedico.add(medico);

		medico = new Medico();
		listaMedico.add(medico);

		consultorio = new Consultorio();
		consultorio.setId(1L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-18T16:33");
		entity = validarHoraDataFormato(entity);

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// ------------------------------------------------------------------------
		entity = new Consulta();

		paciente = new Paciente();
		paciente.setId(12L);

		listaMedico = new ArrayList<Medico>();

		medico = new Medico();
		medico.setId(1L);
		listaMedico.add(medico);

		medico = new Medico();
		listaMedico.add(medico);

		consultorio = new Consultorio();
		consultorio.setId(1L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-18T17:33");
		entity = validarHoraDataFormato(entity);

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// ------------------------------------------------------------------------
		entity = new Consulta();

		paciente = new Paciente();
		paciente.setId(13L);

		listaMedico = new ArrayList<Medico>();

		medico = new Medico();
		medico.setId(1L);
		listaMedico.add(medico);

		medico = new Medico();
		listaMedico.add(medico);

		consultorio = new Consultorio();
		consultorio.setId(1L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-18T03:45");
		entity = validarHoraDataFormato(entity);

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));

	}

	@Test
	public void testListaConsultaNaoNula() {

		List<Consulta> response = null;

		String endpoint = "http://localhost:8082/api/v1/consulta/read-all";

		RestTemplate restTemplate = new RestTemplate();
		try {

			ResponseEntity<Consulta[]> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, null,
					Consulta[].class);
			Consulta[] listaConsultas = requestResponse.getBody();
			response = Arrays.asList(listaConsultas);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Integer quantidadeListaEsperada = null;
		assertNotEquals(quantidadeListaEsperada, response);

	}

	public Consulta validarHoraDataFormato(Consulta consulta) {

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

	@Test
	public void testCadastroConsultaSucesso() {
		Map<String, String> resultadoEsperado = new LinkedHashMap<>();

		resultadoEsperado.put("criado", "sucesso");

		Consulta entity = new Consulta();

		Paciente paciente = new Paciente();
		paciente.setId(1L);

		List<Medico> listaMedico = new ArrayList<Medico>();

		Medico medico = new Medico();
		medico.setId(1L);
		listaMedico.add(medico);

		medico = new Medico();
		listaMedico.add(medico);

		Consultorio consultorio = new Consultorio();
		consultorio.setId(1L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-18T07:13");
		entity = validarHoraDataFormato(entity);

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/consulta/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
	}

	@Test
	public void testFalhaPacienteVazio() {
		Map<String, String> resultadoEsperado = new LinkedHashMap<>();
		resultadoEsperado.put("pacienteCampo", "Campo paciente em branco.");

		Consulta entity = new Consulta();

		Paciente paciente = new Paciente();
		paciente.setId(null);

		List<Medico> listaMedico = new ArrayList<Medico>();

		Medico medico = new Medico();
		medico.setId(1L);
		listaMedico.add(medico);

		medico = new Medico();
		listaMedico.add(medico);

		Consultorio consultorio = new Consultorio();
		consultorio.setId(1L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-18T08:13");
		entity = validarHoraDataFormato(entity);

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/consulta/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("pacienteCampo"), resposta.get("pacienteCampo"));
	}

	@Test
	public void testFalhaConsultorioVazio() {
		Map<String, String> resultadoEsperado = new LinkedHashMap<>();
		resultadoEsperado.put("consultorioCampo", "Campo consultorio em branco.");

		Consulta entity = new Consulta();

		Paciente paciente = new Paciente();
		paciente.setId(1L);

		List<Medico> listaMedico = new ArrayList<Medico>();

		Medico medico = new Medico();
		medico.setId(1L);
		listaMedico.add(medico);

		medico = new Medico();
		listaMedico.add(medico);

		Consultorio consultorio = new Consultorio();
		consultorio.setId(null);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-18T09:13");
		entity = validarHoraDataFormato(entity);

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/consulta/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("consultorioCampo"), resposta.get("consultorioCampo"));
	}

	@Test
	public void testFalhaListaMedicosVazio() {
		Map<String, String> resultadoEsperado = new LinkedHashMap<>();
		resultadoEsperado.put("medicosCampo", "Nenhum médico selecionado para a consulta.");

		Consulta entity = new Consulta();

		Paciente paciente = new Paciente();
		paciente.setId(1L);

		List<Medico> listaMedico = new ArrayList<Medico>();

		Medico medico = new Medico();
		listaMedico.add(medico);

		medico = new Medico();
		listaMedico.add(medico);

		Consultorio consultorio = new Consultorio();
		consultorio.setId(1L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-18T10:13");
		entity = validarHoraDataFormato(entity);

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/consulta/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("medicosCampo"), resposta.get("medicosCampo"));
	}

	@Test
	public void testFalhaDataHoraConsultaVazia() {
		Map<String, String> resultadoEsperado = new LinkedHashMap<>();
		resultadoEsperado.put("consultaCampo", "Nenhuma data e hora escolhida para a consulta.");

		Consulta entity = new Consulta();

		Paciente paciente = new Paciente();
		paciente.setId(1L);

		List<Medico> listaMedico = new ArrayList<Medico>();

		Medico medico = new Medico();
		medico.setId(1L);
		listaMedico.add(medico);

		medico = new Medico();
		listaMedico.add(medico);

		Consultorio consultorio = new Consultorio();
		consultorio.setId(1L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("");
		entity = validarHoraDataFormato(entity);

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/consulta/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("consultaCampo"), resposta.get("consultaCampo"));
	}

	@Test
	public void testFalhaDoisMedicosNaoPodeAtenderNoMesmoConsultorioComEspecialidadeDiferenteCirurgiao() {
		Map<String, String> resultadoEsperado = new LinkedHashMap<>();
		resultadoEsperado.put("medicosEspecialidade",
				"Dois médicos só podem atender no mesmo consultório e na mesma data e hora, caso forem cirurgião.");

		Consulta entity = new Consulta();

		Paciente paciente = new Paciente();
		paciente.setId(1L);

		List<Medico> listaMedico = new ArrayList<Medico>();

		Medico medico = new Medico();
		medico.setId(1L);
		listaMedico.add(medico);

		medico = new Medico();
		medico.setId(2L);
		listaMedico.add(medico);

		Consultorio consultorio = new Consultorio();
		consultorio.setId(1L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-18T11:13");
		entity = validarHoraDataFormato(entity);

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/consulta/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("medicosEspecialidade"), resposta.get("medicosEspecialidade"));
	}

	@Test
	public void testFalhaNaoPodeEscolherMesmoMedicoDuasVezes() {
		Map<String, String> resultadoEsperado = new LinkedHashMap<>();
		resultadoEsperado.put("mesmoMedicoConsulta", "Não pode escolher duas vezes o mesmo médico.");

		Consulta entity = new Consulta();

		Paciente paciente = new Paciente();
		paciente.setId(1L);

		List<Medico> listaMedico = new ArrayList<Medico>();

		Medico medico = new Medico();
		medico.setId(1L);

		listaMedico.add(medico);
		listaMedico.add(medico);

		Consultorio consultorio = new Consultorio();
		consultorio.setId(1L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-19T12:13");
		entity = validarHoraDataFormato(entity);

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/consulta/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("mesmoMedicoConsulta"), resposta.get("mesmoMedicoConsulta"));
	}

	@Test
	public void testFalhaMesmoPacienteNaoPodeMarcarDuasConsultasNoMesmoDia() {
		Map<String, String> resultadoEsperado = new LinkedHashMap<>();
		resultadoEsperado.put("consultaPacienteConsultorio",
				"O mesmo paciente não pode marcar duas ou mais consultas no mesmo dia.");

		Consulta entity = new Consulta();

		Paciente paciente = new Paciente();
		paciente.setId(1L);

		List<Medico> listaMedico = new ArrayList<Medico>();

		Medico medico = new Medico();
		medico.setId(1L);
		listaMedico.add(medico);

		medico = new Medico();
		listaMedico.add(medico);

		Consultorio consultorio = new Consultorio();
		consultorio.setId(1L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-18T05:13");
		entity = validarHoraDataFormato(entity);

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/consulta/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("consultaPacienteConsultorio"), resposta.get("consultaPacienteConsultorio"));
	}

	@Test
	public void testFalhaMedicoDeveAtenderConsultasNoMesmoConsultorio() {
		Map<String, String> resultadoEsperado = new LinkedHashMap<>();
		resultadoEsperado.put("consultaMedicoConsultorio",
				"O médico deve atender todas as consultas em um mesmo consultório");

		Consulta entity = new Consulta();

		Paciente paciente = new Paciente();
		paciente.setId(14L);

		List<Medico> listaMedico = new ArrayList<Medico>();

		Medico medico = new Medico();
		medico.setId(1L);
		listaMedico.add(medico);

		medico = new Medico();
		listaMedico.add(medico);

		Consultorio consultorio = new Consultorio();
		consultorio.setId(2L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-18T03:13");
		entity = validarHoraDataFormato(entity);

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/consulta/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("consultaMedicoConsultorio"), resposta.get("consultaMedicoConsultorio"));
	}

	@Test
	public void testFalhaLimiteConsultaAtingido() {
		Map<String, String> resultadoEsperado = new LinkedHashMap<>();
		resultadoEsperado.put("limiteConsulta",
				"O número máximo de consultas no mesmo consultório por dia (12) atingidos.");
		cadastroMais11Consultas();
		Consulta entity = new Consulta();

		Paciente paciente = new Paciente();
		paciente.setId(14L);

		List<Medico> listaMedico = new ArrayList<Medico>();

		Medico medico = new Medico();
		medico.setId(1L);
		listaMedico.add(medico);

		medico = new Medico();
		listaMedico.add(medico);

		Consultorio consultorio = new Consultorio();
		consultorio.setId(1L);

		entity.setPaciente(paciente);
		entity.setConsultorio(consultorio);
		entity.setListaMedico(listaMedico);

		entity.setDataHoraFormatada("2021-06-18T21:43");
		entity = validarHoraDataFormato(entity);

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/consulta/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("limiteConsulta"), resposta.get("limiteConsulta"));
	}

}
