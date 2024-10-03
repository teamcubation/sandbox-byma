package com.example.proyectoSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.proyectoSpringBoot.model.InstrumentoFinanciero;

@Repository
public interface InstrumentoFinancieroRepository extends JpaRepository<InstrumentoFinanciero, Long> {
    boolean existsByNombre(String nombre);
}
