package isobar.fm.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import demo.models.Band;
import demo.service.BandService;

@ExtendWith(MockitoExtension.class)
public class BandServiceTest {
	
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private BandService bandService;
	
	private Band band1;
	private Band band2;
	
	@BeforeEach
	void setUp() {
		band1 = new Band();
		band1.setId("bc710bcf-8815-42cf-bad2-3f1d12246aeb");
		band1.setName("Nickelback");
		band1.setGenre("Rock");
		
		band2 = new Band();
		band2.setId("e2c00c56-8365-4160-9f40-a64682917633");
		band2.setName("Goo Goo Dolls");
		band2.setGenre("Rock");
	}

	
	@Test
	void testShouldReturnAllBands() {
		Band[] bandsArray = {band1, band2};
		
		when(restTemplate.getForEntity(anyString(), eq(Band[].class)))
			.thenReturn(new ResponseEntity<>(bandsArray, HttpStatus.OK));
			
		List<Band> result = bandService.getAllBands();
		
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getName()).isEqualTo("Nickelback");
		assertThat(result.get(1).getName()).isEqualTo("Goo Goo Dolls");
	}
	
	@Test
	void testShouldReturnBandById() {
		Band[] bandsArray = {band1, band2};
		
		when(restTemplate.getForEntity(anyString(), eq(Band[].class)))
			.thenReturn(new ResponseEntity<>(bandsArray, HttpStatus.OK));
			
		Band result = bandService.getBandById("e2c00c56-8365-4160-9f40-a64682917633");
		
		assertThat(result).isNotNull();
		assertThat(result.getName()).isEqualTo("Goo Goo Dolls");
	}
	
	@Test
	void testShouldThrowExceptionWhenBandNotFound() {
	    Band[] bandsArray = {band1, band2};

	    when(restTemplate.getForEntity(anyString(), eq(Band[].class)))
	        .thenReturn(new ResponseEntity<>(bandsArray, HttpStatus.OK));

	    assertThatThrownBy(() -> bandService.getBandById("non-existent-id"))
	    							.isInstanceOf(RuntimeException.class)
	    							.hasMessage("Band not found!");

	}

	@Test
	void testShouldReturnBandsByGenre() {
	    band2.setGenre("alternative");
	    Band[] bandsArray = {band1, band2};

	    when(restTemplate.getForEntity(anyString(), eq(Band[].class)))
	        .thenReturn(new ResponseEntity<>(bandsArray, HttpStatus.OK));

	    List<Band> result = bandService.getAllBands().stream()
	            .filter(b -> b.getGenre().equalsIgnoreCase("rock"))
	            .collect(Collectors.toList());

	    assertThat(result).hasSize(1);
	    assertThat(result.get(0).getName()).isEqualTo("Nickelback");
	}
	
	@Test
	void testShouldReturnBandsByName() {
	    Band[] bandsArray = {band1, band2};

	    when(restTemplate.getForEntity(anyString(), eq(Band[].class)))
	        .thenReturn(new ResponseEntity<>(bandsArray, HttpStatus.OK));

	    List<Band> result = bandService.getAllBands().stream()
	            .filter(b -> b.getName().equalsIgnoreCase("Nickelback"))
	            .collect(Collectors.toList());

	    assertThat(result).hasSize(1);
	    assertThat(result.get(0).getName()).isEqualTo("Nickelback");
	}
}












