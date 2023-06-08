package org.spring.prueba.controller;

import org.spring.prueba.response.OutputEntity;
import org.spring.prueba.services.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salas")
public class SalaController {

	@Autowired
	private SalaService salaService;
	
	@GetMapping("/consultarSalas/{nombreSala}")
	public ResponseEntity<OutputEntity<String>> consultarSalas(@PathVariable String nombreSala) {
		OutputEntity<String> out = null;
		try {
			out = this.salaService.getSalas(nombreSala);
			return new ResponseEntity<OutputEntity<String>>(out, out.getCode());
		} catch (Exception e) {
			out.error();
			return new ResponseEntity<OutputEntity<String>>(out, out.getCode());
		}
	}
}
