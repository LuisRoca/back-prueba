package org.spring.prueba.response;

import java.util.Date;

import lombok.Data;

@Data
public class PeliculaSalaCineResponse {

	public PeliculaSalaCineResponse(String nombrePelicula, Integer duracion, 
			String nombreSala, Date fechaPublicacion , Date fechaFin) {
		this.nombrePelicula = nombrePelicula;
		this.nombreSala = nombreSala;
		this.duracion = duracion;
		this.fechaPublicacion = fechaPublicacion;
		this.fechaFin = fechaFin;
	}
	
	public PeliculaSalaCineResponse() {
		
	}
	
	private String nombrePelicula;
	private Integer duracion;
	private String nombreSala;
	private Date fechaPublicacion;
	private Date fechaFin;
}
