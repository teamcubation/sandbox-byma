package springbootApp.java.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootApp.java.exceptions.InversorExistenteException;
import springbootApp.java.exceptions.InversorNoEncontradoException;
import springbootApp.java.models.InstrumentoFinanciero;
import springbootApp.java.models.Inversor;
import springbootApp.java.repositories.InversorRepository;
import springbootApp.java.utils.Validaciones;

import java.util.List;

@Service
public class InversorService {

    @Autowired
    private InversorRepository inversoresRepository;

    public void registrarInversor(Inversor inversor) throws InversorExistenteException {
        Inversor inversorNuevo = inversoresRepository.buscarInversor(inversor.getDni());
        if (inversorNuevo != null)
            throw new InversorExistenteException("Error. Inversor existente");
        if (validarDatosInversor(inversor.getNombre(), inversor.getDni())) {
            inversoresRepository.registrarInversor(new Inversor(inversor.getNombre(), inversor.getDni()));
        }
    }

    public List<InstrumentoFinanciero> consultarInstrumentosDeInversor(String dni) throws InversorNoEncontradoException {
        Inversor inversor = inversoresRepository.buscarInversor(dni);
        if (inversor == null) {
            throw new InversorNoEncontradoException("Error. Inversor no encontrado");
        }
        return inversor.getInstrumentosInversor();
    }

    public void eliminarInversor(String dni) throws InversorNoEncontradoException {
        Inversor inversor = inversoresRepository.buscarInversor(dni);
        if (inversor == null) {
            throw new InversorNoEncontradoException("Error. Inversor no encontrado");
        }
        inversoresRepository.borrarInversor(inversor);
    }

    public List<Inversor> consultarTodosLosInversores() {
        return inversoresRepository.consultarTodosLosInversores();
    }

    public Inversor actualizarInversor(String dni, Inversor inversor) throws InversorNoEncontradoException {
        Inversor inversorEncontrado = inversoresRepository.buscarInversor(dni);
        if (inversorEncontrado == null) {
            throw new InversorNoEncontradoException("Error. Inversor no encontrado");
        }
        if (validarDatosInversor(inversor.getNombre(), inversor.getDni())) {
            inversorEncontrado.setDni(inversor.getDni());
            inversorEncontrado.setNombre(inversor.getNombre());
        }
        return inversor;
    }

    private boolean validarDatosInversor(String nombre, String dni) {
        return Validaciones.validarDni(dni) && Validaciones.validarNombre(nombre);
    }

    public Inversor consultarInversor(String dni) throws InversorNoEncontradoException {
        if (inversoresRepository.buscarInversor(dni) == null)
            throw new InversorNoEncontradoException("Error. Inversor no encontrado");
        return inversoresRepository.buscarInversor(dni);
    }
}
