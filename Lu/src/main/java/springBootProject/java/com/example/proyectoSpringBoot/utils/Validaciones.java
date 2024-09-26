package springBootProject.java.com.example.proyectoSpringBoot.utils;

public class Validaciones {
    private static final String NOMBRE_REGEX = "^[0-9]+.*";

    public static boolean esNombreValido(String nombreAValidar) {
        return nombreAValidar != null && !(nombreAValidar.trim().isEmpty()) && !(nombreAValidar.trim().matches(NOMBRE_REGEX));
    }

    public static boolean esPrecioValido(Double precioAValidar) {
        return precioAValidar != null && precioAValidar > 0;
    }

    public static boolean esTipoValido(Integer tipo) {
        return tipo != null;
    }
}
