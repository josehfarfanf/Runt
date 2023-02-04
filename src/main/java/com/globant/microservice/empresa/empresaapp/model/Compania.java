package com.globant.microservice.empresa.empresaapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Compania {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String direccion;

    @NotEmpty
    private String ciudad;

}
