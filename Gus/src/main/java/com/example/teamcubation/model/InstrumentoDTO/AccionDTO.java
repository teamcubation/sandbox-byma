package com.example.teamcubation.model.InstrumentoDTO;

import com.example.teamcubation.util.validaciones.ValidationErrorMessages;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccionDTO {
    
    @NotNull(message = ValidationErrorMessages.EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_SER_NULO)
    @NotBlank(message = ValidationErrorMessages.EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_SER_VACIO)
    @NotEmpty(message = ValidationErrorMessages.EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_SER_VACIO)
    @Pattern(regexp = ValidationErrorMessages.REGEX_LETRAS_NUMEROS, message = ValidationErrorMessages.EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_CONTENER_CARACTERES_ESPECIALES)
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;
    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor o igual a 0")
    private Double precio;
    @NotNull(message = "La tasa de interes no puede ser nula")
    @DecimalMin(value = "0.01", message = "La tasa de interes debe ser mayor o igual a 0")
    private Double dividendo;
}
