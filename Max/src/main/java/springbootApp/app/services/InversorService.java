package  springbootApp.app.services;


import springbootApp.app.services.interfaces.IInversorService;
import springbootApp.app.utils.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  springbootApp.app.exceptions.InversorExistenteException;
import  springbootApp.app.exceptions.InversorNoEncontradoException;
import  springbootApp.app.models.InstrumentoFinanciero;
import  springbootApp.app.models.Inversor;
import  springbootApp.app.repositories.IInversorRepository;

import java.util.List;

@Service
public class InversorService implements IInversorService {

    public static final String ERROR_INVERSOR_NO_ENCONTRADO = "Error. Inversor no encontrado";
    public static final String ERROR_INVERSOR_CON_DNI_EXISTENTE = "Error. Inversor con dni ya existente";
    @Autowired
    private IInversorRepository inversorRepository;

    public void registrarInversor(Inversor inversor) throws InversorExistenteException {
        Inversor inversorNuevo = inversorRepository.findByDni(inversor.getDni());
        if (inversorNuevo != null)
            throw new InversorExistenteException(ERROR_INVERSOR_CON_DNI_EXISTENTE);
        if (Validaciones.validarDatosInversor(inversor.getNombre(), inversor.getDni())) {
            inversorRepository.save(new Inversor(inversor.getNombre(), inversor.getDni()));
        }
    }

    public List<InstrumentoFinanciero> consultarInstrumentosDeInversor(Long id) throws InversorNoEncontradoException {
        Inversor inversor = inversorRepository.findById(id).orElse(null);
        if (inversor == null) {
            throw new InversorNoEncontradoException(ERROR_INVERSOR_NO_ENCONTRADO);
        }
        return inversor.getInstrumentosDelInversor();
    }

    public void eliminarInversor(Long id) throws InversorNoEncontradoException {
        Inversor inversor = inversorRepository.findById(id).orElse(null);
        if (inversor == null) {
            throw new InversorNoEncontradoException(ERROR_INVERSOR_NO_ENCONTRADO);
        }
        inversorRepository.delete(inversor);
    }

    public List<Inversor> consultarTodosLosInversores() {
        return inversorRepository.findAll();
    }

    public Inversor actualizarInversor(Long id, Inversor inversor) throws InversorNoEncontradoException, InversorExistenteException {
        Inversor inversorEncontrado = inversorRepository.findById(id).orElse(null);
        if (inversorEncontrado == null) {
            throw new InversorNoEncontradoException(ERROR_INVERSOR_NO_ENCONTRADO);
        }
        if (Validaciones.validarDniDuplicado(inversor.getDni(), id, inversorRepository)) {
            throw new InversorExistenteException(ERROR_INVERSOR_CON_DNI_EXISTENTE);
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
            throw new InversorNoEncontradoException(ERROR_INVERSOR_NO_ENCONTRADO);
        return inversor;
    }
    public void guardarInversor(Inversor inversor){
        inversorRepository.save(inversor);
    }
}
