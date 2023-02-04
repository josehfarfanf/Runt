package com.globant.microservice.empresa.empresaapp.service.Impl;

import com.globant.microservice.empresa.empresaapp.dao.DepartamentoRepository;
import com.globant.microservice.empresa.empresaapp.dao.UsuarioRepository;
import com.globant.microservice.empresa.empresaapp.exceptions.DepartamentoNotFoundException;
import com.globant.microservice.empresa.empresaapp.model.Departamento;
import com.globant.microservice.empresa.empresaapp.model.Usuario;
import com.globant.microservice.empresa.empresaapp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario create(Usuario usuario) {
        this.validarDepartamento(usuario);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> update(Usuario usuario, Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            this.validarDepartamento(usuario);
            Usuario usuarioDb=usuarioOptional.get();
            usuarioDb.setDireccion(usuario.getDireccion());
            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setApellido(usuario.getApellido());
            usuarioDb.setCargo(usuario.getCargo());
            usuarioDb.setDepartamento(usuario.getDepartamento());
            usuarioDb.setTelefono(usuario.getTelefono());
            usuarioDb.setCiudad(usuario.getCiudad());
            usuarioDb.setEstado(usuario.getEstado());
            return Optional.of(usuarioRepository.save(usuarioDb));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> delete(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            usuarioRepository.delete(usuarioOptional.get());
            return usuarioOptional;
        }
        return Optional.empty();
    }

    @Override
    public List<Usuario> findByIdCompania(Long companiaId) {
        return usuarioRepository.findByIdCompania(companiaId);
    }

    @Override
    public Optional<Usuario> enrollar(Departamento departamento, Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuario=usuarioOptional.get();
            usuario.setDepartamento(departamento);
            this.validarDepartamento(usuario);
            usuarioRepository.save(usuario);
            return Optional.of(usuario);
        }
        return Optional.empty();
    }

    private void validarDepartamento(Usuario usuario){
        Optional<Departamento> departamento = departamentoRepository.findById(usuario.getDepartamento().getId());
        if(!departamento.isPresent()){
            throw new DepartamentoNotFoundException("Departamento con id ".concat(usuario.getDepartamento().getId().toString()));
        }
    }

}
