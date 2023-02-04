package com.globant.microservice.empresa.empresaapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.microservice.empresa.empresaapp.model.Compania;
import com.globant.microservice.empresa.empresaapp.model.Departamento;
import com.globant.microservice.empresa.empresaapp.service.DepartamentoService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartamentoController.class)
class DepartamentoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DepartamentoService departamentoService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper=new ObjectMapper();
    }

    @Test
    void findAll() throws Exception {
        List<Departamento> departamentoList= Arrays.asList(new Departamento(1L,"TI",new Compania()));
        when(departamentoService.findAll()).thenReturn(departamentoList);

        mvc.perform(get("/departamento").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(1)));

        verify(departamentoService).findAll();
    }

    @Test
    void findById() throws Exception {
        Departamento departamento=new Departamento(1L,"TI",new Compania());
        when(departamentoService.findById(anyLong())).thenReturn(Optional.of(departamento));

        mvc.perform(get("/departamento/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.descripcion").value("TI"));

        verify(departamentoService).findById(anyLong());
    }

    @Test
    void create() throws Exception {
        Departamento departamento=new Departamento(null,"TI",new Compania());
        when(departamentoService.create(any())).then(invocation->{
            Departamento c=invocation.getArgument(0);
            c.setId(10L);
            return c;
        });

        mvc.perform(post("/departamento").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departamento)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.descripcion").value("TI"));

        verify(departamentoService).create(any(Departamento.class));
    }

    @Test
    void update() throws Exception {
        Departamento departamento=new Departamento(1L,"TI",new Compania());
        when(departamentoService.update(any(Departamento.class),anyLong())).thenReturn(Optional.of(departamento));

        mvc.perform(put("/departamento/1").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departamento)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.descripcion").value("TI"));

        verify(departamentoService).update(any(Departamento.class),anyLong());
    }

    @Test
    void deleteDepartamento() throws Exception {
        Departamento departamento=new Departamento(1L,"TI",new Compania());
        when(departamentoService.delete(anyLong())).thenReturn(Optional.of(departamento));

        mvc.perform(delete("/departamento/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(departamentoService).delete(anyLong());
    }
}