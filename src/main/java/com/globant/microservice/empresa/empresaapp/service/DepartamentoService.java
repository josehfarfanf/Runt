package com.globant.microservice.empresa.empresaapp.service;

import com.globant.microservice.empresa.empresaapp.model.Departamento;

import java.util.List;
import java.util.Optional;

public interface DepartamentoService {

    public List<Departamento> findAll();

    public Optional<Departamento> findById(Long id);

    public Departamento create(Departamento departamento);

    public Optional<Departamento> update(Departamento departamento, Long id);

    public Optional<Departamento> delete(Long id);
}
