package springbootApp.app.utils;

import springbootApp.app.exceptions.DniInvalidoException;
import springbootApp.app.exceptions.NombreInvalidoException;
import springbootApp.app.exceptions.PrecioInvalidoException;
import springbootApp.app.exceptions.TipoInvalidoException;
import springbootApp.app.models.InstrumentoFinanciero;
import springbootApp.app.models.Inversor;
import springbootApp.app.models.Tipo;
import springbootApp.app.repositories.IInstrumentoFinancieroRepository;
import springbootApp.app.repositories.IInversorRepository;

public class Validaciones {

    public static boolean validarNombre(String nombre) {
        if (nombre == null) {
            return false;
        }
        return !nombre.isBlank();
    }

    public static boolean validarDni(String dni) {
        if (dni == null) {
            return false;
        }
        return !dni.isBlank();
    }

    public static boolean validarTipo(Tipo tipo) {
        if (tipo == null) {
            return false;
        }
        return tipo == Tipo.BONO || tipo == Tipo.ACCION;
    }

    public static boolean validarPrecio(Double precio) {
        return precio != null && precio > 0;
    }

    public static boolean validarDatosInstrumento(String nombre, double precio, Tipo tipo) {
        if (!validarNombre(nombre))
            throw new NombreInvalidoException("Error. Nombre invalido");
        if (!validarPrecio(precio))
            throw new PrecioInvalidoException("Error. Precio invalido");
        if (!validarTipo(tipo))
            throw new TipoInvalidoException("Error. Tipo invalido");
        return true;
    }

    public static boolean validarDatosInversor(String nombre, String dni) {
        if (!validarNombre(nombre))
            throw new NombreInvalidoException("Error. Nombre invalido");
        if (!validarDni(dni))
            throw new DniInvalidoException("Error. Dni invalido");
        return true;
    }

    public static boolean validarNombreDuplicado(String nombre, Long id, IInstrumentoFinancieroRepository repository) {
        if (nombre == null) {
            return false;
        }
        InstrumentoFinanciero instrumentoFinanciero = repository.findByNombre(nombre);
        return instrumentoFinanciero != null && !instrumentoFinanciero.getId().equals(id);
    }

    public static boolean validarDniDuplicado(String dni, Long id, IInversorRepository repository) {
        if (dni == null) {
            return false;
        }
        Inversor inversor = repository.findByDni(dni);
        return inversor != null && !inversor.getId().equals(id);
    }
}