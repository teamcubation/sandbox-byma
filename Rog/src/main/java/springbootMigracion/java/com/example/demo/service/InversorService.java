package springbootMigracion.java.com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootMigracion.java.com.example.demo.dto.InversorDTO;
import springbootMigracion.java.com.example.demo.exception.InstrumentoNoEncontradoException;
import springbootMigracion.java.com.example.demo.exception.InversorDuplicadoException;
import springbootMigracion.java.com.example.demo.exception.InversorNoEncontradoException;
import springbootMigracion.java.com.example.demo.model.InstrumentoFinanciero;
import springbootMigracion.java.com.example.demo.model.Inversor;
import springbootMigracion.java.com.example.demo.repository.IInversorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InversorService implements IInversorService {

    @Autowired
    private IInversorRepository inversorRepository;

    @Override
    public void registrarInversor(InversorDTO inversorDTO) {
        Inversor inversor = new Inversor(inversorDTO.getNombre(), inversorDTO.getEmail());
        if (buscarInversorPorNombre(inversor.getNombre()).isPresent()){
            throw new InversorDuplicadoException("EL inversor ya existe.");
        }
        inversorRepository.save(inversor);
    }

    @Override
    public void eliminarInversor(String nombre) {
        Inversor inversor = inversorRepository.findByNombreIgnoreCase(nombre)
                .orElseThrow(() -> new InversorNoEncontradoException("Inversor no encontrado"));
        for (InstrumentoFinanciero instrumento : inversor.getInstrumentosSuscritosList()) {
            instrumento.getInversoresSuscritosList().remove(inversor);
        }
        inversorRepository.delete(inversor);
    }

    @Override
    public List<Inversor> listarTodosLosInversores() {
        return inversorRepository.findAll();
    }

    @Override
    public Optional<Inversor> buscarInversorPorNombre(String nombre) {
        return inversorRepository.findByNombreIgnoreCase(nombre);
    }

    @Override
    public void editarInversor(String nombre, InversorDTO inversorDTO) {
        Inversor inversorExistente = inversorRepository.findByNombreIgnoreCase(nombre)
                .orElseThrow(() -> new InversorNoEncontradoException("Inversor no encontrado"));
        inversorExistente.setNombre(inversorDTO.getNombre());
        inversorExistente.setEmail(inversorDTO.getEmail());
        inversorRepository.save(inversorExistente);
    }

}
