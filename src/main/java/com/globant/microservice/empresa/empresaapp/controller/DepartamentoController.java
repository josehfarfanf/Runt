package com.globant.microservice.empresa.empresaapp.controller;

import com.globant.microservice.empresa.empresaapp.model.Departamento;
import com.globant.microservice.empresa.empresaapp.service.DepartamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public ResponseEntity<List<Departamento>> findAll(){
        return ResponseEntity.ok(departamentoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Departamento> findById(@PathVariable Long id){
        Optional<Departamento> departamento = departamentoService.findById(id);
        if(departamento.isPresent()){
            return ResponseEntity.ok(departamento.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Departamento> create(@Valid @RequestBody Departamento departamento){
        Departamento clientDb = departamentoService.create(departamento);
        return ResponseEntity.created(URI.create("/departamento/".concat(clientDb.getId().toString())))
                .body(clientDb);
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<Departamento> update(@PathVariable Long id,@Valid  @RequestBody Departamento departamento){
        Optional<Departamento>departamentoOptional=departamentoService.update(departamento,id);
        if (departamentoOptional.isPresent()){
            return ResponseEntity.created(URI.create("/departamento/".concat(departamentoOptional.get().getId().toString())))
                    .body(departamentoOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<Departamento> departamento = departamentoService.delete(id);
        if(departamento.isPresent()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
