package br.com.logusretail.api.testeConsultorio;

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

import br.com.logusretail.model.entities.Consultorio;

class ConsultorioTest {

	@Test
	public void testListaConsultorioNaoNula() {

		List<Consultorio> response = null;

		String endpoint = "http://localhost:8082/api/v1/consultorio/read-all";

		RestTemplate restTemplate = new RestTemplate();
		try {

			ResponseEntity<Consultorio[]> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, null,
					Consultorio[].class);
			Consultorio[] listaConsultorios = requestResponse.getBody();
			response = Arrays.asList(listaConsultorios);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Integer quantidadeListaEsperada = null;
		assertEquals(quantidadeListaEsperada, response);

	}

	@Test
	public void criarConsultorioSucesso() {

		Map<String, String> resultadoEsperado = new LinkedHashMap<>();

		resultadoEsperado.put("criado", "sucesso");
		Consultorio entity = new Consultorio();

		entity.setNumeroConsultorio(1);

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/consultorio/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Consultorio> httpEntity = new HttpEntity<Consultorio>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
		// ----------------------------------------------------------------------
		resultadoEsperado = new LinkedHashMap<>();

		resultadoEsperado.put("criado", "sucesso");
		entity = new Consultorio();

		entity.setNumeroConsultorio(2);

		resposta = null;
		restTemplate = new RestTemplate();

		try {

			HttpEntity<Consultorio> httpEntity = new HttpEntity<Consultorio>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("criado"), resposta.get("criado"));
	}

	@Test
	public void criarConsultorioComCampoNumeroConsultorioVazio() {
		Map<String, String> resultadoEsperado = new LinkedHashMap<>();
		resultadoEsperado.put("consultorioCampo", "Campo consultorio em branco.");
		Consultorio entity = new Consultorio();

		entity.setNumeroConsultorio(null);

		Map<String, String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/consultorio/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Consultorio> httpEntity = new HttpEntity<Consultorio>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertEquals(resultadoEsperado.get("consultorioCampo"), resposta.get("consultorioCampo"));
	}

}
