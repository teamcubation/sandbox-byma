package com.example.project.utils;

public class Validador {
    public static void validarNumeroPositivo(Double unNumero) {
        if(unNumero <= 0) {
            throw new IllegalArgumentException("El numero ingresado no puede ser menor que 0.");
        }
    }

    public static void validarNumeroNoSeaNull(Double unNumero) {
        if(unNumero == null) {
            throw new IllegalArgumentException("El numero no puede ser nulo.");
        }
    }

    public static void validarNombreInversor(String unString) {
        if (unString.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        if (unString.matches("[0-9]*")) {
            throw new IllegalArgumentException("El nombre no puede contener numeros.");
        }
    }
}
