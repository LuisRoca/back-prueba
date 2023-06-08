package org.spring.prueba.repository;

import java.util.List;

import org.spring.prueba.entity.PeliculaSalaCine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaSalaCineRepository extends CrudRepository<PeliculaSalaCine, Integer>{

	@Query("select pla from PeliculaSalaCine pla where lower(pla.pelicula.nombre) = lower(?1) and lower(pla.salaCine.id) = lower(?2) and pla.pelicula.estado='A' and pla.salaCine.estado='A'")
	PeliculaSalaCine buscarPorNombrePeliculaIdSala(String nombrePelicula, Integer idSala);
	
	@Query("select pla.pelicula.nombre, count(pla.fechaPublicacion), pla.fechaPublicacion from PeliculaSalaCine pla where pla.salaCine.estado='A'"+
			"GROUP BY pla.fechaPublicacion, pla.pelicula.id")
	Object[][] buscarPeliculasCantidad();
	
	@Query("select pla from PeliculaSalaCine pla where lower(pla.salaCine.nombre) = lower(?1) and pla.pelicula.estado='A' and pla.salaCine.estado='A'")
	List<PeliculaSalaCine> buscarSalas(String nombreSala);
}
