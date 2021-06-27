package br.com.logusretail.client.entitiesService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.logusretail.client.service.IConsultorioService;
import br.com.logusretail.model.entities.Consultorio;

@Service
public class ConsultorioService implements IConsultorioService {

	@Override
	public List<Consultorio> readAll(Map<String, Object> criteria) {
		List<Consultorio> response = null;

		String endpoint = "http://localhost:8082/api/v1/consultorio/read-all";

		RestTemplate restTemplate = new RestTemplate();
		try {

			ResponseEntity<Consultorio[]> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, null,
					Consultorio[].class);
			Consultorio[] listaConsultorio = requestResponse.getBody();
			response = Arrays.asList(listaConsultorio);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return response;
	}

	@Override
	public Map<String, String> create(Consultorio entity) {
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

		return resposta;
	}

	@Override
	public Consultorio readById(Long id) {
		Consultorio response = null;

		String endpoint = "http://localhost:8082/api/v1/consultorio/read-by-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		try {

			ResponseEntity<Consultorio> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, null,
					Consultorio.class);

			response = requestResponse.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public boolean update(Consultorio entity) {
		boolean response = false;

		String endpoint = "http://localhost:8082/api/v1/consultorio/update";

		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Consultorio> httpEntity = new HttpEntity<Consultorio>(entity);

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
