package org.spring.prueba.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="pelicula_sala_cine")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaSalaCine{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pelicula_sala", nullable = false , unique = true , updatable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_pelicula", nullable = false)
	private Pelicula pelicula;
	
	@ManyToOne
	@JoinColumn(name="id_sala_cine" , nullable = false)
	private SalaCine salaCine;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_publicacion")
	private Date fechaPublicacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_fin")
	private Date fechaFin;
	
	@Column(name = "estado")
	private String estado;
}
