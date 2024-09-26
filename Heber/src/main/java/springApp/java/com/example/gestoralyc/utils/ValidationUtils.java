package springApp.java.com.example.gestoralyc.utils;

import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;

public class ValidationUtils {

    private static final String MSJ_ERROR_INSTRUMENTO_NULO = "Instrumento nulo";
    private static final String MSJ_ERROR_CADENA_VACIA = "Cadena vacía";
    private static final String MSJ_ERROR_PRECIO_NEGATIVO = "Precio negativo";

    //TODO: hacer constantes los mensajes de error y no pedir mensajes de error en los metodos
    // Método para validar que un objeto no sea null
    public static void validarNoNulo(Object objeto, String mensajeError) {
        if (objeto == null) {
            throw new InvalidInstrumentoDataException(mensajeError);
        }
    }

    public static void validarNoNulo(Object objeto) {
        if (objeto == null) {
            throw new InvalidInstrumentoDataException(MSJ_ERROR_INSTRUMENTO_NULO);
        }
    }


    // Método para validar que una cadena no sea null o vacía
    public static void validarCadenaNoVacia(String cadena, String mensajeError) {
        if (cadena == null || cadena.trim().isEmpty()) {
            throw new InvalidInstrumentoDataException(mensajeError);
        }
    }

    public static void validarCadenaNoVacia(String cadena) {
        if (cadena == null || cadena.trim().isEmpty()) {
            throw new InvalidInstrumentoDataException(MSJ_ERROR_CADENA_VACIA);
        }
    }

    // Método para validar que un valor numérico sea mayor a cero
    public static void validarPrecioPositivo(double precio, String mensajeError) {
        if (precio <= 0) {
            throw new InvalidInstrumentoDataException(mensajeError);
        }
    }

    public static void validarPrecioPositivo(double precio) {
        if (precio <= 0) {
            throw new InvalidInstrumentoDataException(MSJ_ERROR_PRECIO_NEGATIVO);
        }
    }
}
