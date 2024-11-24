package com.empresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Empleado;
import com.empresa.service.EmpleadoService;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoRestController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<?> obtenerEmpleados(@RequestParam(required = false) String dni,
                                              @RequestParam(required = false) String nombre,
                                              @RequestParam(required = false) String sexo,
                                              @RequestParam(required = false) Integer categoria,
                                              @RequestParam(required = false) Integer anyos) {
        try {
            // Llamada al servicio para obtener los empleados según los filtros
            return ResponseEntity.ok(empleadoService.buscarEmpleadosPorCampos(dni, nombre, sexo, categoria, anyos));
        } catch (Exception ex) {
            // En caso de error general, se responde con código 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error interno del servidor. Por favor intente más tarde.");
        }
    }

    @GetMapping("/{dni}")
    public ResponseEntity<?> obtenerEmpleadoPorDni(@PathVariable String dni) {
        try {
            Empleado empleado = empleadoService.buscarEmpleadosPorDni(dni);
            if (empleado == null) {
                // Si no se encuentra el empleado, se responde con código 404
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("Empleado con DNI " + dni + " no encontrado.");
            }
            return ResponseEntity.ok(empleado);
        } catch (Exception ex) {
            // En caso de error inesperado, se responde con código 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error interno del servidor. Por favor intente más tarde.");
        }
    }

    @PostMapping
    public ResponseEntity<?> crearEmpleado(@RequestBody Empleado empleado) {
        try {
            // Validación o procesos previos pueden ser añadidos aquí
            if (empleado.getDni() == null || empleado.getDni().isEmpty()) {
                // Si el DNI está vacío, se responde con código 400 Bad Request
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body("El DNI es obligatorio.");
            }
            Empleado empleadoGuardado = empleadoService.guardarOActualizar(empleado);
            return ResponseEntity.status(HttpStatus.CREATED).body(empleadoGuardado);
        } catch (Exception ex) {
            // En caso de error general, se responde con código 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al crear el empleado. Por favor intente más tarde.");
        }
    }

    @PutMapping("/{dni}")
    public ResponseEntity<?> actualizarEmpleado(@PathVariable String dni, @RequestBody Empleado empleado) {
        try {
            Empleado empleadoExistente = empleadoService.buscarEmpleadosPorDni(dni);
            if (empleadoExistente == null) {
                // Si el empleado no existe, respondemos con código 404
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("Empleado con DNI " + dni + " no encontrado.");
            }
            empleado.setDni(dni); // Se asegura de que el DNI no cambie
            Empleado empleadoActualizado = empleadoService.guardarOActualizar(empleado);
            return ResponseEntity.ok(empleadoActualizado);
        } catch (Exception ex) {
            // En caso de error inesperado, se responde con código 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al actualizar el empleado. Por favor intente más tarde.");
        }
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable String dni) {
        try {
            empleadoService.eliminarEmpleado(dni);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            // Si el empleado no se encuentra, se responde con código 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Empleado con DNI " + dni + " no encontrado.");
        } catch (Exception ex) {
            // En caso de error inesperado, se responde con código 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al eliminar el empleado. Por favor intente más tarde.");
        }
    }
}
