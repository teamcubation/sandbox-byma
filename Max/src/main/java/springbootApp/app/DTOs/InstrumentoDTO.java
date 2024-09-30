package  springbootApp.app.DTOs;

import lombok.Getter;
import lombok.Setter;
import  springbootApp.app.models.Tipo;

@Getter
public class InstrumentoDTO {
    @Setter
    private String nombre;
    @Setter
    private Double precio;
    private Tipo tipo;

    public void  setTipo(String tipo) {
        if (tipo == null) {
            throw new NullPointerException("Error. El tipo no puede ser nulo");
        }
        switch (tipo) {
            case "ACCION":
                this.tipo = Tipo.ACCION;
                break;
            case "BONO":
                this.tipo = Tipo.BONO;
                break;
            default:
                throw new IllegalArgumentException("Error. Tipo invalido");
        }
    }

}