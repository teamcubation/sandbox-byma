package com.example.proyectoSpringBoot.utils;

import com.example.proyectoSpringBoot.dto.InstrumentoFinancieroDTO;
import com.example.proyectoSpringBoot.dto.InstrumentoFinancieroMapper;
import com.example.proyectoSpringBoot.excepciones.customExcepcions.OpcionInvalidaException;
import com.example.proyectoSpringBoot.model.InstrumentoFinanciero;

import static com.example.proyectoSpringBoot.utils.ExcepcionesMensajes.*;

public class Validaciones {
    private static final String NOMBRE_REGEX = "^[0-9]+.*";

    public static boolean esNombreValido(String nombreAValidar) {
        if (nombreAValidar != null && !(nombreAValidar.trim().isEmpty()) && !(nombreAValidar.trim().matches(NOMBRE_REGEX))) {
            return true;
        }
        return false;
    }

    public static boolean esPrecioValido(Double precioAValidar) {
        if (precioAValidar != null && precioAValidar > 0) {
            return true;
        }
        return false;
    }

    public static boolean esTipoValido(Integer tipo) {
        if (tipo != null) {
            return true;
        }
        return false;
    }

    public static boolean NoEsNombreValido(String nombreAValidar) {
        return !esNombreValido(nombreAValidar);
    }

    public static boolean NoEsPrecioValido(Double precioAValidar) {
        return !esPrecioValido(precioAValidar);
    }

    public static boolean NoEsTipoValido(Integer tipo) {
        return !esTipoValido(tipo);
    }

    public static void validarInstrumentoFinanciero(InstrumentoFinanciero instrumentoFinanciero) {
        String mensajeError = "";

        if (NoEsNombreValido(instrumentoFinanciero.getNombre())) {
            mensajeError += NOMBRE_INVALIDO + " _ ";
        }
        if (NoEsPrecioValido(instrumentoFinanciero.getPrecio())) {
            mensajeError += PRECIO_INVALIDO + " _ ";
        }
        if (NoEsTipoValido(instrumentoFinanciero.getTipo())) {
            mensajeError += TIPO_INVALIDO + " _ ";
        }

        if (!mensajeError.isEmpty()) {
            throw new OpcionInvalidaException(mensajeError);
        }
    }
}