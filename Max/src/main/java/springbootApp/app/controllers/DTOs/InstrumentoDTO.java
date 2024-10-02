package springbootApp.app.controllers.DTOs;

import lombok.*;
import  springbootApp.app.models.Tipo;

@ToString
@Getter
@Setter
@AllArgsConstructor
@Builder
public class InstrumentoDTO {
    private String nombre;
    private Double precio;
    private String tipo;

}