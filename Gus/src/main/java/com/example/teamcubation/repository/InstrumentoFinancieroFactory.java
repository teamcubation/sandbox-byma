package com.example.teamcubation.repository;


import com.example.teamcubation.model.InstrumentoFinanciero;
import com.example.teamcubation.model.instrumentoEnums.TipoInstrumentoFinanciero;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentoFinancieroFactory {
    InstrumentoFinanciero crearInstrumento(String nombre, double precio, TipoInstrumentoFinanciero tipo);
}
