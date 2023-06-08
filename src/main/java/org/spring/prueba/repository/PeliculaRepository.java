package org.spring.prueba.repository;

import org.spring.prueba.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer>{

	@Procedure("existe_nombre")
	Integer existeNombre(String nom);
	
	@Query(value = "select b from Pelicula b where b.id = ?1 ", nativeQuery = false)
	Pelicula buscarPorId(Integer id);
	
}
