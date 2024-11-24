package com.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.entity.Nomina;

@Repository
public interface NominaRepository extends JpaRepository<Nomina, String> {
	
    Nomina findByEmpleadoDniIgnoreCase(String empleado_dni);

}
