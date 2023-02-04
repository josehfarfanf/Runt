package com.globant.microservice.empresa.empresaapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.microservice.empresa.empresaapp.model.Departamento;
import com.globant.microservice.empresa.empresaapp.model.Usuario;
import com.globant.microservice.empresa.empresaapp.service.UsuarioService;
import com.globant.microservice.empresa.empresaapp.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsuarioService usuarioService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper=new ObjectMapper();
    }

    @Test
    void findAll() throws Exception {
        List<Usuario> usuarioList= Arrays.asList(new Usuario(1L,"Juan","Perez","","","","","",new Departamento(1L,"",null)));
        when(usuarioService.findAll()).thenReturn(usuarioList);

        mvc.perform(get("/usuario").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(1)));

        verify(usuarioService).findAll();
    }

    @Test
    void findById() throws Exception {
        Usuario usuario=new Usuario(1L,"Juan","Perez","","","","","",new Departamento(1L,"",null));
        when(usuarioService.findById(anyLong())).thenReturn(Optional.of(usuario));

        mvc.perform(get("/usuario/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Juan"));

        verify(usuarioService).findById(anyLong());
    }

    @Test
    void create() throws Exception {
        Usuario usuario=new Usuario(null,"Juan","Perez","","","","","",new Departamento(1L,"",null));
        when(usuarioService.create(any())).then(invocation->{
            Usuario c=invocation.getArgument(0);
            c.setId(10L);
            return c;
        });

        mvc.perform(post("/usuario").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.nombre").value("Juan"));

        verify(usuarioService).create(any(Usuario.class));
    }

    @Test
    void update() throws Exception {
        Usuario usuario=new Usuario(1L,"Juan","Perez","","","","","",new Departamento(1L,"",null));
        when(usuarioService.update(any(Usuario.class),anyLong())).thenReturn(Optional.of(usuario));

        mvc.perform(put("/usuario/1").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Juan"));

        verify(usuarioService).update(any(Usuario.class),anyLong());
    }

    @Test
    void deleteUsuario() throws Exception {
        Usuario usuario=new Usuario(1L,"Juan","Perez","","","","","",new Departamento(1L,"",null));
        when(usuarioService.delete(anyLong())).thenReturn(Optional.of(usuario));

        mvc.perform(delete("/usuario/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(usuarioService).delete(anyLong());
    }
}