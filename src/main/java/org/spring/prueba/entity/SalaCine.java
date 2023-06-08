package org.spring.prueba.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="sala_cine")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalaCine{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_sala", nullable = false , unique = true , updatable = false)
	private Integer id;
	
	@Column(name="nombre", nullable = false , length = 255, unique = true)
	private String nombre;
	
	@Column(name="estado", nullable = false)
	private String estado;
}
