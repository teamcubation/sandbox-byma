package com.example.teamcubation.service;


import com.example.teamcubation.model.InstrumentoDTO.InstrumentoFinancieroDTO;
import com.example.teamcubation.model.instrumentoEnums.TipoInstrumentoFinanciero;
import com.example.teamcubation.repository.InstrumentoFinancieroFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstrumentoFinancieroFactoryService {
    private final InstrumentoFinancieroFactory instrumentoFinancieroFactory;

    @Autowired
    public InstrumentoFinancieroFactoryService(InstrumentoFinancieroFactory instrumentoFinancieroFactory) {
        this.instrumentoFinancieroFactory = instrumentoFinancieroFactory;
    }

    public com.example.teamcubation.model.InstrumentoFinanciero crearInstrumento(InstrumentoFinancieroDTO instrumentoDTO, TipoInstrumentoFinanciero tipo) {
        return instrumentoFinancieroFactory.crear(instrumentoDTO, tipo);
    }
}
