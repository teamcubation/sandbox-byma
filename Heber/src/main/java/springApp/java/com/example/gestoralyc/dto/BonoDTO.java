package springApp.java.com.example.gestoralyc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import springApp.java.com.example.gestoralyc.utils.FechaUtils;

import java.time.LocalDate;

@Data
public class BonoDTO {
    private Long id;
    private String nombre;
    private Double precio;
    private Double tasaInteres;
    @JsonIgnore
    private LocalDate fechaCreacion;
    private LocalDate finDelParking;

    public void calcularFinDelParking() {
        this.finDelParking = FechaUtils.calcularFinDelParking(LocalDate.now(), 2);
    }
}
