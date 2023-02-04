package com.globant.microservice.empresa.empresaapp.service;

import com.globant.microservice.empresa.empresaapp.model.Compania;

import java.util.List;
import java.util.Optional;

public interface CompaniaService {

    public List<Compania> findAll();

    public Optional<Compania> findById(Long id);

    public Compania create(Compania compania);

    public Optional<Compania> update(Compania compania, Long id);

    public Optional<Compania> delete(Long id);
}
