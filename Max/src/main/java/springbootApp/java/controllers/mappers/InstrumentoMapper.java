package springbootApp.java.controllers.mappers;

import springbootApp.java.DTOs.InstrumentoDTO;
import springbootApp.java.models.Accion;
import springbootApp.java.models.Bono;
import springbootApp.java.models.InstrumentoFinanciero;
import springbootApp.java.models.Tipo;

public class InstrumentoMapper {

    public static InstrumentoDTO instrumentoToInstrumentoDTO(InstrumentoFinanciero instrumento) {

        InstrumentoDTO instrumentoDTO = new InstrumentoDTO();
        instrumentoDTO.setNombre(instrumento.getNombre());
        instrumentoDTO.setTipo(instrumento.getTipo());
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
