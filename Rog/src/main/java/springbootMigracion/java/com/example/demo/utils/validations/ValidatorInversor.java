package springbootMigracion.java.com.example.demo.utils.validations;

import springbootMigracion.java.com.example.demo.dto.InversorDTO;
import springbootMigracion.java.com.example.demo.exception.ValidationException;

public class ValidatorInversor {

    public static void validarInversor(InversorDTO inversorDTO) throws ValidationException {
        String mensajeError = "";
        if (inversorDTO == null) {
            mensajeError += ErrorMessagesInversor.INVERSOR_NO_NULO + "-";
        }
        if (inversorDTO.getNombre() == null || inversorDTO.getNombre().isEmpty()) {
            mensajeError += ErrorMessagesInversor.NOMBRE_INVERSOR_NO_NULO + "-";
        }
        if (inversorDTO.getEmail() == null || inversorDTO.getEmail().isEmpty()) {
            mensajeError += ErrorMessagesInversor.EMAIL_INVERSOR_VALIDO + "-";
        }
        if (!esEmailValido(inversorDTO.getEmail())) {
            mensajeError += ErrorMessagesInversor.EMAIL_INVERSOR_FORMATO_INVALIDO + "-";
        }

        if (!mensajeError.isEmpty()){
            throw new ValidationException(mensajeError);
        }
    }

    private static boolean esEmailValido(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

}
