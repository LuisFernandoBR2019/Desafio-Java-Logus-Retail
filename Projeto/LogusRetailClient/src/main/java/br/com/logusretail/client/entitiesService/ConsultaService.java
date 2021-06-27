package br.com.logusretail.client.entitiesService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.logusretail.client.service.IConsultaService;
import br.com.logusretail.model.entities.Consulta;

@Service
public class ConsultaService implements IConsultaService {

	@Override
	public List<Consulta> readAll(Map<String, Object> criteria) {
		List<Consulta> response = null;

		String endpoint = "http://localhost:8082/api/v1/consulta/read-all";

		RestTemplate restTemplate = new RestTemplate();
		try {

			ResponseEntity<Consulta[]> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, null,
					Consulta[].class);
			Consulta[] listaConsulta = requestResponse.getBody();
			response = Arrays.asList(listaConsulta);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return response;
	}

	@Override
	public Map<String,String> create(Consulta entity) {
		Map<String,String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/consulta/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
					Map.class);

			resposta = (Map<String, String>)responseEntity.getBody();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return resposta;
	}

	@Override
	public Consulta readById(Long id) {
		Consulta response = null;

		String endpoint = "http://localhost:8082/api/v1/consulta/read-by-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		try {

			ResponseEntity<Consulta> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, null,
					Consulta.class);

			response = requestResponse.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public boolean update(Consulta entity) {
		boolean response = false;

		String endpoint = "http://localhost:8082/api/v1/consulta/update";

		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Consulta> httpEntity = new HttpEntity<Consulta>(entity);

			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(endpoint, HttpMethod.PUT, httpEntity,
					Boolean.class);

			response = responseEntity.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
