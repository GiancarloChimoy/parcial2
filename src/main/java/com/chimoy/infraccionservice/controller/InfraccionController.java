package com.chimoy.infraccionservice.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chimoy.infraccionservice.entity.Infraccion;
import com.chimoy.infraccionservice.service.InfraccionService;

@RestController
@RequestMapping("infracciones")
public class InfraccionController {
	
	@Autowired
	private InfraccionService service;

	@GetMapping()
	public ResponseEntity<List<Infraccion>> findAll(
			@RequestParam(value = "fecha", required = false, defaultValue = " ")LocalDateTime fecha,
			@RequestParam(value = "offset", required = false, defaultValue = "0")int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5")int pageSize){
		
		Pageable page= PageRequest.of(pageNumber, pageSize);
		List<Infraccion> infracciones;
		if(fecha== null ) {
			infracciones = service.findAll(page);
			
		}else {
			infracciones = service.findByFecha(fecha, page);
		}
		
		if(infracciones.isEmpty()) {
			return ResponseEntity.noContent().build();
			
		}
		return ResponseEntity.ok(infracciones);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Infraccion> findById(@PathVariable("id")ObjectId id){
		
		Infraccion registro = service.findById(id);
		if(registro==null) {
			return ResponseEntity.notFound().build();
			
		}
		return ResponseEntity.ok(registro);
		
	}
	
	@PostMapping()
	 public ResponseEntity<Infraccion> create(@RequestBody Infraccion infraccion){
			
			Infraccion registro = service.save(infraccion);
			return ResponseEntity.status(HttpStatus.CREATED).body(registro);
		}
	
	@GetMapping("/usuario/{dni}") // Añadir este endpoint
    public ResponseEntity<List<Infraccion>> findByDni(@PathVariable("dni") String dni) {
        List<Infraccion> infracciones = service.findByDni(dni);
        if (infracciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(infracciones);
    }
	
	@PutMapping(value="/{id}")
    public ResponseEntity<Infraccion> update(@PathVariable("id") ObjectId id, @RequestBody Infraccion infraccion){
		
		Infraccion registro = service.update(infraccion);
		if(registro==null) {
			return ResponseEntity.notFound().build();
			
		}
		return ResponseEntity.ok(registro);
	}
	
}
