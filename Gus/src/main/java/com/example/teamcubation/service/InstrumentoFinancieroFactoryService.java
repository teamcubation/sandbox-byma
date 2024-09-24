package com.example.teamcubation.service;


import com.example.teamcubation.model.InstrumentoFinanciero;
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

    public InstrumentoFinanciero crearInstrumento(String nombre, double precio, TipoInstrumentoFinanciero tipo) {
        return instrumentoFinancieroFactory.crearInstrumento(nombre, precio, tipo);
    }
}
