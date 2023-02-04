package com.globant.microservice.empresa.empresaapp.dao;

import com.globant.microservice.empresa.empresaapp.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento,Long> {

}
