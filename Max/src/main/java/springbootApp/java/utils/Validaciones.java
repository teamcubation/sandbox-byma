package springbootApp.java.utils;

import springbootApp.java.models.Tipo;

public class Validaciones {

    public static boolean validarNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("Error. El nombre no puede ser nulo o vacío.");
        }
        return true;
    }

    public static boolean validarDni(String dni) {
        if (dni == null || dni.isEmpty()) {
            throw new IllegalArgumentException("Error. El dni no puede ser nulo o vacío.");
        }
        return true;
    }

    public static boolean validarTipo(Tipo tipo) {
        if (tipo != Tipo.BONO && tipo != Tipo.ACCION)
            throw new IllegalArgumentException("Error. Tipo invalido");
        return true;
    }
    public static boolean validarPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("Error. El precio no puede ser negativo.");
        }
        return true;
    }
}
