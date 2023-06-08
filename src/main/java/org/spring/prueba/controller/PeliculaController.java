package org.spring.prueba.controller;

import java.util.List;

import org.spring.prueba.entity.Pelicula;
import org.spring.prueba.exepciones.MyException;
import org.spring.prueba.helper.SchemaHelper;
import org.spring.prueba.request.PeliculaRequest;
import org.spring.prueba.response.OutputEntity;
import org.spring.prueba.response.PeliculaResponse;
import org.spring.prueba.services.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {
	
	@Autowired
	private PeliculaService peliculaService;
	
	@Autowired
	private SchemaHelper schemaHelper;
	
	@PostMapping("/insertarPelicula")
	public ResponseEntity<OutputEntity<String>> insertarPelicula(@RequestBody PeliculaRequest pelicula) {
		OutputEntity<String> out = null;
		try {
			this.schemaHelper.validateJsonSchema("/jsonSchemas/peliculaSchema.json", pelicula);
			out = this.peliculaService.insertarPelicula(pelicula);
			return new ResponseEntity<OutputEntity<String>>(out, out.getCode());
		} catch (MyException e) {
			out = new OutputEntity<String>().failed(e.getCode(), e.getMessages(), null);
			return new ResponseEntity<OutputEntity<String>>(out, out.getCode());
		}catch (Exception e) {
			out.error();
			return new ResponseEntity<OutputEntity<String>>(out, out.getCode());
		}
	}
	
	@PostMapping("/actualizarPelicula")
	public ResponseEntity<OutputEntity<String>> actualizarPelicula(@RequestBody Pelicula pelicula) {
		OutputEntity<String> out = null;
		try {
			this.schemaHelper.validateJsonSchema("/jsonSchemas/peliculaEditarSchema.json", pelicula);
			out = this.peliculaService.actualizarPelicula(pelicula);
			return new ResponseEntity<OutputEntity<String>>(out, out.getCode());
		} catch (MyException e) {
			out = new OutputEntity<String>().failed(e.getCode(), e.getMessages(), null);
			return new ResponseEntity<OutputEntity<String>>(out, out.getCode());
		}catch (Exception e) {
			out.error();
			return new ResponseEntity<OutputEntity<String>>(out, out.getCode());
		}
	}
	
	@GetMapping("/consultarPeliculas")
	public ResponseEntity<OutputEntity<List<PeliculaResponse>>> consultarPeliculas() {
		OutputEntity<List<PeliculaResponse>> out = null;
		try {
			out = this.peliculaService.getPelicula();
			return new ResponseEntity<OutputEntity<List<PeliculaResponse>>>(out, out.getCode());
		} catch (Exception e) {
			out.error();
			return new ResponseEntity<OutputEntity<List<PeliculaResponse>>>(out, out.getCode());
		}
	}
	
	@DeleteMapping("/eliminarPelicula/{id}")
	public ResponseEntity<OutputEntity<String>> eliminarPelicula(@PathVariable Integer id) {
		OutputEntity<String> out = null;
		try {
			//this.schemaHelper.validateJsonSchema("/jsonSchemas/peliculaEditarSchema.json", pelicula);
			out = this.peliculaService.eliminarPelicula(id);
			return new ResponseEntity<OutputEntity<String>>(out, out.getCode());
		} catch (Exception e) {
			out.error();
			return new ResponseEntity<OutputEntity<String>>(out, out.getCode());
		}
	}
}
