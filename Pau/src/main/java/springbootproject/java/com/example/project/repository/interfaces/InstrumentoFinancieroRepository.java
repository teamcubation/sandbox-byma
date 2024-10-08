package com.example.project.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.project.model.instrumentofinanciero.InstrumentoFinanciero;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentoFinancieroRepository extends JpaRepository<InstrumentoFinanciero, Long> {

}
