package springbootMigracion.java.com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootMigracion.java.com.example.demo.dto.InversorDTO;
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
        inversorRepository.agregarInversor(inversor);
    }

    @Override
    public void eliminarInversor(String nombre) {
        inversorRepository.eliminarInversor(nombre);
    }

    @Override
    public List<Inversor> listarTodosLosInversores() {
        return inversorRepository.listarTodosLosInversores();
    }

    @Override
    public Optional<Inversor> buscarInversorPorNombre(String nombre) {
        return inversorRepository.buscarInversorPorNombre(nombre);
    }

    @Override
    public void editarInversor(String nombre, InversorDTO inversorDTO) {
        Inversor nuevoInversor = new Inversor(inversorDTO.getNombre(), inversorDTO.getEmail());
        inversorRepository.editarInversor(nombre, nuevoInversor);
    }

}
