package springbootproject.java.com.example.project.service;

import org.springframework.stereotype.Service;
import springbootproject.java.com.example.project.exceptions.InversorYaRegistradoException;
import springbootproject.java.com.example.project.model.Inversor;
import springbootproject.java.com.example.project.repository.InversorRepository;

import java.util.stream.Collectors;

@Service
public class InversorService {
    private static InversorService instance;
    private InversorRepository inversorRepository;

    private InversorService() {
        inversorRepository = InversorRepository.getInstance();
    }

    public static InversorService getInstance() {
        if (instance == null) {
            instance = new InversorService();
        }
        return instance;
    }

    public Inversor registrarInversor(String nombre) throws InversorYaRegistradoException {
        Inversor inversor = inversorRepository.buscarInversor(nombre);
        if (inversor != null) {
            throw new InversorYaRegistradoException("El inversor de nombre "+ nombre + "ya fue registrado.");
        } else {
            Inversor nuevoInversor = new Inversor(nombre);

            return inversorRepository.agregarInversor(nuevoInversor);
        }
    }

    public String listarInversores(){
        return this.inversorRepository.obtenerInversores().stream().map(inversor -> inversor.toString()).collect(Collectors.joining(" \n"));
    }
}
