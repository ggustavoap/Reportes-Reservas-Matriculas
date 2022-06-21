package org.reservasApolaya.repository;

import org.reservasApolaya.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarreraRepository 
		extends JpaRepository<Carrera, Integer>{
}
