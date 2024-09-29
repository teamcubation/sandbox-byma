package com.example.proyectoSpringBoot.service.factory;

import com.example.proyectoSpringBoot.excepciones.customExcepcions.OpcionInvalidaException;
import com.example.proyectoSpringBoot.model.Accion;
import com.example.proyectoSpringBoot.model.Bono;
import com.example.proyectoSpringBoot.model.InstrumentoFinanciero;
import com.example.proyectoSpringBoot.service.factory.enums.TiposInstrumentosFinancieros;

public class InstrumentoFinancieroFactory  {
    public static InstrumentoFinanciero crearInstrumentoFinanciero(Integer tipo) throws OpcionInvalidaException {

        TiposInstrumentosFinancieros tipoInstrumentoFinanciero = TiposInstrumentosFinancieros.opcionSeleccionada(tipo);

        return switch (tipoInstrumentoFinanciero) {
            case BONO -> new Bono();
            case ACCION -> new Accion();
        };
    }
}
