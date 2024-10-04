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

    @Schema(description = "Nombre de la acción", example = "BYMA", required = true)
    private String nombre;

    @Schema(description = "Precio de la acción", example = "150.0", required = true)
    private Double precio;

    @Schema(description = "Dividendo de la acción", example = "1.5", required = true)
    private Double dividendo;

    @JsonIgnore
    private LocalDate fechaCreacion;

    @Schema(hidden = true)
    private LocalDate finDelParking;

    public void calcularFinDelParking() {
        this.finDelParking = FechaUtils.calcularFinDelParking(LocalDate.now(), 2);
    }
}
