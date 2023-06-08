package org.spring.prueba.repository;

import org.spring.prueba.entity.SalaCine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SalaCineRepository extends JpaRepository<SalaCine, Integer>{

	@Query(value = "select b from SalaCine b where b.id = ?1 ", nativeQuery = false)
	SalaCine buscarPorId(Integer id);
}
