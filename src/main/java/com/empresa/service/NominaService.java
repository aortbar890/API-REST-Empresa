package com.empresa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Nomina;
import com.empresa.repository.NominaRepository;

@Service
public class NominaService {

    @Autowired
    private NominaRepository nominaRepository;

    public Integer obtenerSalario(String dni) {
        Nomina nomina = nominaRepository.findByEmpleadoDniIgnoreCase(dni);
        if (nomina != null) {
            return nomina.getSueldo();
        }
        return null;
    }
}
