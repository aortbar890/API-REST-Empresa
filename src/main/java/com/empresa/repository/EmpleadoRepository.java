package com.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.empresa.entity.Empleado;

@Repository
public interface  EmpleadoRepository extends JpaRepository<Empleado, String>{

	Empleado findByDniIgnoreCase(String dni);

    @Query("SELECT e FROM Empleado e " +
           "WHERE (:dni IS NULL OR e.dni LIKE %:dni%) " +
           "AND  (:nombre IS NULL OR e.nombre LIKE %:nombre%)" +
           "AND (:sexo IS NULL OR e.sexo = :sexo) " +
           "AND (:categoria IS NULL OR e.categoria = :categoria) " +
           "AND (:anyos IS NULL OR e.anyos = :anyos)")
    List<Empleado> listarEmpleadosPorCampos(
			@Param("dni") String dni,
            @Param("nombre") String nombre,
            @Param("sexo") String sexo,
            @Param("categoria") Integer categoria,
            @Param("anyos") Integer anyos);
}
