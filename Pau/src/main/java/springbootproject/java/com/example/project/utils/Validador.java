package com.example.project.utils;

import java.time.LocalDate;

public class Validador {
    public static void validarDoublePositivo(Double unNumero) {
        if(unNumero <= 0) {
            throw new IllegalArgumentException("El numero ingresado no puede ser menor que 0.");
        }
    }

    public static void validarDoubleNoSeaNull(Double unNumero) {
        if(unNumero == null) {
            throw new IllegalArgumentException("El numero no puede ser nulo.");
        }
    }

    public static void validarNombreInversor(String unString) {
        if (unString.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        if (!unString.matches("[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+")) {
            throw new IllegalArgumentException("El nombre no puede contener numeros.");
        }
    }

    public static void validarFechaNoSeaNull(LocalDate unaFecha) {
        if (unaFecha == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede estar vacia.");
        }
    }

    public static void validarNombreAccion(String unNombre) {
        if (unNombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        if(!unNombre.matches("[A-Z]{3,4}(\\d)?")) {
            throw new IllegalArgumentException("El nombre ingresado no es valido para una accion.");
        }
    }

    public static void validarNombreBono(String unNombre) {
        if (unNombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        if(!unNombre.matches("[A-Z]{2,6}\\d{2,4}")) {
            throw new IllegalArgumentException("El nombre ingresado no es valido para una accion.");
        }
    }
}
