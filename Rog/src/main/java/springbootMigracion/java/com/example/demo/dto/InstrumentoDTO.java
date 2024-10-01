package springbootMigracion.java.com.example.demo.dto;

import lombok.Data;

@Data
public class InstrumentoDTO {
    private String nombre;
    private Double precio;
    private String tipo;
    private Double tasaDeInteres;

}
