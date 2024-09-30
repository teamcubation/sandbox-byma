package com.example.teamcubation.util.validaciones;

import com.example.teamcubation.exceptions.ModeloInvalidoException;
import com.example.teamcubation.model.InstrumentoDTO.AccionDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidatorAccionDTO {

    public static void validarAccionDTO(AccionDTO nuevaAccion) throws ModeloInvalidoException {
        if (nuevaAccion.getNombre().isBlank() || nuevaAccion.getNombre().isEmpty()) {

            log.error("Error: El nombre del instrumento no puede ser vacio");
            throw new ModeloInvalidoException("Error: El nombre del instrumento no puede ser vacio");
        }

        if (nuevaAccion.getNombre().matches("^[a-zA-Z0-9\\s]+$\n")) {

            log.error("Error: El nombre del instrumento no puede contener caracteres especiales");
            throw new ModeloInvalidoException("Error: El nombre del instrumento no puede contener caracteres especiales");
        }
        if (nuevaAccion.getPrecio() <= 0) {

            log.error("Error: El precio del instrumento debe ser mayor a 0");
            throw new ModeloInvalidoException("Error: El precio del instrumento debe ser mayor a 0");
        }

        if (nuevaAccion.getDividendo() <= 0) {

            log.error("Error: El dividendo del instrumento debe ser mayor a 0");
            throw new ModeloInvalidoException("Error: El dividendo del instrumento debe ser mayor a 0");
        }
    }
}
