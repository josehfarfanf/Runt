package com.globant.microservice.empresa.empresaapp.service.Impl;

import com.globant.microservice.empresa.empresaapp.dao.CompaniaRepository;
import com.globant.microservice.empresa.empresaapp.model.Compania;
import com.globant.microservice.empresa.empresaapp.service.CompaniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompaniaServiceImpl implements CompaniaService {

    @Autowired
    private CompaniaRepository companiaRepository;

    @Override
    public List<Compania> findAll() {
        return companiaRepository.findAll();
    }

    @Override
    public Optional<Compania> findById(Long id) {
        return companiaRepository.findById(id);
    }

    @Override
    public Compania create(Compania compania) {
        return companiaRepository.save(compania);
    }

    @Override
    public Optional<Compania> update(Compania compania, Long id) {
        Optional<Compania> companiaOptional = companiaRepository.findById(id);
        if(companiaOptional.isPresent()){
            Compania companiaDb=companiaOptional.get();
            companiaDb.setDireccion(compania.getDireccion());
            companiaDb.setNombre(compania.getNombre());
            companiaDb.setCiudad(compania.getCiudad());
            return Optional.of(companiaRepository.save(companiaDb));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Compania> delete(Long id) {
        Optional<Compania> companiaOptional = companiaRepository.findById(id);
        if(companiaOptional.isPresent()){
            companiaRepository.delete(companiaOptional.get());
            return companiaOptional;
        }
        return Optional.empty();
    }
}
