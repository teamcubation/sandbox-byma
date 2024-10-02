package springApp.java.com.example.gestoralyc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import springApp.java.com.example.gestoralyc.utils.FechaUtils;

import java.time.LocalDate;

@Data
public class AccionDTO {
    @Schema(hidden = true)
    private Long id;
    private String nombre;
    private Double precio;
    private Double dividendo;
    private LocalDate fechaCreacion;
    @JsonIgnore
    private LocalDate finDelParking;

    public void calcularFinDelParking() {
        this.finDelParking = FechaUtils.calcularFinDelParking(LocalDate.now(), 2);
    }
}
