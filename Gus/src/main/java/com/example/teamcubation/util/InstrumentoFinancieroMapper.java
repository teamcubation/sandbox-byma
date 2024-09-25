package com.example.teamcubation.util;

import com.example.teamcubation.model.InstrumentoDTO.InstrumentoFinancieroDTO;
import com.example.teamcubation.model.InstrumentoFinanciero;
import com.example.teamcubation.model.instrumentoEnums.TipoInstrumentoFinanciero;
import com.example.teamcubation.service.InstrumentoFinancieroFactoryService;
import org.springframework.stereotype.Component;

@Component
public class InstrumentoFinancieroMapper {

    //toInstrumentoFinanciero
    public static InstrumentoFinanciero instrumentoDTOtoInstrumentoFinanciero(InstrumentoFinancieroDTO instrumentoDTO, InstrumentoFinancieroFactoryService instrumentoFinancieroFactoryService) {

        return instrumentoFinancieroFactoryService.crearInstrumento(instrumentoDTO, TipoInstrumentoFinanciero.valueOf(instrumentoDTO.getTipo().toUpperCase()));
    }

//    public static InstrumentoFinanciero editarInstrumentoDTOtoInstrumentoFinanciero(EditarInstrumentoDTO instrumentoDTO, InstrumentoFinancieroFactoryService instrumentoFinancieroFactoryService) {
//        return instrumentoFinancieroFactoryService.crearInstrumento(new InstrumentoFinancieroDTO(instrumentoDTO.getNuevoNombre(), instrumentoDTO.getNuevoPrecio(), instrumentoDTO.getTipo()), TipoInstrumentoFinanciero.valueOf(instrumentoDTO.getTipo().toUpperCase()));
//    }


    //toDtos
    public static InstrumentoFinancieroDTO instrumentoFinancieroToInstrumentoDTO(InstrumentoFinanciero instrumentoFinanciero) {
        return new InstrumentoFinancieroDTO(instrumentoFinanciero.getNombre(), instrumentoFinanciero.getPrecio(), instrumentoFinanciero.getTipo().toString());
    }


}
