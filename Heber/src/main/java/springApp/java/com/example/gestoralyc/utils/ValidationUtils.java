package springApp.java.com.example.gestoralyc.utils;

import springApp.java.com.example.gestoralyc.dto.AccionDTO;
import springApp.java.com.example.gestoralyc.dto.BonoDTO;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;

public class ValidationUtils {

    public static void validarEsNoNulo(Object objeto, String mensajeError) throws InvalidInstrumentoDataException {
        if (objeto != null) {
            throw new InvalidInstrumentoDataException(mensajeError);
        }
    }


    public static void validarEsNulo(Object objeto, String mensajeError) throws InvalidInstrumentoDataException {
        if (objeto == null) {
            throw new InvalidInstrumentoDataException(mensajeError);
        }
    }


    public static void validarCadenaNoVacia(String cadena, String mensajeError) throws InvalidInstrumentoDataException {
        if (cadena == null || cadena.trim().isEmpty()) {
            throw new InvalidInstrumentoDataException(mensajeError);
        }
    }


    public static void validarValorPositivo(Double valor, String mensajeError) throws InvalidInstrumentoDataException {
        if (valor == null || valor <= 0) {
            throw new InvalidInstrumentoDataException(mensajeError);
        }
    }

    public static void validarAccionDTO(AccionDTO accionDTO) throws InvalidInstrumentoDataException {
        validarValorPositivo(accionDTO.getDividendo(), "El campo 'dividendo' es requerido para una acción.");
        validarCadenaNoVacia(accionDTO.getNombre(), "El campo 'nombre' es requerido para una acción.");
        validarValorPositivo(accionDTO.getPrecio(), "El campo 'precio' debe ser un valor positivo.");

        validarEsNoNulo(accionDTO.getFinDelParking(), "El campo 'finDelParking' no es válido para la creación.");
        validarEsNoNulo(accionDTO.getFechaCreacion(), "El campo 'fechaCreacion' no es válido para la creación.");
        validarEsNoNulo(accionDTO.getId(), "El campo 'id' no es válido para la creación.");
    }

    public static void validarBonoDTO(BonoDTO bonoDTO) throws InvalidInstrumentoDataException {
        validarCadenaNoVacia(bonoDTO.getNombre(), "El campo 'nombre' es requerido para un bono.");
        validarValorPositivo(bonoDTO.getPrecio(), "El campo 'precio' debe ser un valor positivo.");
        validarValorPositivo(bonoDTO.getTasaInteres(), "El campo 'tasaInteres' debe ser un valor positivo.");

        validarEsNoNulo(bonoDTO.getFinDelParking(), "El campo 'finDelParking' no es válido para la creación.");
        validarEsNoNulo(bonoDTO.getFechaCreacion(), "El campo 'fechaCreacion' no es válido para la creación.");
        validarEsNoNulo(bonoDTO.getId(), "El campo 'id' no es válido para la creación.");
    }

    public static void validarAccionActualizacion(AccionDTO accionDTO) throws InvalidInstrumentoDataException {
        validarEsNoNulo(accionDTO.getFinDelParking(), "El campo 'finDelParking' no es válido para la actualización.");
        validarEsNoNulo(accionDTO.getFechaCreacion(), "El campo 'fechaCreacion' no es válido para la actualización.");
        validarEsNoNulo(accionDTO.getId(), "El campo 'id' no es válido para la actualización.");
    }


}
