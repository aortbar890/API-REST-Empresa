package com.empresa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "empleados")
public class Empleado implements Persona {
    
    @Id
    @NotNull
    private String dni;

    @NotNull
    private String nombre;

    @NotNull
    @Pattern(regexp = "^[FfMm]$")
    private String sexo;

    @NotNull
    @Min(value = 1)
    @Max(value = 10)
    private Integer categoria;

    @NotNull
    @Min(value = 0)
    private Integer anyos;

    @OneToOne(mappedBy = "empleado", fetch = FetchType.LAZY)
    @JsonBackReference
    private Nomina nomina;

}
