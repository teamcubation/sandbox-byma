package com.example.teamcubation.util.validaciones;

import com.example.teamcubation.exceptions.ModeloInvalidoException;
import com.example.teamcubation.model.InstrumentoDTO.BonoDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidatorBonoDTO {

    public static void validarBonoDTO(BonoDTO nuevoBono) throws ModeloInvalidoException {
        if (nuevoBono.getNombre().isBlank() || nuevoBono.getNombre().isEmpty()) {

            log.error("Error: El nombre del instrumento no puede ser vacio");
            throw new ModeloInvalidoException("Error: El nombre del instrumento no puede ser vacio");
        }

        if (nuevoBono.getNombre().matches("^[a-zA-Z0-9\\s]+$\n")) {

            log.error("Error: El nombre del instrumento no puede contener caracteres especiales");
            throw new ModeloInvalidoException("Error: El nombre del instrumento no puede contener caracteres especiales");
        }
        if (nuevoBono.getPrecio() <= 0) {

            log.error("Error: El precio del instrumento debe ser mayor a 0");
            throw new ModeloInvalidoException("Error: El precio del instrumento debe ser mayor a 0");
        }

        if (nuevoBono.getTasaInteres() <= 0) {

            log.error("Error: El dividendo del instrumento debe ser mayor a 0");
            throw new ModeloInvalidoException("Error: El dividendo del instrumento debe ser mayor a 0");
        }
    }

}
