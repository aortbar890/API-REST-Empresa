package com.empresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Empleado;
import com.empresa.repository.EmpleadoRepository;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> obtenerEmpleados() {
        return empleadoRepository.findAll();
    }

    public List<Empleado> buscarEmpleadosPorCampos(String dni, String nombre, String sexo, Integer categoria, Integer anyos) {
        return empleadoRepository.listarEmpleadosPorCampos(dni, nombre, sexo, categoria, anyos);
    }

    public Empleado buscarEmpleadosPorDni(String dni) {
        return empleadoRepository.findByDniIgnoreCase(dni);
    }

    public Empleado guardarOActualizar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public void eliminarEmpleado(String dni) {
        Optional<Empleado> empleado = Optional.ofNullable(empleadoRepository.findByDniIgnoreCase(dni));
        if (empleado.isPresent()) {
            empleadoRepository.delete(empleado.get());
        } else {
            throw new RuntimeException("Empleado no encontrado con DNI: " + dni);
        }
    }
}
