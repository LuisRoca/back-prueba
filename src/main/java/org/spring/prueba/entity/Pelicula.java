package org.spring.prueba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.spring.prueba.request.PeliculaRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="pelicula")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pelicula", nullable = false , unique = true , updatable = false)
	private Integer id;
	
	@Column(name="nombre", nullable = false , length = 255, unique = true)
	private String nombre;
	
	@Column(name="duracion", nullable = false)
	private Integer duracion;
	
	@Column(name="estado")
	private String estado;
	
	public Pelicula(PeliculaRequest e) {
		this.nombre = e.getNombre();
		this.duracion = e.getDuracion();
		this.estado = "A";
	}
	
	public Pelicula(Pelicula e) {
		this.id = e.getId();
		this.nombre = e.getNombre();
		this.duracion = e.getDuracion();
		this.estado = "A";
	}
	
}
