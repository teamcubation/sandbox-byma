package springbootApp.java.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootApp.java.exceptions.InversorExistenteException;
import springbootApp.java.exceptions.InversorNoEncontradoException;
import springbootApp.java.models.InstrumentoFinanciero;
import springbootApp.java.models.Inversor;
import springbootApp.java.repositories.interfaces.IInversorRepository;
import springbootApp.java.utils.Validaciones;

import java.util.List;

@Service
public class InversorService {

    @Autowired
    private IInversorRepository inversorRepository;

    public void registrarInversor(Inversor inversor) throws InversorExistenteException {
        Inversor inversorNuevo = inversorRepository.findByDni(inversor.getDni());
        if (inversorNuevo != null)
            throw new InversorExistenteException("Error. Inversor existente");
        if (Validaciones.validarDatosInversor(inversor.getNombre(), inversor.getDni())) {
            inversorRepository.save(new Inversor(inversor.getNombre(), inversor.getDni()));
        }
    }

    public List<InstrumentoFinanciero> consultarInstrumentosDeInversor(String dni) throws InversorNoEncontradoException {
        Inversor inversor = inversorRepository.findByDni(dni);
        if (inversor == null) {
            throw new InversorNoEncontradoException("Error. Inversor no encontrado");
        }
        return inversor.getInstrumentosDelInversor();
    }

    public void eliminarInversor(String dni) throws InversorNoEncontradoException {
        Inversor inversor = inversorRepository.findByDni(dni);
        if (inversor == null) {
            throw new InversorNoEncontradoException("Error. Inversor no encontrado");
        }
        inversorRepository.delete(inversor);
    }

    public List<Inversor> consultarTodosLosInversores() {
        return inversorRepository.findAll();
    }

    public Inversor actualizarInversor(String dni, Inversor inversor) throws InversorNoEncontradoException {
        Inversor inversorEncontrado = inversorRepository.findByDni(dni);
        if (inversorEncontrado == null) {
            throw new InversorNoEncontradoException("Error. Inversor no encontrado");
        }
        if (Validaciones.validarDatosInversor(inversor.getNombre(), inversor.getDni())) {
            inversorEncontrado.setDni(inversor.getDni());
            inversorEncontrado.setNombre(inversor.getNombre());
            inversorRepository.save(inversorEncontrado);
        }
        return inversorEncontrado;
    }

    public Inversor consultarInversor(String dni) throws InversorNoEncontradoException {
        if (inversorRepository.findByDni(dni) == null)
            throw new InversorNoEncontradoException("Error. Inversor no encontrado");
        return inversorRepository.findByDni(dni);
    }
}
