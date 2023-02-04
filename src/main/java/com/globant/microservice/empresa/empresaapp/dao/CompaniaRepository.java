package com.globant.microservice.empresa.empresaapp.dao;

import com.globant.microservice.empresa.empresaapp.model.Compania;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompaniaRepository extends JpaRepository<Compania,Long> {

}
