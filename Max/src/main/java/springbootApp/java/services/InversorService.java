package springbootApp.java.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
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

    public List<InstrumentoFinanciero> consultarInstrumentosDeInversor(Long id) throws InversorNoEncontradoException {
        Inversor inversor = inversorRepository.findById(id).orElse(null);
        if (inversor == null) {
            throw new InversorNoEncontradoException("Error. Inversor no encontrado");
        }
        return inversor.getInstrumentosDelInversor();
    }

    public void eliminarInversor(Long id) throws InversorNoEncontradoException {
        Inversor inversor = inversorRepository.findById(id).orElse(null);
        if (inversor == null) {
            throw new InversorNoEncontradoException("Error. Inversor no encontrado");
        }
        inversorRepository.delete(inversor);
    }

    public List<Inversor> consultarTodosLosInversores() {
        return inversorRepository.findAll();
    }

    public Inversor actualizarInversor(Long id, Inversor inversor) throws InversorNoEncontradoException, InversorExistenteException {
        Inversor inversorEncontrado = inversorRepository.findById(id).orElse(null);
        if (inversorEncontrado == null) {
            throw new InversorNoEncontradoException("Error. Inversor no encontrado");
        }
        if (Validaciones.validarDniDuplicado(inversor.getDni(), id, inversorRepository)) {
            throw new InversorExistenteException("Error. Inversor con dni ya existente");
        }
        if (Validaciones.validarDatosInversor(inversor.getNombre(), inversor.getDni())) {
            inversorEncontrado.setDni(inversor.getDni());
            inversorEncontrado.setNombre(inversor.getNombre());
            inversorRepository.save(inversorEncontrado);
        }
        return inversorEncontrado;
    }

    public Inversor consultarInversor(Long id) throws InversorNoEncontradoException {
        Inversor inversor = inversorRepository.findById(id).orElse(null);
        if (inversor == null)
            throw new InversorNoEncontradoException("Error. Inversor no encontrado");
        return inversor;
    }
}
