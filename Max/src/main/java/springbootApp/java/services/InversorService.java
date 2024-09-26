package springbootApp.java.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootApp.java.exceptions.InversorNoEncontradoException;
import springbootApp.java.models.InstrumentoFinanciero;
import springbootApp.java.models.Inversor;
import springbootApp.java.DTOs.InversorDTO;
import springbootApp.java.repositories.InversorRepository;
import springbootApp.java.utils.Validaciones;

import java.util.List;

@Service
public class InversorService {

    @Autowired
    private InversorRepository inversoresRepository;

    public void registrarInversor(String nombre, String dni) {
        if (validarDatosInversor(nombre, dni)) {
            Inversor inversor = inversoresRepository.buscarInversor(dni);
            if (inversor != null)
                throw new RuntimeException("Error. Inversor existente");
            inversoresRepository.registrarInversor(new Inversor(nombre, dni));
        }
    }

    public List<InstrumentoFinanciero> consultarInstrumentosDeInversor(String dni) throws InversorNoEncontradoException {
        Inversor inversor = inversoresRepository.buscarInversor(dni);
        if (inversor == null) {
            throw new InversorNoEncontradoException("Error. Inversor no encontrado");
        }
        return inversor.consultarInstrumentos();
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

    public void actualizarInversor(String dni, InversorDTO inversor) throws InversorNoEncontradoException {
        if (!validarDatosInversor(inversor.getNombre(), inversor.getDni())) {
            Inversor inversorEncontrado = inversoresRepository.buscarInversor(dni);
            if (inversorEncontrado == null) {
                throw new InversorNoEncontradoException("Error. Inversor no encontrado");
            }
            inversorEncontrado.actualizarInversor(inversor);
        }
    }

    private boolean validarDatosInversor(String nombre, String dni) {
        return Validaciones.validarDni(dni) && Validaciones.validarNombre(nombre);
    }
}
