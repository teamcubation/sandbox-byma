package springbootApp.app.controllers.DTOs;

import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@Builder
public class InversorDTO {
    private String nombre;
    private String dni;
}
