package com.globant.microservice.empresa.empresaapp.service;

import com.globant.microservice.empresa.empresaapp.model.Departamento;
import com.globant.microservice.empresa.empresaapp.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    public List<Usuario> findAll();

    public Optional<Usuario> findById(Long id);

    public Usuario create(Usuario usuario);

    public Optional<Usuario> update(Usuario usuario, Long id);

    public Optional<Usuario> delete(Long id);

    public List<Usuario> findByIdCompania(Long idCompania);

    public Optional<Usuario> enrollar(Departamento departamento, Long id);
}
