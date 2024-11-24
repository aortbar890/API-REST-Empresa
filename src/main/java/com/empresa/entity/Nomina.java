package com.empresa.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nominas")
public class Nomina {

    @Id
    @NotNull
    @Column(insertable = false, updatable = false)
    private String empleado_dni;
    
    @NotNull
    @Column(insertable = false, updatable = false)
    private Integer sueldo;

    @OneToOne
    @JsonManagedReference  
    private Empleado empleado;

}
