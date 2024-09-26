package springApp.java.com.example.gestoralyc.utils;

import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;

public class ValidationUtils {


    //TODO: hacer constantes los mensajes de error y no pedir mensajes de error en los metodos
    // Método para validar que un objeto no sea null
    public static void validarNoNulo(Object objeto, String mensajeError) {
        if (objeto == null) {
            throw new InvalidInstrumentoDataException(mensajeError);
        }
    }

    // Método para validar que una cadena no sea null o vacía
    public static void validarCadenaNoVacia(String cadena, String mensajeError) {
        if (cadena == null || cadena.trim().isEmpty()) {
            throw new InvalidInstrumentoDataException(mensajeError);
        }
    }

    // Método para validar que un valor numérico sea mayor a cero
    public static void validarPrecioPositivo(double precio, String mensajeError) {
        if (precio <= 0) {
            throw new InvalidInstrumentoDataException(mensajeError);
        }
    }
}
