package org.spring.prueba.controller;

import java.util.List;
import java.util.Map;

import org.spring.prueba.response.OutputEntity;
import org.spring.prueba.response.PeliculaSalaCineResponse;
import org.spring.prueba.services.PeliculaSalaCineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/peliculaSala")
public class PeliculaSalaCineController {
	
	@Autowired
	private PeliculaSalaCineService peliculaSalaService;

	@GetMapping("/consultarPeliculaSala/{nombrePelicula}/{idSala}")
	public ResponseEntity<OutputEntity<PeliculaSalaCineResponse>> consultarPeliculaSala(@PathVariable String nombrePelicula, @PathVariable Integer idSala) {
		OutputEntity<PeliculaSalaCineResponse> out = null;
		try {
			out = this.peliculaSalaService.consultarPeliculaNombreIdSala(nombrePelicula, idSala);
			return new ResponseEntity<OutputEntity<PeliculaSalaCineResponse>>(out, out.getCode());
		} catch (Exception e) {
			out.error();
			return new ResponseEntity<OutputEntity<PeliculaSalaCineResponse>>(out, out.getCode());
		}
	}
	
	@GetMapping("/consultarPeliculasCantidad")
	public ResponseEntity<OutputEntity<List<Map<String, Object>>>> consultarPeliculasCantidad() {
		OutputEntity<List<Map<String, Object>>> out = null;
		try {
			out = this.peliculaSalaService.consultarPeliculasCantidad();
			return new ResponseEntity<OutputEntity<List<Map<String, Object>>>>(out, out.getCode());
		} catch (Exception e) {
			out.error();
			return new ResponseEntity<OutputEntity<List<Map<String, Object>>>>(out, out.getCode());
		}
	}
}
