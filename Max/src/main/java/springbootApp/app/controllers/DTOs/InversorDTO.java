package springbootApp.app.controllers.DTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InversorDTO {

    private String nombre;
    private String dni;

    public InversorDTO(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

}
