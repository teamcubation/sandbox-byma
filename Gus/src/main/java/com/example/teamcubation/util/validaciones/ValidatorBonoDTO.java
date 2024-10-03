package com.example.teamcubation.util.validaciones;

import com.example.teamcubation.exceptions.ModeloInvalidoException;
import com.example.teamcubation.model.InstrumentoDTO.BonoDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.teamcubation.util.validaciones.ValidationErrorMessages.*;


@Slf4j
public class ValidatorBonoDTO {
    private static final String REGEX_LETRAS_NUMEROS = "^[a-zA-Z0-9\\s]+$";

    public static void validarBonoDTO(BonoDTO nuevoBono) throws ModeloInvalidoException {
        List<String> errores = new ArrayList<>();
        validarDatosDelBonoDTO(nuevoBono, errores);
        if (!errores.isEmpty()) {
            throw new ModeloInvalidoException(ERROR_MESSAGE_HEADER + errores.stream().collect(Collectors.joining(",", "[", "]")));
        }
    }

    private static void validarDatosDelBonoDTO(BonoDTO nuevoBono, List<String> errores) {
        validarNombreNullDTO(nuevoBono, errores);
        validarTasaDeInteresDTO(nuevoBono, errores);
        validarPrecioDTO(nuevoBono, errores);
        validarContenidoNombreDTO(nuevoBono, errores);
        validaFormatoNombreDTO(nuevoBono, errores);
        validarPrecioDTOPositivo(nuevoBono, errores);
        validarTasaDTOPositiva(nuevoBono, errores);
    }

    private static void validarTasaDTOPositiva(BonoDTO nuevoBono, List<String> errores) {
        if (tasaDeInteresMenorACero(nuevoBono.getTasaInteres())) {
            log.error(ERROR_MESSAGE_HEADER + LA_TASA_DE_INTERES_DEL_INSTRUMENTO_DEBE_SER_MAYOR_A_0);
            errores.add(LA_TASA_DE_INTERES_DEL_INSTRUMENTO_DEBE_SER_MAYOR_A_0);
        }
    }

    private static void validarPrecioDTOPositivo(BonoDTO nuevoBono, List<String> errores) {
        //Validacion Precio
        if (precioMenorACero(nuevoBono.getPrecio())) {
            log.error(ERROR_EL_PRECIO_DEL_INSTRUMENTO_DEBE_SER_MAYOR_A_0);
            errores.add(EL_PRECIO_DEL_INSTRUMENTO_DEBE_SER_MAYOR_A_0);
        }
    }

    private static void validaFormatoNombreDTO(BonoDTO nuevoBono, List<String> errores) {
        if (nombreContieneCaracteresEspeciales(nuevoBono.getNombre())) {
            log.error(ERROR_EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_CONTENER_CARACTERES_ESPECIALES);
            errores.add(EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_CONTENER_CARACTERES_ESPECIALES);
        }
    }

    private static void validarContenidoNombreDTO(BonoDTO nuevoBono, List<String> errores) {
        //Validacion Nombre
        if (nombreVacio(nuevoBono.getNombre())) {
            log.error(ERROR_EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_SER_VACIO);
            errores.add(EL_NOMBRE_DEL_INSTRUMENTO_NO_PUEDE_SER_VACIO);
        }
    }

    private static void validarPrecioDTO(BonoDTO nuevoBono, List<String> errores) {
        if (precioEsInvalido(nuevoBono.getPrecio())) {
            log.error(ERROR_EL_PRECIO_INGRESADO_ES_INVALIDO);
            errores.add(EL_PRECIO_INGRESADO_ES_INVALIDO);
        }
    }

    private static void validarTasaDeInteresDTO(BonoDTO nuevoBono, List<String> errores) {
        if (tasaDeInteresEsInvalida(nuevoBono.getTasaInteres())) {
            log.error(ERROR_MESSAGE_HEADER + LA_TASA_DE_INTERES_ES_INVALIDA);
            errores.add(LA_TASA_DE_INTERES_ES_INVALIDA);
        }
    }

    private static void validarNombreNullDTO(BonoDTO nuevoBono, List<String> errores) {
        if (nombreEsNull(nuevoBono.getNombre())) {
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

    private static boolean precioMenorACero(double precio) {
        return precio <= 0;
    }


    private static boolean precioEsInvalido(Double precio) {
        return precio == null || precio.isNaN() || precio.isInfinite();
    }

    private static boolean tasaDeInteresMenorACero(double dividendo) {
        return dividendo <= 0;
    }

    private static boolean tasaDeInteresEsInvalida(Double dividendo) {
        return dividendo == null || dividendo.isNaN() || dividendo.isInfinite();
    }

}
