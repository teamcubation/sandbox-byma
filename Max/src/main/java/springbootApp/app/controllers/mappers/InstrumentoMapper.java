package  springbootApp.app.controllers.mappers;

import lombok.Builder;
import springbootApp.app.controllers.DTOs.InstrumentoDTO;
import  springbootApp.app.models.Accion;
import  springbootApp.app.models.Bono;
import  springbootApp.app.models.InstrumentoFinanciero;
import  springbootApp.app.models.Tipo;

@Builder
public class InstrumentoMapper {

    public static InstrumentoDTO instrumentoToInstrumentoDTO(InstrumentoFinanciero instrumento) {
        return InstrumentoDTO.builder()
                .nombre(instrumento.getNombre())
                .precio(instrumento.getPrecio())
                .tipo(String.valueOf(instrumento.getTipo()))
                .build();
    }

    public static InstrumentoFinanciero instrumentoDTOToInstrumento(InstrumentoDTO instrumentoDTO) {
        if (instrumentoDTO.getTipo().toUpperCase().equals(String.valueOf(Tipo.ACCION))) {
            return Accion.builder()
                    .nombre(instrumentoDTO.getNombre())
                    .precio(instrumentoDTO.getPrecio())
                    .tipo(Tipo.ACCION)
                    .build();
        } else if (instrumentoDTO.getTipo().toUpperCase().equals(String.valueOf(Tipo.BONO))) {
            return Bono.builder()
                    .nombre(instrumentoDTO.getNombre())
                    .precio(instrumentoDTO.getPrecio())
                    .tipo(Tipo.BONO)
                    .build();
        }
        return null;
    }
}