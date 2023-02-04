package com.globant.microservice.empresa.empresaapp.service.Impl;

import com.globant.microservice.empresa.empresaapp.dao.DepartamentoRepository;
import com.globant.microservice.empresa.empresaapp.dao.UsuarioRepository;
import com.globant.microservice.empresa.empresaapp.model.Compania;
import com.globant.microservice.empresa.empresaapp.model.Departamento;
import com.globant.microservice.empresa.empresaapp.model.Usuario;
import com.globant.microservice.empresa.empresaapp.service.UsuarioService;
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
class UsuarioServiceImplTest {

    @MockBean
    DepartamentoRepository departamentoRepository;

    @MockBean
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioService usuarioService;

    @Test
    void findAll() {
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(new Usuario()));

        List<Usuario> departamentoList=usuarioRepository.findAll();

        assertFalse(departamentoList.isEmpty());
        assertEquals(1,departamentoList.size());
        verify(usuarioRepository).findAll();
    }

    @Test
    void findById() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(new Usuario(1L,"Juan","Perez","","","","","",new Departamento())));

        Optional<Usuario> usuarioOptional=usuarioService.findById(1L);

        assertTrue(usuarioOptional.isPresent());
        assertEquals("Juan",usuarioOptional.get().getNombre());
        verify(usuarioRepository).findById(anyLong());
    }

    @Test
    void create() {
        Usuario usuarioNuevo=new Usuario(null,"Juan","Perez","","","","","",new Departamento(1L,"",null));
        when(departamentoRepository.findById(anyLong())).thenReturn(Optional.of(new Departamento()));
        when(usuarioRepository.save(any(Usuario.class))).then(invocationOnMock -> {
            Usuario c=invocationOnMock.getArgument(0);
            c.setId(10L);
            return c;
        });

        Usuario usuarioGuardado= usuarioService.create(usuarioNuevo);

        assertEquals(10L,usuarioGuardado.getId());
        assertEquals("Juan",usuarioGuardado.getNombre());
        verify(usuarioRepository).save(any(Usuario.class));

    }

    @Test
    void update() {
        Usuario usuarioNuevo=new Usuario(10L,"Juan","Perez","","","","","",new Departamento(1L,"",null));
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuarioNuevo));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioNuevo);
        when(departamentoRepository.findById(anyLong())).thenReturn(Optional.of(new Departamento()));


        Optional<Usuario> usuarioGuardado = usuarioService.update(usuarioNuevo, 10L);

        assertTrue(usuarioGuardado.isPresent());
        assertEquals(10L,usuarioGuardado.get().getId());
        assertEquals("Juan",usuarioGuardado.get().getNombre());
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void delete() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(new Usuario(1L,"","","","","","","",new Departamento())));

        usuarioService.delete(10L);

        verify(usuarioRepository).findById(any());
        verify(usuarioRepository).delete(any(Usuario.class));
    }
}