package com.example.teamcubation.repository;


import com.example.teamcubation.model.InstrumentoDTO.InstrumentoFinancieroDTO;
import com.example.teamcubation.model.instrumentoEnums.TipoInstrumentoFinanciero;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentoFinancieroFactory {
    com.example.teamcubation.model.InstrumentoFinanciero crear(InstrumentoFinancieroDTO instrumentoDTO, TipoInstrumentoFinanciero tipo);
}
