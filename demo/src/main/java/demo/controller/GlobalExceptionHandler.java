package demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, String>> handleNotFound(RuntimeException ex) {
		
		Map<String, String> error = new HashMap<>();
		error.put("error", ex.getMessage());
		error.put("status", "404");
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleGeneric(Exception ex){

		Map<String, String> error = new HashMap<>();
		error.put("error", "internal server error");
		error.put("status", "500");
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}
