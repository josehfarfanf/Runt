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
public class Departamento {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String descripcion;

    @ManyToOne()
    @JoinColumn(name = "compania_id")
    @NotNull
    private Compania compania;
}
