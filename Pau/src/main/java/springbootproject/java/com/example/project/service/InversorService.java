package springbootproject.java.com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootproject.java.com.example.project.controller.InversorDTO;
import springbootproject.java.com.example.project.exceptions.InversorNoEncontradoException;
import springbootproject.java.com.example.project.exceptions.InversorYaRegistradoException;
import springbootproject.java.com.example.project.model.Inversor;
import springbootproject.java.com.example.project.repository.InversorRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class InversorService {
    private static InversorService instance;
    private InversorRepository inversorRepository;

    @Autowired
    private InversorService(InversorRepository inversorRepository) {
        this.inversorRepository = inversorRepository;
    }

    public Inversor registrarInversor(InversorDTO inversorDTO) throws InversorYaRegistradoException {
        Inversor inversor = inversorRepository.buscarInversor(inversorDTO.getNombre());
        if (inversor != null) {
            throw new InversorYaRegistradoException("El inversor de nombre "+ inversorDTO.getNombre() + " ya fue registrado.");
        } else {
            Inversor nuevoInversor = new Inversor(inversorDTO.getNombre(), inversorDTO.getFechaDeNacimiento());

            return inversorRepository.agregarInversor(nuevoInversor);
        }
    }

    public ArrayList<Inversor> obtenerInversores() {
        return this.inversorRepository.obtenerInversores();
    }

    public Inversor consultarPorUnInversor(String nombre) throws InversorNoEncontradoException {
        Inversor inversor = this.inversorRepository.buscarInversor(nombre);
        if (inversor == null) {
            throw new InversorNoEncontradoException("El inversor de nombre " + nombre + " no esta registrado en el sistema.");
        } else {
            return inversor;
        }
    }

    public void eliminarInversor(String nombre) throws InversorNoEncontradoException {
        Inversor inversor = this.inversorRepository.buscarInversor(nombre);
        if (inversor == null) {
            throw new InversorNoEncontradoException("El inversor de nombre " + nombre + " no esta registrado en el sistema. No es posible eliminarlo.");
        } else {
            this.inversorRepository.eliminarInversor(inversor);
        }

    }
}
