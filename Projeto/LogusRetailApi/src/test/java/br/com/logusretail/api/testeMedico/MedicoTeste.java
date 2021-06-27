package br.com.logusretail.api.testeMedico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.logusretail.model.entities.Medico;

class MedicoTeste {

	@Test
	public void testListaMedicoNaoNula() {

		List<Medico> response = null;

		String endpoint = "http://localhost:8082/api/v1/medico/read-all";

		RestTemplate restTemplate = new RestTemplate();
		try {

			ResponseEntity<Medico[]> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, null,
					Medico[].class);
			Medico[] listaMedicos = requestResponse.getBody();
			response = Arrays.asList(listaMedicos);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Integer quantidadeListaEsperada = null;
		assertEquals(quantidadeListaEsperada, response);

	}

	@Test
	public void criarMedicoSucesso() {

		Map<String, String> resultadoEsperado = new LinkedHashMap<>();

		resultadoEsperado.put("criado", "sucesso");
		Medico entity = new Medico();

		entity.setNome("Dr. Rodrigo");
		entity.setEspecialidade("Cirurgiao");
		entity.setCrm("15.145");
		entity.setIdade(25);

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/medico/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Medico> httpEntity = new HttpEntity<Medico>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// ------------------------------------------------------------------------
		resultadoEsperado = new LinkedHashMap<>();

		resultadoEsperado.put("criado", "sucesso");
		entity = new Medico();

		entity.setNome("Dr. Simao");
		entity.setEspecialidade("Oftamologista");
		entity.setCrm("15.189");
		entity.setIdade(25);

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Medico> httpEntity = new HttpEntity<Medico>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
	}

	@Test
	public void criarMedicoComCampoNomeVazio() {
		Map<String, String> resultadoEsperado = new LinkedHashMap<>();
		resultadoEsperado.put("medicoCampoNome", "Campo nome em branco.");
		Medico entity = new Medico();

		entity.setNome("");
		entity.setEspecialidade("Cirurgiao");
		entity.setCrm("69.551");
		entity.setIdade(32);

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/medico/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Medico> httpEntity = new HttpEntity<Medico>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("medicoCampoNome"), resposta.get("medicoCampoNome"));
	}

	@Test
	public void criarMedicoComCampoEspecialidadeVazio() {
		Map<String, String> resultadoEsperado = new LinkedHashMap<>();
		resultadoEsperado.put("medicoCampoEspecialidade", "Campo especialidade em branco.");
		Medico entity = new Medico();

		entity.setNome("Dr. Henrique");
		entity.setEspecialidade("");
		entity.setCrm("25.157");
		entity.setIdade(23);

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/medico/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Medico> httpEntity = new HttpEntity<Medico>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("medicoCampoEspecialidade"), resposta.get("medicoCampoEspecialidade"));
	}

	@Test
	public void criarMedicoComCampoCrmVazio() {
		Map<String, String> resultadoEsperado = new LinkedHashMap<>();
		resultadoEsperado.put("medicoCampoCrm", "Campo CRM em branco.");
		Medico entity = new Medico();

		entity.setNome("Dr. Drauzio");
		entity.setEspecialidade("Oftamologista");
		entity.setCrm("");
		entity.setIdade(51);

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/medico/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Medico> httpEntity = new HttpEntity<Medico>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("medicoCampoCrm"), resposta.get("medicoCampoCrm"));
	}
	
	@Test
	public void criarMedicoComCampoCrmDuplicado() {
		Map<String, String> resultadoEsperado = new LinkedHashMap<>();
		resultadoEsperado.put("medicoCampoCrmUnique", "Este CRM já está cadastrado.");
		Medico entity = new Medico();

		entity.setNome("Dr. Drauzio");
		entity.setEspecialidade("Oftamologista");
		entity.setCrm("15.189");
		entity.setIdade(51);

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/medico/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Medico> httpEntity = new HttpEntity<Medico>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("medicoCampoCrmUnique"), resposta.get("medicoCampoCrmUnique"));
	}

	@Test
	public void criarMedicoComCampoIdadeVazio() {
		Map<String, String> resultadoEsperado = new LinkedHashMap<>();
		resultadoEsperado.put("medicoCampoIdade", "Campo idade em branco.");

		Medico entity = new Medico();

		entity.setNome("Dr. Jorge");
		entity.setEspecialidade("Clinico geral");
		entity.setCrm("48.965");
		entity.setIdade(null);

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/medico/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Medico> httpEntity = new HttpEntity<Medico>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("medicoCampoIdade"), resposta.get("medicoCampoIdade"));
	}

	@Test
	public void criarMedicoTodosCamposVazios() {

		Map<String, String> resultadoEsperado = new LinkedHashMap<>();

		resultadoEsperado.put("medicoCampoNome", "Campo nome em branco.");
		resultadoEsperado.put("medicoCampoIdade", "Campo idade em branco.");
		resultadoEsperado.put("medicoCampoCrm", "Campo CRM em branco.");
		resultadoEsperado.put("medicoCampoEspecialidade", "Campo especialidade em branco.");

		Medico entity = new Medico();

		entity.setNome("");
		entity.setEspecialidade("");
		entity.setCrm("");
		entity.setIdade(null);

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/medico/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Medico> httpEntity = new HttpEntity<Medico>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("medicoCampoNome"), resposta.get("medicoCampoNome"));
		assertEquals(resultadoEsperado.get("medicoCampoIdade"), resposta.get("medicoCampoIdade"));
		assertEquals(resultadoEsperado.get("medicoCampoCrm"), resposta.get("medicoCampoCrm"));
		assertEquals(resultadoEsperado.get("medicoCampoEspecialidade"), resposta.get("medicoCampoEspecialidade"));
	}

}
