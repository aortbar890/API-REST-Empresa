package com.empresa.controller;

import com.empresa.service.NominaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nominas")
public class NominaRestController {

    @Autowired
    private NominaService nominaService;

    @GetMapping("/salario/{dni}")
    public ResponseEntity<?> obtenerSalarioPorDni(@PathVariable String dni) {
        try {
            Integer salario = nominaService.obtenerSalario(dni);
            if (salario == null) {
                // Si no se encuentra la nómina, se responde con código 404
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("Empleado con DNI " + dni + " no encontrado o nómina no disponible.");
            }
            return ResponseEntity.ok(salario);
        } catch (Exception ex) {
            // En caso de error general, se responde con código 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error interno del servidor. Por favor intente más tarde.");
        }
    }
}
