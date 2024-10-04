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

    public static void validarAccionDTO(AccionDTO nuevaAccion) throws ModeloInvalidoException {
        List<String> errores = new ArrayList<>();
        validarDatosDelDTO(nuevaAccion, errores);
        if (!errores.isEmpty()) {
            throw new ModeloInvalidoException(ERROR_MESSAGE_HEADER + errores.stream().collect(Collectors.joining(" ,", " [ ", " ] ")));
        }
    }

    private static void validarDatosDelDTO(AccionDTO nuevaAccion, List<String> errores) throws ModeloInvalidoException {
        validarNombreDTO(nuevaAccion, errores);
        validarDividendoDTO(nuevaAccion, errores);
        validarPrecioDTO(nuevaAccion, errores);
        validarContenidoDelNombreDTO(nuevaAccion, errores);
        validarFormatoDelNombreDTO(nuevaAccion, errores);
        validarPrecioPositivoDTO(nuevaAccion, errores);
        validarDividendoPositivoDTO(nuevaAccion, errores);
    }

    private static void validarDividendoPositivoDTO(AccionDTO nuevaAccion, List<String> errores) {
        if (dividendoMenorACero(nuevaAccion.getDividendo())) {
            log.error(ERROR_EL_DIVIDENDO_DEL_INSTRUMENTO_DEBE_SER_MAYOR_A_0);
            errores.add(EL_DIVIDENDO_DEL_INSTRUMENTO_DEBE_SER_MAYOR_A_0);
        }
    }

    private static void validarPrecioPositivoDTO(AccionDTO nuevaAccion, List<String> errores) {
        if (precioMenorACero(nuevaAccion.getPrecio())) {
            log.error(ERROR_EL_PRECIO_DEL_INSTRUMENTO_DEBE_SER_MAYOR_A_0);
            errores.add(EL_PRECIO_DEL_INSTRUMENTO_DEBE_SER_MAYOR_A_0);
        }
    }

    private static void validarFormatoDelNombreDTO(AccionDTO nuevaAccion, List<String> errores) {
        if (nombreContieneCaracteresEspeciales(nuevaAccion.getNombre())) {
            log.error(ERROR_EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_CONTENER_CARACTERES_ESPECIALES);
            errores.add(EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_CONTENER_CARACTERES_ESPECIALES);
        }
    }

    private static void validarContenidoDelNombreDTO(AccionDTO nuevaAccion, List<String> errores) {
        if (nombreVacio(nuevaAccion.getNombre())) {
            log.error(ERROR_EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_SER_VACIO);
            errores.add(EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_SER_VACIO);
        }
    }

    private static void validarPrecioDTO(AccionDTO nuevaAccion, List<String> errores) throws ModeloInvalidoException {
        if (precioEsInvalido(nuevaAccion.getPrecio())) {
            log.error(ERROR_EL_PRECIO_INGRESADO_ES_INVALIDO);
            errores.add(EL_PRECIO_INGRESADO_ES_INVALIDO);
            throw new ModeloInvalidoException("Error: " + String.join(" ,", errores));

        }
    }

    private static void validarDividendoDTO(AccionDTO nuevaAccion, List<String> errores) {
        if (dividendoEsInvalido(nuevaAccion.getDividendo())) {
            log.error(ERROR_EL_DIVIDENDO_INGRESADO_ES_INVALIDO);
            errores.add(EL_DIVIDENDO_INGRESADO_ES_INVALIDO);
        }
    }

    private static void validarNombreDTO(AccionDTO nuevaAccion, List<String> errores) {
        if (nombreEsNull(nuevaAccion.getNombre())) {
            log.error(ERROR_EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_SER_NULO);
            errores.add(EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_SER_NULO);
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
