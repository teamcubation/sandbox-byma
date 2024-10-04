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
public class BonoDTO {
    @NotNull(message = ValidationErrorMessages.EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_SER_NULO)
    @NotBlank(message = ValidationErrorMessages.EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_SER_VACIO)
    @NotEmpty(message = ValidationErrorMessages.EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_SER_VACIO)
    @Pattern(regexp = ValidationErrorMessages.REGEX_LETRAS_NUMEROS, message = ValidationErrorMessages.EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_CONTENER_CARACTERES_ESPECIALES)
    @Size(min = 3, max = 20, message = "El nombre del instrumento debe tener entre 3 y 20 caracteres")
    private String nombre;
    @NotNull(message = "El precio del instrumento no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor que 0")
    private Double precio;
    @NotNull(message = "La tasa de interes no puede ser nula")
    @DecimalMin(value = "0.01", message = "El dividendo debe ser mayor que 0")
    private Double tasaInteres;
}
