package  springbootApp.app.services;


import springbootApp.app.services.interfaces.IInversorService;
import springbootApp.app.utils.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  springbootApp.app.exceptions.InversorExistenteException;
import  springbootApp.app.exceptions.InversorNoEncontradoException;
import  springbootApp.app.models.InstrumentoFinanciero;
import  springbootApp.app.models.Inversor;
import  springbootApp.app.repositories.interfaces.IInversorRepository;

import java.util.List;

@Service
public class InversorService implements IInversorService {

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
    public void guardarInversor(Inversor inversor){
        inversorRepository.save(inversor);
    }
}
