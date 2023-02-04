package com.globant.microservice.empresa.empresaapp.dao;

import com.globant.microservice.empresa.empresaapp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query(value = "from Usuario u where u.departamento.compania.id = :companiaId")
    public List<Usuario> findByIdCompania(@Param("companiaId")Long companiaId);
}
