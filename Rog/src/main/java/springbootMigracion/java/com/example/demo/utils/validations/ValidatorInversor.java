package springbootMigracion.java.com.example.demo.utils.validations;

import springbootMigracion.java.com.example.demo.dto.InversorDTO;
import springbootMigracion.java.com.example.demo.exception.ValidationException;

public class ValidatorInversor {

    public static void validarInversor(InversorDTO inversorDTO) throws ValidationException {
        if (inversorDTO == null) {
            throw new ValidationException(ErrorMessages.INVERSOR_NO_NULO);
        }
        if (inversorDTO.getNombre() == null || inversorDTO.getNombre().isEmpty()) {
            throw new ValidationException(ErrorMessages.NOMBRE_INVERSOR_NO_NULO);
        }
        if (inversorDTO.getEmail() == null || inversorDTO.getEmail().isEmpty()) {
            throw new ValidationException(ErrorMessages.EMAIL_INVERSOR_VALIDO);
        }
        if (!esEmailValido(inversorDTO.getEmail())) {
            throw new ValidationException(ErrorMessages.EMAIL_INVERSOR_FORMATO_INVALIDO);
        }
    }

    private static boolean esEmailValido(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

}
