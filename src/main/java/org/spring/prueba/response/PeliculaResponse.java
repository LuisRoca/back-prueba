package org.spring.prueba.response;

import org.spring.prueba.entity.Pelicula;

import lombok.Data;

@Data
public class PeliculaResponse {
	
	public PeliculaResponse(Pelicula pelicula) {
		this.nombre = pelicula.getNombre();
		this.duracion = pelicula.getDuracion();
	}
	
	private String nombre;
	private Integer duracion;
}
