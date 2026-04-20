package demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import demo.models.Band;

@Service
public class BandService {
	
	private static final String API_URL = "https://bands-api.vercel.app/api/bands";
	
	@Autowired
	private RestTemplate restTemplate;

	@Cacheable("bands")
	public List<Band> getAllBands() {
		ResponseEntity<Band[]> responseEntity = restTemplate.getForEntity(API_URL, Band[].class);
		return Arrays.asList(responseEntity.getBody());
	}
	

	@Cacheable(value = "bands", key = "#id")
	public Band getBandById(String id) {
		return getAllBands().stream()
				.filter(b -> b.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Band not found!"));
	}
	
	
}
