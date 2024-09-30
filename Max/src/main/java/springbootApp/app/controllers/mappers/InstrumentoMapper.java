package  springbootApp.app.controllers.mappers;

import  springbootApp.app.DTOs.InstrumentoDTO;
import  springbootApp.app.models.Accion;
import  springbootApp.app.models.Bono;
import  springbootApp.app.models.InstrumentoFinanciero;
import  springbootApp.app.models.Tipo;

public class InstrumentoMapper {

    public static InstrumentoDTO instrumentoToInstrumentoDTO(InstrumentoFinanciero instrumento) {

        InstrumentoDTO instrumentoDTO = new InstrumentoDTO();
        instrumentoDTO.setNombre(instrumento.getNombre());
        instrumentoDTO.setTipo(String.valueOf(instrumento.getTipo()));
        instrumentoDTO.setPrecio(instrumento.getPrecio());
        return instrumentoDTO;
    }

    public static InstrumentoFinanciero instrumentoDTOToInstrumento(InstrumentoDTO instrumentoDTO) {
        if (instrumentoDTO.getTipo() == Tipo.ACCION) {
            return new Accion(instrumentoDTO.getNombre(), instrumentoDTO.getPrecio());
        } else if (instrumentoDTO.getTipo() == Tipo.BONO) {
            return new Bono(instrumentoDTO.getNombre(), instrumentoDTO.getPrecio());
        }
        return null;
    }
}
