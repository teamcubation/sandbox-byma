package com.example.teamcubation.repository.interfaces;


import com.example.teamcubation.model.InstrumentoFinanciero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentoFinancieroRepository extends JpaRepository<InstrumentoFinanciero, Long> {


}
