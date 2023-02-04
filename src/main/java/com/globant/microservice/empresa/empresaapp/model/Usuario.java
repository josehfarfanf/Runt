package com.globant.microservice.empresa.empresaapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellido;

    private String cargo;

    private String direccion;

    private String telefono;

    private String ciudad;

    private String estado;

    @ManyToOne()
    @JoinColumn(name = "departamento_id")
    @NotNull
    private Departamento departamento;
}
