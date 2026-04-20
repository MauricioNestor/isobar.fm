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

@RestController
@RequestMapping("/demo/bands")
@CrossOrigin(origins = "*")
public class BandController {
	
	@Autowired
	private BandService bandService;
	
	@GetMapping
	public ResponseEntity<List<Band>> getAll() {
		return ResponseEntity.ok(bandService.getAllBands());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Band> getById(@PathVariable String id){
		return ResponseEntity.ok(bandService.getBandById(id));
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Band>> search(@RequestParam String name){
		List<Band> result = bandService.getAllBands().stream()
				.filter(b -> b.getName().toLowerCase().contains(name.toLowerCase()))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(result);
	}

	
	@GetMapping("/genre/{genre}")
	public ResponseEntity<List<Band>> getByGenre(@PathVariable String genre){
		List<Band> result = bandService.getAllBands().stream()
				.filter(b -> b.getGenre().equalsIgnoreCase(genre))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(result);
	}
}
