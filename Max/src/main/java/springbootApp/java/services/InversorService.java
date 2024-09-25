package springbootApp.java.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootApp.java.exceptions.InversorNoEncontradoException;
import springbootApp.java.models.InstrumentoFinanciero;
import springbootApp.java.models.Inversor;
import springbootApp.java.models.InversorDTO;
import springbootApp.java.repositories.InversorRepository;

import java.util.ArrayList;
@Service
public class InversorService {
    @Autowired
    private static InversorRepository inversoresRepository;

    public InversorService() {
        inversoresRepository = InversorRepository.getInversorRepository();
    }
    public void registrarInversor(String nombre, String dni) {
        Inversor inversor = inversoresRepository.buscarInversor(dni);
        if (inversor != null)
            throw new RuntimeException("Error. Inversor existente");
        inversoresRepository.registrarInversor(new Inversor(nombre, dni));
    }

    public ArrayList<InstrumentoFinanciero> consultarInstrumentosDeInversor(String dni) throws InversorNoEncontradoException {
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

    public void modificarInversor(String variable, String modificacion, String dni) throws InversorNoEncontradoException {
        Inversor inversor = inversoresRepository.buscarInversor(dni);
        if (inversor == null) {
            throw new InversorNoEncontradoException("Error. Inversor no encontrado");
        }
        switch (variable) {
            case "1":
                inversor.setNombre(modificacion);
                break;
            case "2":
                inversor.setDni(modificacion);
                break;
            default:
                throw new IllegalArgumentException("Error. los campos no coinciden");
        }
    }

    public ArrayList<Inversor> consultarTodosLosInversores() {
        return inversoresRepository.consultarTodosLosInversores();
    }

    public void actualizarInversor(String dni, InversorDTO inversor) throws InversorNoEncontradoException {
        Inversor inversorEncontrado = inversoresRepository.buscarInversor(dni);
        if (inversorEncontrado == null) {
            throw new InversorNoEncontradoException("Error. Inversor no encontrado");
        }
        inversorEncontrado.actualizarInversor(inversor);
    }
}
