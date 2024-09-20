package consoleversion.service;

import consoleversion.exceptions.InversorYaRegistradoException;
import consoleversion.model.Inversor;
import consoleversion.repository.InversorRepository;

import java.util.stream.Collectors;

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
