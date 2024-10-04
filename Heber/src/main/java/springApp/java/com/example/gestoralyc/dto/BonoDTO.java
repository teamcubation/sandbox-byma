package springApp.java.com.example.gestoralyc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import springApp.java.com.example.gestoralyc.utils.FechaUtils;

import java.time.LocalDate;

@Data
public class BonoDTO {

    @Schema(hidden = true)
    private Long id;

    @Schema(description = "Nombre del bono", example = "AL30", required = true)
    private String nombre;

    @Schema(description = "Precio del bono", example = "1000.0", required = true)
    private Double precio;

    @Schema(description = "Tasa de interés del bono", example = "5.5", required = true)
    private Double tasaInteres;

    @JsonIgnore
    private LocalDate fechaCreacion;

    @Schema(hidden = true)
    private LocalDate finDelParking;

    public void calcularFinDelParking() {
        this.finDelParking = FechaUtils.calcularFinDelParking(LocalDate.now(), 2);
    }
}
