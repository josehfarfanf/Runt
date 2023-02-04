package com.globant.microservice.empresa.empresaapp.controller;

import com.globant.microservice.empresa.empresaapp.model.Departamento;
import com.globant.microservice.empresa.empresaapp.model.Usuario;
import com.globant.microservice.empresa.empresaapp.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioService.findById(id);
        if(usuario.isPresent()){
            return ResponseEntity.ok(usuario.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@Valid @RequestBody Usuario usuario){
        Usuario clientDb = usuarioService.create(usuario);
        return ResponseEntity.created(URI.create("/usuario/".concat(clientDb.getId().toString())))
                .body(clientDb);
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id,@Valid  @RequestBody Usuario usuario){
        Optional<Usuario>usuarioOptional=usuarioService.update(usuario,id);
        if (usuarioOptional.isPresent()){
            return ResponseEntity.created(URI.create("/usuario/".concat(usuarioOptional.get().getId().toString())))
                    .body(usuarioOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioService.delete(id);
        if(usuario.isPresent()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/compania/{idCompania}")
    public ResponseEntity<List<Usuario>> findByIdCompania(@PathVariable Long idCompania){
       List<Usuario> usuarioList = usuarioService.findByIdCompania(idCompania);
        if(usuarioList.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(usuarioList);
        }
    }

    @PatchMapping(value ="/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Departamento departamento){
        Optional<Usuario>usuarioOptional=usuarioService.enrollar(departamento,id);
        if (usuarioOptional.isPresent()){
            return ResponseEntity.created(URI.create("/usuario/".concat(usuarioOptional.get().getId().toString())))
                    .body(usuarioOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
