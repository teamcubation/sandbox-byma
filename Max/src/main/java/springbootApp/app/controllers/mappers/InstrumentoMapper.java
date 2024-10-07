package springbootApp.app.controllers.mappers;

import lombok.Builder;
import springbootApp.app.controllers.DTOs.InstrumentoDTO;
import springbootApp.app.models.Accion;
import springbootApp.app.models.Bono;
import springbootApp.app.models.InstrumentoFinanciero;
import springbootApp.app.models.Tipo;

import static springbootApp.app.models.InstrumentoFactory.TIPO_INVALIDO;

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
        String tipo = instrumentoDTO.getTipo();
        tipo = tipo != null ? tipo.toUpperCase() : null;
        return switch (tipo) {
            case "ACCION" -> Accion.builder()
                    .nombre(instrumentoDTO.getNombre())
                    .precio(instrumentoDTO.getPrecio())
                    .tipo(Tipo.ACCION)
                    .build();
            case "BONO" -> Bono.builder()
                    .nombre(instrumentoDTO.getNombre())
                    .precio(instrumentoDTO.getPrecio())
                    .tipo(Tipo.BONO)
                    .build();
            case null ->
                    Bono.builder()
                            .nombre(instrumentoDTO.getNombre())
                            .precio(instrumentoDTO.getPrecio())
                            .tipo(null)
                            .build();
            default -> throw new IllegalStateException(TIPO_INVALIDO);
        };
    }
}