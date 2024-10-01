package springbootMigracion.java.com.example.demo.utils.validations;

public class ErrorMessages {
    // Mensajes de validacion de instrumentos
    public static final String INSTRUMENTO_NO_NULO = "El instrumento no puede ser nulo.";
    public static final String NOMBRE_INSTRUMENTO_NO_NULO = "El nombre del instrumento no puede ser nulo o vacio.";
    public static final String PRECIO_INSTRUMENTO_NO_NULO = "El precio del instrumento no puede ser nulo o vacio.";
    public static final String PRECIO_INSTRUMENTO_VALIDO = "El precio del instrumento debe ser mayor a 0.";
    public static final String TIPO_INSTRUMENTO_NO_NULO = "El tipo de instrumento no puede ser nulo o vacio.";
    public static final String TASA_INTERES_BONO_NO_NULO = "La tasa de interes es requerida para los bonos.";
    public static final String ERROR_INSTRUMENTO_DUPLICADO = "EL instrumento ya existe.";
    public static final String ERROR_INSTRUMENTO_NO_ENCONTRADO = "Instrumento no encontrado";

    // Mensajes de validacion de inversores
    public static final String INVERSOR_NO_NULO = "El inversor no puede ser nulo.";
    public static final String NOMBRE_INVERSOR_NO_NULO = "El nombre del inversor no puede ser nulo o vacio.";
    public static final String EMAIL_INVERSOR_VALIDO = "El email del inversor no puede ser nulo o vacio.";
    public static final String EMAIL_INVERSOR_FORMATO_INVALIDO = "El email del inversor no es valido.";
    public static final String ERROR_INVERSOR_DUPLICADO = "EL inversor ya existe.";
    public static final String ERROR_INVERSOR_NO_ENCONTRADO = "Inversor no encontrado";

    public static final String ID_INVALIDO = "El ID proporcionado no es valido.";
    public static final String FECHA_INVALIDA = "La fecha proporcionada no es valida.";
}
