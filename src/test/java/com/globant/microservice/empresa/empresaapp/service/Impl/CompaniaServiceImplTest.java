package com.globant.microservice.empresa.empresaapp.service.Impl;

import com.globant.microservice.empresa.empresaapp.dao.CompaniaRepository;
import com.globant.microservice.empresa.empresaapp.model.Compania;
import com.globant.microservice.empresa.empresaapp.service.CompaniaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class CompaniaServiceImplTest {

    @MockBean
    CompaniaRepository companiaRepository;

    @Autowired
    CompaniaService companiaService;

    @Test
    void findAll() {
        when(companiaRepository.findAll()).thenReturn(Arrays.asList(new Compania()));

        List<Compania> companiaList=companiaService.findAll();

        assertFalse(companiaList.isEmpty());
        assertEquals(1,companiaList.size());
        verify(companiaRepository).findAll();
    }

    @Test
    void findById() {
        when(companiaRepository.findById(anyLong())).thenReturn(Optional.of(new Compania(1L,"Runt","Calle 1 y Calle 2", "Bogotá")));

        Optional<Compania> companiaOptional=companiaService.findById(1L);

        assertTrue(companiaOptional.isPresent());
        assertEquals("Runt",companiaOptional.get().getNombre());
        verify(companiaRepository).findById(anyLong());
    }

    @Test
    void create() {
        Compania companiaNueva=new Compania(null, "Compañía X","Calle I y calle II","Bogotá");
        when(companiaRepository.save(any(Compania.class))).then(invocationOnMock -> {
            Compania c=invocationOnMock.getArgument(0);
            c.setId(10L);
            return c;
        });

        Compania companiaGuardada= companiaService.create(companiaNueva);

        assertEquals(10L,companiaGuardada.getId());
        assertEquals("Compañía X",companiaGuardada.getNombre());
        verify(companiaRepository).save(any(Compania.class));

    }

    @Test
    void update() {
        Compania compania=new Compania(10L, "Compañía X","Calle I y calle II","Bogotá");
        when(companiaRepository.findById(anyLong())).thenReturn(Optional.of(compania));
        when(companiaRepository.save(any(Compania.class))).thenReturn(compania);

        Optional<Compania> companiaGuardada = companiaService.update(compania, 10L);

        assertTrue(companiaGuardada.isPresent());
        assertEquals(10L,companiaGuardada.get().getId());
        assertEquals("Compañía X",companiaGuardada.get().getNombre());
        verify(companiaRepository).save(any(Compania.class));
    }

    @Test
    void delete() {
        when(companiaRepository.findById(anyLong())).thenReturn(Optional.of(new Compania(1L,"Runt","Calle 1 y Calle 2", "Bogotá")));

        companiaService.delete(10L);

        verify(companiaRepository).findById(any());
        verify(companiaRepository).delete(any(Compania.class));
    }
}