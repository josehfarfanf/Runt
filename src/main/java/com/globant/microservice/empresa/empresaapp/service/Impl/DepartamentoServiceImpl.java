package com.globant.microservice.empresa.empresaapp.service.Impl;

import com.globant.microservice.empresa.empresaapp.dao.CompaniaRepository;
import com.globant.microservice.empresa.empresaapp.dao.DepartamentoRepository;
import com.globant.microservice.empresa.empresaapp.exceptions.CompaniaNotFoundException;
import com.globant.microservice.empresa.empresaapp.model.Compania;
import com.globant.microservice.empresa.empresaapp.model.Departamento;
import com.globant.microservice.empresa.empresaapp.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private CompaniaRepository companiaRepository;

    @Override
    public List<Departamento> findAll() {
        return departamentoRepository.findAll();
    }

    @Override
    public Optional<Departamento> findById(Long id) {
        return departamentoRepository.findById(id);
    }

    @Override
    public Departamento create(Departamento departamento) {
        this.validarCompania(departamento);
        return departamentoRepository.save(departamento);
    }

    @Override
    public Optional<Departamento> update(Departamento departamento, Long id) {
        Optional<Departamento> departamentoOptional = departamentoRepository.findById(id);
        if(departamentoOptional.isPresent()){
            this.validarCompania(departamento);
            Departamento departamentoDb=departamentoOptional.get();
            departamentoDb.setCompania(departamento.getCompania());
            departamentoDb.setDescripcion(departamento.getDescripcion());
            return Optional.of(departamentoRepository.save(departamentoDb));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Departamento> delete(Long id) {
        Optional<Departamento> departamentoOptional = departamentoRepository.findById(id);
        if(departamentoOptional.isPresent()){
            departamentoRepository.delete(departamentoOptional.get());
            return departamentoOptional;
        }
        return Optional.empty();
    }

    private void validarCompania(Departamento departamento){
        Optional<Compania> compania = companiaRepository.findById(departamento.getCompania().getId());
        if(!compania.isPresent()){
            throw new CompaniaNotFoundException("Compañía con id ".concat(departamento.getCompania().getId().toString()));
        }
    }
}
