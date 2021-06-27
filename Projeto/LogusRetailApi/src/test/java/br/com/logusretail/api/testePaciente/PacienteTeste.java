package br.com.logusretail.api.testePaciente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.web.client.RestTemplate;

import br.com.logusretail.model.entities.Paciente;

class PacienteTeste {

	@Test
	public void testListaPacienteNaoNula() {

		List<Paciente> response = null;

		String endpoint = "http://localhost:8082/api/v1/paciente/read-all";

		RestTemplate restTemplate = new RestTemplate();
		try {

			ResponseEntity<Paciente[]> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, null,
					Paciente[].class);
			Paciente[] listaPacientes = requestResponse.getBody();
			response = Arrays.asList(listaPacientes);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Integer quantidadeListaEsperada = null;
		assertNotEquals(quantidadeListaEsperada, response);

	}

	@Test
	public void criarPacienteSucesso() {

		Map<String, String> resultadoEsperado = new LinkedHashMap<>();

		resultadoEsperado.put("criado", "sucesso");
		Paciente entity = new Paciente();

		entity.setNome("Marcos Moreira");

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/paciente/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Paciente> httpEntity = new HttpEntity<Paciente>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// -----------------------------------------------------------------------
		entity = new Paciente();

		entity.setNome("Reinaldo Moreira");

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Paciente> httpEntity = new HttpEntity<Paciente>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// -----------------------------------------------------------------------
		entity = new Paciente();

		entity.setNome("Ronaldo Moreira");

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Paciente> httpEntity = new HttpEntity<Paciente>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// -----------------------------------------------------------------------
		entity = new Paciente();

		entity.setNome("Antonio Moreira");

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Paciente> httpEntity = new HttpEntity<Paciente>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// -----------------------------------------------------------------------
		entity = new Paciente();

		entity.setNome("Bruno");

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Paciente> httpEntity = new HttpEntity<Paciente>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// -----------------------------------------------------------------------
		entity = new Paciente();

		entity.setNome("Rafael");

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Paciente> httpEntity = new HttpEntity<Paciente>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// -----------------------------------------------------------------------
		entity = new Paciente();

		entity.setNome("Guilherme");

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Paciente> httpEntity = new HttpEntity<Paciente>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// -----------------------------------------------------------------------
		entity = new Paciente();

		entity.setNome("Yaclara");

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Paciente> httpEntity = new HttpEntity<Paciente>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// -----------------------------------------------------------------------
		entity = new Paciente();

		entity.setNome("Angela");

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Paciente> httpEntity = new HttpEntity<Paciente>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// -----------------------------------------------------------------------
		entity = new Paciente();

		entity.setNome("Monique");

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Paciente> httpEntity = new HttpEntity<Paciente>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// -----------------------------------------------------------------------
		entity = new Paciente();

		entity.setNome("Eduarda");

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Paciente> httpEntity = new HttpEntity<Paciente>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// -----------------------------------------------------------------------
		entity = new Paciente();

		entity.setNome("Rodrigo");

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Paciente> httpEntity = new HttpEntity<Paciente>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// -----------------------------------------------------------------------
		entity = new Paciente();

		entity.setNome("Vinicius");

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Paciente> httpEntity = new HttpEntity<Paciente>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));

		// -----------------------------------------------------------------------
		entity = new Paciente();

		entity.setNome("Antonela");

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Paciente> httpEntity = new HttpEntity<Paciente>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
	}

	@Test
	public void criarPacienteComCampoNomeVazio() {
		Map<String, String> resultadoEsperado = new LinkedHashMap<>();
		resultadoEsperado.put("pacienteCampo", "Campo paciente em branco.");
		Paciente entity = new Paciente();

		entity.setNome("");

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/paciente/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Paciente> httpEntity = new HttpEntity<Paciente>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("pacienteCampo"), resposta.get("pacienteCampo"));
	}

}
