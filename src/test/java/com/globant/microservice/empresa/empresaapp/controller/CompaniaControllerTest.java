package com.globant.microservice.empresa.empresaapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.microservice.empresa.empresaapp.model.Compania;
import com.globant.microservice.empresa.empresaapp.service.CompaniaService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CompaniaController.class)
class CompaniaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CompaniaService companiaService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper=new ObjectMapper();
    }

    @Test
    void findAll() throws Exception {
        List<Compania> companiaList= Arrays.asList(new Compania(1L,"Compañía X","",""));
        when(companiaService.findAll()).thenReturn(companiaList);

        mvc.perform(get("/compania").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(1)));

        verify(companiaService).findAll();
    }

    @Test
    void findById() throws Exception {
        Compania compania=new Compania(1L,"Compañía X","","");
        when(companiaService.findById(anyLong())).thenReturn(Optional.of(compania));

        mvc.perform(get("/compania/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Compañía X"));

        verify(companiaService).findById(anyLong());
    }

    @Test
    void create() throws Exception {
        Compania compania=new Compania(null,"Compañía X","Calle 1","Cali");
        when(companiaService.create(any())).then(invocation->{
            Compania c=invocation.getArgument(0);
            c.setId(10L);
            return c;
        });

        mvc.perform(post("/compania").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(compania)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.nombre").value("Compañía X"));

        verify(companiaService).create(any(Compania.class));
    }

    @Test
    void update() throws Exception {
        Compania compania=new Compania(1L,"Compañía X","Calle 1","Cali");
        when(companiaService.update(any(Compania.class),anyLong())).thenReturn(Optional.of(compania));

        mvc.perform(put("/compania/1").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(compania)))
                        .andExpect(status().isCreated())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.id").value(1L))
                        .andExpect(jsonPath("$.nombre").value("Compañía X"));

        verify(companiaService).update(any(Compania.class),anyLong());
    }

    @Test
    void deleteCompania() throws Exception {
        Compania compania=new Compania(1L,"Compañía X","","");
        when(companiaService.delete(anyLong())).thenReturn(Optional.of(compania));

        mvc.perform(delete("/compania/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(companiaService).delete(anyLong());
    }
}