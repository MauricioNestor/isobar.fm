package demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.models.Band;
import demo.service.BandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/demo/bands")
@CrossOrigin(origins = "*")
@Tag(name="Bands", description = "endpoints for band consulting")
public class BandController {
	
	@Autowired
	private BandService bandService;
	
	@GetMapping
	@Operation(summary = "list all bands")
	public ResponseEntity<List<Band>> getAll() {
		return ResponseEntity.ok(bandService.getAllBands());
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "searches band by ID")
	public ResponseEntity<Band> getById(@PathVariable String id){
		return ResponseEntity.ok(bandService.getBandById(id));
	}
	
	@GetMapping("/search")
	@Operation(summary = "searches band by Name")
	public ResponseEntity<List<Band>> getByName(@RequestParam String name){
		List<Band> result = bandService.getAllBands().stream()
				.filter(b -> b.getName().toLowerCase().contains(name.toLowerCase()))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(result);
	}

	
	@GetMapping("/genre/{genre}")
	@Operation(summary = "searches band by genre")
	public ResponseEntity<List<Band>> getByGenre(@PathVariable String genre){
		List<Band> result = bandService.getAllBands().stream()
				.filter(b -> b.getGenre().equalsIgnoreCase(genre))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(result);
	}
}
