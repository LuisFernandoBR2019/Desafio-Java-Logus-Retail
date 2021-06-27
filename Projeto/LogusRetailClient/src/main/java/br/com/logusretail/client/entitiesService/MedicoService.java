package br.com.logusretail.client.entitiesService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.logusretail.client.service.IMedicoService;
import br.com.logusretail.model.entities.Medico;

@Service
public class MedicoService implements IMedicoService {

	@Override
	public List<Medico> readAll(Map<String, Object> criteria) {
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
		return response;
	}

	@Override
	public Map<String, String> create(Medico entity) {
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

		return resposta;
	}

	@Override
	public Medico readById(Long id) {
		Medico response = null;

		String endpoint = "http://localhost:8082/api/v1/medico/read-by-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		try {

			ResponseEntity<Medico> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, null,
					Medico.class);

			response = requestResponse.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public boolean update(Medico entity) {
		boolean response = false;

		String endpoint = "http://localhost:8082/api/v1/medico/update";

		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Medico> httpEntity = new HttpEntity<Medico>(entity);

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
