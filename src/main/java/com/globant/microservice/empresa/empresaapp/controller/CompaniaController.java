package com.globant.microservice.empresa.empresaapp.controller;

import com.globant.microservice.empresa.empresaapp.model.Compania;
import com.globant.microservice.empresa.empresaapp.service.CompaniaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compania")
public class CompaniaController {

    @Autowired
    private CompaniaService companiaService;

    @GetMapping
    public ResponseEntity<List<Compania>> findAll(){
        return ResponseEntity.ok(companiaService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Compania> findById(@PathVariable Long id){
        Optional<Compania> compania = companiaService.findById(id);
        if(compania.isPresent()){
            return ResponseEntity.ok(compania.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Compania> create(@Valid @RequestBody Compania compania){
        Compania clientDb = companiaService.create(compania);
        return ResponseEntity.created(URI.create("/compania/".concat(clientDb.getId().toString())))
                .body(clientDb);
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<Compania> update(@PathVariable Long id,@Valid @RequestBody Compania compania){
        Optional<Compania>companiaOptional=companiaService.update(compania,id);
        if (companiaOptional.isPresent()){
            return ResponseEntity.created(URI.create("/compania/".concat(companiaOptional.get().getId().toString())))
                    .body(companiaOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<Compania> compania = companiaService.delete(id);
        if(compania.isPresent()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
