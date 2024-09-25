package com.example.teamcubation.repository;


import com.example.teamcubation.model.Accion;
import com.example.teamcubation.model.Bono;
import com.example.teamcubation.model.InstrumentoDTO.InstrumentoFinancieroDTO;
import com.example.teamcubation.model.instrumentoEnums.TipoInstrumentoFinanciero;
import com.example.teamcubation.repository.interfaces.InstrumentoFinancieroFactory;
import org.springframework.stereotype.Repository;

@Repository
public class InstrumentoFinancieroFactoryImpl implements InstrumentoFinancieroFactory {
    @Override
    public com.example.teamcubation.model.InstrumentoFinanciero crear(InstrumentoFinancieroDTO instrumentoDTO, TipoInstrumentoFinanciero tipo) {
        com.example.teamcubation.model.InstrumentoFinanciero nuevoInstrumentoFinanciero = null;
        if (tipo.equals(TipoInstrumentoFinanciero.ACCION)) {
            nuevoInstrumentoFinanciero = new Accion(instrumentoDTO.getNombreInstrumento(), instrumentoDTO.getPrecio());
        }


        if (tipo.equals(TipoInstrumentoFinanciero.BONO)) {
            nuevoInstrumentoFinanciero = new Bono(instrumentoDTO.getNombreInstrumento(), instrumentoDTO.getPrecio());
        }
        return nuevoInstrumentoFinanciero;
    }
}
