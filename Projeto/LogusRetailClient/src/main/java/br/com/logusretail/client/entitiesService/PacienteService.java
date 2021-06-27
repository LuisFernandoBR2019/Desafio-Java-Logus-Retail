package br.com.logusretail.client.entitiesService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.logusretail.client.service.IPacienteService;
import br.com.logusretail.model.entities.Paciente;

@Service
public class PacienteService implements IPacienteService {

	@Override
	public List<Paciente> readAll(Map<String, Object> criteria) {
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
		return response;
	}

	@Override
	public Map<String,String> create(Paciente entity) {
		Map<String,String> resposta = null;
		String endpoint = "http://localhost:8082/api/v1/paciente/create";
		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Paciente> httpEntity = new HttpEntity<Paciente>(entity);

			ResponseEntity<?> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
					Map.class);

			resposta = (Map<String, String>) responseEntity.getBody();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return resposta;
	}

	@Override
	public Paciente readById(Long id) {
		Paciente response = null;

		String endpoint = "http://localhost:8082/api/v1/paciente/read-by-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		try {

			HttpEntity<String> requestEntity = new HttpEntity<String>("");
			ResponseEntity<Paciente> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, requestEntity,
					Paciente.class);

			response = requestResponse.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public boolean update(Paciente entity) {
		boolean response = false;

		String endpoint = "http://localhost:8082/api/v1/paciente/update";

		RestTemplate restTemplate = new RestTemplate();

		try {

			HttpEntity<Paciente> httpEntity = new HttpEntity<Paciente>(entity);

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
