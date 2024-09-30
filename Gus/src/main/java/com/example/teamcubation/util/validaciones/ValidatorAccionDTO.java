package com.example.teamcubation.util.validaciones;

import com.example.teamcubation.exceptions.ModeloInvalidoException;
import com.example.teamcubation.model.InstrumentoDTO.AccionDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.teamcubation.util.validaciones.ValidationErrorMessages.*;

@Slf4j
public class ValidatorAccionDTO {

    private static final String REGEX_LETRAS_NUMEROS = "^[a-zA-Z0-9\\s]+$";

    //TODO separar la logica de la validacion
    public static void validarAccionDTO(AccionDTO nuevaAccion) throws ModeloInvalidoException {

        List<String> errores = new ArrayList<>();

        if (nombreEsNull(nuevaAccion.getNombre())) {

            log.error(ERROR_EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_SER_NULO);
            errores.add(EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_SER_NULO);
            throw new ModeloInvalidoException("Error: " + String.join(" ,", errores));
        }

        if (dividendoEsInvalido(nuevaAccion.getDividendo())) {

            log.error(ERROR_EL_DIVIDENDO_INGRESADO_ES_INVALIDO);
            errores.add(EL_DIVIDENDO_INGRESADO_ES_INVALIDO);
            throw new ModeloInvalidoException("Error: " + String.join(" ,", errores));

        }

        if (precioEsInvalido(nuevaAccion.getPrecio())) {

            log.error(ERROR_EL_PRECIO_INGRESADO_ES_INVALIDO);
            errores.add(EL_PRECIO_INGRESADO_ES_INVALIDO);
            throw new ModeloInvalidoException("Error: " + String.join(" ,", errores));

        }

        //Validacion Nombre
        if (nombreVacio(nuevaAccion.getNombre())) {

            log.error(ERROR_EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_SER_VACIO);
            errores.add(EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_SER_VACIO);
        }

        if (nombreContieneCaracteresEspeciales(nuevaAccion.getNombre())) {

            log.error(ERROR_EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_CONTENER_CARACTERES_ESPECIALES);
            errores.add(EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_CONTENER_CARACTERES_ESPECIALES);
        }


        //Validacion Precio
        if (precioMenorACero(nuevaAccion.getPrecio())) {

            log.error(ERROR_EL_PRECIO_DEL_INSTRUMENTO_DEBE_SER_MAYOR_A_0);
            errores.add(EL_PRECIO_DEL_INSTRUMENTO_DEBE_SER_MAYOR_A_0);
        }


        //Validacion Dividendo
        if (dividendoMenorACero(nuevaAccion.getDividendo())) {

            log.error(ERROR_EL_DIVIDENDO_DEL_INSTRUMENTO_DEBE_SER_MAYOR_A_0);
            errores.add(EL_DIVIDENDO_DEL_INSTRUMENTO_DEBE_SER_MAYOR_A_0);
        }


        if (!errores.isEmpty()) {
            throw new ModeloInvalidoException(ERROR_MESSAGE_HEADER + errores.stream().collect(Collectors.joining(" ,", " [ ", " ] ")));
        }
    }

    private static boolean nombreVacio(String nombre) {
        return nombre.isBlank() || nombre.isEmpty();
    }

    private static boolean nombreContieneCaracteresEspeciales(String nombre) {
        return !nombre.matches(REGEX_LETRAS_NUMEROS);
    }

    private static boolean nombreEsNull(String nombre) {

        return nombre == null;
    }

    private static boolean precioMenorACero(Double precio) {
        return precio <= 0;
    }

    private static boolean precioEsInvalido(Double precio) {
        return precio == null || precio.isNaN() || precio.isInfinite();
    }


    private static boolean dividendoMenorACero(Double dividendo) {
        return dividendo <= 0;
    }


    private static boolean dividendoEsInvalido(Double dividendo) {
        return dividendo == null || dividendo.isNaN() || dividendo.isInfinite();
    }
}
