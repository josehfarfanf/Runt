package com.globant.microservice.empresa.empresaapp.service.Impl;

import com.globant.microservice.empresa.empresaapp.dao.CompaniaRepository;
import com.globant.microservice.empresa.empresaapp.dao.DepartamentoRepository;
import com.globant.microservice.empresa.empresaapp.model.Compania;
import com.globant.microservice.empresa.empresaapp.model.Departamento;
import com.globant.microservice.empresa.empresaapp.service.DepartamentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DepartamentoServiceTest {

    @MockBean
    DepartamentoRepository departamentoRepository;

    @MockBean
    CompaniaRepository companiaRepository;

    @Autowired
    DepartamentoService departamentoService;

    @Test
    void findAll() {
        when(departamentoRepository.findAll()).thenReturn(Arrays.asList(new Departamento()));

        List<Departamento> departamentoList=departamentoRepository.findAll();

        assertFalse(departamentoList.isEmpty());
        assertEquals(1,departamentoList.size());
        verify(departamentoRepository).findAll();
    }

    @Test
    void findById() {
        when(departamentoRepository.findById(anyLong())).thenReturn(Optional.of(new Departamento(1L,"Contable",new Compania())));

        Optional<Departamento> departamentoOptional=departamentoService.findById(1L);

        assertTrue(departamentoOptional.isPresent());
        assertEquals("Contable",departamentoOptional.get().getDescripcion());
        verify(departamentoRepository).findById(anyLong());
    }

    @Test
    void create() {
        Departamento departamentoNuevo=new Departamento(null, "Financiero",new Compania(10L,"","",""));
        when(companiaRepository.findById(anyLong())).thenReturn(Optional.of(new Compania()));
        when(departamentoRepository.save(any(Departamento.class))).then(invocationOnMock -> {
            Departamento c=invocationOnMock.getArgument(0);
            c.setId(10L);
            return c;
        });

        Departamento departamentoGuardado= departamentoService.create(departamentoNuevo);

        assertEquals(10L,departamentoGuardado.getId());
        assertEquals("Financiero",departamentoGuardado.getDescripcion());
        verify(departamentoRepository).save(any(Departamento.class));

    }

    @Test
    void update() {
        Departamento departamento=new Departamento(10L, "Compras",new Compania(10L,"","",""));
        when(departamentoRepository.findById(anyLong())).thenReturn(Optional.of(departamento));
        when(departamentoRepository.save(any(Departamento.class))).thenReturn(departamento);
        when(companiaRepository.findById(anyLong())).thenReturn(Optional.of(new Compania()));

        Optional<Departamento> departamentoGuardado = departamentoService.update(departamento, 1L);

        assertTrue(departamentoGuardado.isPresent());
        assertEquals(10L,departamentoGuardado.get().getId());
        assertEquals("Compras",departamentoGuardado.get().getDescripcion());
        verify(departamentoRepository).save(any(Departamento.class));
    }

    @Test
    void delete() {
        when(departamentoRepository.findById(anyLong())).thenReturn(Optional.of(new Departamento(1L,"TI",new Compania())));

        departamentoService.delete(10L);

        verify(departamentoRepository).findById(any());
        verify(departamentoRepository).delete(any(Departamento.class));
    }
}