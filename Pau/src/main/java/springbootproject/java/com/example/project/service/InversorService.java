package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.project.controller.dto.InversorDTO;
import com.example.project.exceptions.InversorNoEncontradoException;
import com.example.project.exceptions.InversorYaRegistradoException;
import com.example.project.model.Inversor;
import com.example.project.repository.InversorRepository;

import java.util.List;

@Service
public class InversorService {
    private InversorRepository inversorRepository;

    @Autowired
    private InversorService(InversorRepository inversorRepository) {
        this.inversorRepository = inversorRepository;
    }

    public Inversor registrarInversor(InversorDTO inversorDTO) throws InversorYaRegistradoException {
        Inversor inversor = inversorRepository.buscarInversor(inversorDTO.getNombre());
        if (inversor != null) {
            throw new InversorYaRegistradoException("El inversor de nombre "+ inversorDTO.getNombre() + " ya fue registrado.");
        }
        Inversor nuevoInversor = new Inversor(inversorDTO.getNombre(), inversorDTO.getFechaDeNacimiento());
        return inversorRepository.agregarInversor(nuevoInversor);
    }

    public List<Inversor> obtenerInversores() {
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
