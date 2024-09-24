package com.example.teamcubation.repository;


import com.example.teamcubation.model.Accion;
import com.example.teamcubation.model.Bono;
import com.example.teamcubation.model.InstrumentoFinanciero;
import com.example.teamcubation.model.instrumentoEnums.TipoInstrumentoFinanciero;
import org.springframework.stereotype.Repository;

@Repository
public class InstrumentoFinancieroFactoryImpl implements InstrumentoFinancieroFactory {
    @Override
    public InstrumentoFinanciero crearInstrumento(String nombre, double precio, TipoInstrumentoFinanciero tipo) {
        InstrumentoFinanciero nuevoInstrumentoFinanciero = null;
        if (tipo.equals(TipoInstrumentoFinanciero.ACCION)) {
            nuevoInstrumentoFinanciero = new Accion(nombre, precio);
        }


        if (tipo.equals(TipoInstrumentoFinanciero.BONO)) {
            nuevoInstrumentoFinanciero = new Bono(nombre, precio);
        }
        return nuevoInstrumentoFinanciero;
    }
}
