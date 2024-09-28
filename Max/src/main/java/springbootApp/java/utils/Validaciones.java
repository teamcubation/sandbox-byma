package springbootApp.java.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootApp.java.models.InstrumentoFinanciero;
import springbootApp.java.models.Inversor;
import springbootApp.java.models.Tipo;
import springbootApp.java.repositories.interfaces.IInstrumentoFinancieroRepository;
import springbootApp.java.repositories.interfaces.IInversorRepository;

public class Validaciones {

    public static boolean validarNombre(String nombre) {
        if (nombre == null) {
            throw new IllegalArgumentException("Error. El nombre no puede ser nulo.");
        }
        if (nombre.isBlank())
            throw new IllegalArgumentException("Error. El nombre no puede ser vacío.");
        return true;
    }

    public static boolean validarDni(String dni) {
        if (dni == null || dni.isEmpty()) {
            throw new IllegalArgumentException("Error. El dni no puede ser nulo o vacío.");
        }
        return true;
    }

    public static boolean validarTipo(Tipo tipo) {
        if (tipo == null) {
            throw new NullPointerException("Error. El tipo no puede ser nulo.");
        }
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

    public static boolean validarDatosInstrumento(String nombre, double precio, Tipo tipo) {
        return validarNombre(nombre) && validarPrecio(precio) && validarTipo(tipo);
    }

    public static boolean validarDatosInversor(String nombre, String dni) {
        return validarDni(dni) && validarNombre(nombre);
    }

    public static boolean validarNombreDuplicado(String nombre, Long id, IInstrumentoFinancieroRepository repository) {
        InstrumentoFinanciero instrumentoFinanciero = repository.findByNombre(nombre);
        return instrumentoFinanciero != null && !instrumentoFinanciero.getId().equals(id);
    }
    public static boolean validarDniDuplicado(String dni, Long id, IInversorRepository repository) {
        Inversor inversor = repository.findByDni(dni);
        return inversor != null && !inversor.getId().equals(id);
    }
}