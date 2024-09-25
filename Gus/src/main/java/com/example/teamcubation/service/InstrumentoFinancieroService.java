package com.example.teamcubation.service;


import com.example.teamcubation.exceptions.InstrumentoDuplicadoException;
import com.example.teamcubation.exceptions.InstrumentoNoEncontradoException;
import com.example.teamcubation.exceptions.ModeloInvalidoException;
import com.example.teamcubation.model.InstrumentoFinanciero;
import com.example.teamcubation.repository.interfaces.InstrumentoFinancieroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstrumentoFinancieroService {
    private final InstrumentoFinancieroRepository instrumentoFinancieroRepository;

    @Autowired
    public InstrumentoFinancieroService(InstrumentoFinancieroRepository instrumentoFinancieroRepository) {
        this.instrumentoFinancieroRepository = instrumentoFinancieroRepository;
    }


    public void crear(InstrumentoFinanciero nuevoInstrumento) throws InstrumentoDuplicadoException, ModeloInvalidoException {
        if (this.instrumentoDuplicado(nuevoInstrumento)) {
            throw new InstrumentoDuplicadoException("Error: Ya existe un " + nuevoInstrumento.getTipo() + " con ese nombre!!!. Por favor, pruebe ingresando un nombre distinto.");
        }

        this.validarModelo(nuevoInstrumento);

        this.instrumentoFinancieroRepository.create(nuevoInstrumento);
    }


    public List<InstrumentoFinanciero> listarTodosLosInstrumentosFinancieros() {
        return this.instrumentoFinancieroRepository
                .getAll();
    }

    public List<InstrumentoFinanciero> listarAcciones() {
        return this.instrumentoFinancieroRepository
                .getAcciones();
    }

    public List<InstrumentoFinanciero> listarBonos() {
        return this.instrumentoFinancieroRepository
                .getBonos();
    }

    public Optional<InstrumentoFinanciero> listarInstrumentoPorNombre(String nombreInstrumento) throws InstrumentoNoEncontradoException {

        if (instrumentoEsInexistente(nombreInstrumento)) {
            throw new InstrumentoNoEncontradoException("Error: No se encontro un instrumento con el nombre ingresado");
        }
        return this.instrumentoFinancieroRepository
                .getByName(nombreInstrumento);

    }


    public void editar(InstrumentoFinanciero instrumentoFinanciero, String nombre) throws InstrumentoNoEncontradoException, ModeloInvalidoException {
        if (instrumentoEsInexistente(nombre)) {
            throw new InstrumentoNoEncontradoException("Error: No se encontro un instrumento con el nombre ingresado");
        }

        this.validarModelo(instrumentoFinanciero);

        this.instrumentoFinancieroRepository.update(instrumentoFinanciero, nombre);
    }

//    public void editarNombreInstrumento(EditarInstrumentoDTO instrumentoDTO) throws InstrumentoNoEncontradoException {
//        if (instrumentoEsInexistente(instrumentoDTO.getNombreInstrumento())) {
//            throw new InstrumentoNoEncontradoException("Error: No se encontro un instrumento con el nombre ingresado");
//        }
//        this.instrumentoFinancieroRepository.updateName(instrumentoDTO);
//    }
//
//    public void editarPrecioInstrumento(EditarInstrumentoDTO instrumentoDTO) throws InstrumentoNoEncontradoException {
//        if (instrumentoEsInexistente(instrumentoDTO.getNombreInstrumento())) {
//            throw new InstrumentoNoEncontradoException("Error: No se encontro un instrumento con el nombre ingresado");
//        }
//        this.instrumentoFinancieroRepository.updatePrice(instrumentoDTO);
//    }

    public void eliminar(String nombreInstrumento) throws InstrumentoNoEncontradoException {
        if (instrumentoEsInexistente(nombreInstrumento)) {
            throw new InstrumentoNoEncontradoException("Error: No se encontro un instrumento con el nombre ingresado");
        }

        this.instrumentoFinancieroRepository.delete(nombreInstrumento);
    }


    //Validaciones

    private boolean instrumentoEsInexistente(String nombreInstrumento) {
        return this.instrumentoFinancieroRepository
                .getAll()
                .stream()
                .noneMatch(instrumentoFinanciero -> instrumentoFinanciero.getNombre().equalsIgnoreCase(nombreInstrumento));
    }

    private boolean instrumentoDuplicado(InstrumentoFinanciero nuevoInstrumento) {
        return this.instrumentoFinancieroRepository
                .getAll()
                .stream()
                .filter(instrumentoFinanciero -> instrumentoFinanciero.getTipo().equals(nuevoInstrumento.getTipo()))
                .anyMatch(instrumentoFinanciero -> instrumentoFinanciero.getNombre().equalsIgnoreCase(nuevoInstrumento.getNombre()));
    }

    private void validarModelo(InstrumentoFinanciero nuevoInstrumento) throws ModeloInvalidoException {
        if (nuevoInstrumento.getNombre().isBlank() || nuevoInstrumento.getNombre().isEmpty()) {
            throw new ModeloInvalidoException("Error: El nombre del instrumento no puede ser vacio");
        }

        if (nuevoInstrumento.getNombre().matches("^[a-zA-Z0-9\\s]+$\n")) {
            throw new ModeloInvalidoException("Error: El nombre del instrumento no puede contener caracteres especiales");
        }
        if (nuevoInstrumento.getPrecio() <= 0) {
            throw new ModeloInvalidoException("Error: El precio del instrumento debe ser mayor a 0");
        }
    }


}
