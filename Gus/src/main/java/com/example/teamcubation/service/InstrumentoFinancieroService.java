package com.example.teamcubation.service;


import com.example.teamcubation.exceptions.InstrumentoDuplicadoException;
import com.example.teamcubation.exceptions.InstrumentoNoEncontradoException;
import com.example.teamcubation.model.InstrumentoFinanciero;
import com.example.teamcubation.model.instrumentoEnums.TipoInstrumentoFinanciero;
import com.example.teamcubation.repository.InstrumentoFinancieroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InstrumentoFinancieroService {
    private final InstrumentoFinancieroRepository instrumentoFinancieroRepository;

    @Autowired
    public InstrumentoFinancieroService(InstrumentoFinancieroRepository instrumentoFinancieroRepository) {
        this.instrumentoFinancieroRepository = instrumentoFinancieroRepository;
    }


    public void registrarNuevoInstrumento(InstrumentoFinanciero nuevoInstrumento, TipoInstrumentoFinanciero tipoInstrumentoFinanciero) throws InstrumentoDuplicadoException {
        if (!nombreInstrumentoEsInexistente(nuevoInstrumento.getNombre(), tipoInstrumentoFinanciero)) {
            throw new InstrumentoDuplicadoException("Error: Ya existe un instrumento con ese nombre!!!. Por favor, pruebe ingresando un nombre distinto.");
        }
        this.instrumentoFinancieroRepository.registrarNuevoInstrumento(nuevoInstrumento);
    }


    public ArrayList<InstrumentoFinanciero> listarTodosLosInstrumentosFinancieros() {
        return this.instrumentoFinancieroRepository
                .listarBonosYAcciones()
                .stream()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<InstrumentoFinanciero> listarAcciones() {
        return this.instrumentoFinancieroRepository
                .consultarAcciones()
                .stream()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<InstrumentoFinanciero> listarBonos() {
        return this.instrumentoFinancieroRepository
                .consultarBonos()
                .stream()
                .toList();
    }

    public Optional<InstrumentoFinanciero> listarInstrumentoPorNombre(String nombreInstrumento, TipoInstrumentoFinanciero tipoInstrumentoFinanciero) throws InstrumentoNoEncontradoException {

        if (nombreInstrumentoEsInexistente(nombreInstrumento, tipoInstrumentoFinanciero)) {
            throw new InstrumentoNoEncontradoException("Error: No se encontro un instrumento con el nombre ingresado");
        }
        return this.instrumentoFinancieroRepository
                .listarInstrumentoPorNombre(nombreInstrumento);

    }


    public void editarInstrumento(String nuevoNombre, double nuevoPrecio, String nombreInstrumento, TipoInstrumentoFinanciero tipoInstrumentoFinanciero) throws InstrumentoNoEncontradoException {
        if (nombreInstrumentoEsInexistente(nombreInstrumento, tipoInstrumentoFinanciero)) {
            throw new InstrumentoNoEncontradoException("Error: No se encontro un instrumento con el nombre ingresado");
        }

        this.instrumentoFinancieroRepository.editarInstrumento(nuevoNombre, nuevoPrecio, nombreInstrumento);
    }

    public void editarNombreInstrumento(String nuevoNombre, String nombreInstrumento, TipoInstrumentoFinanciero tipoInstrumentoFinanciero) throws InstrumentoNoEncontradoException {
        if (nombreInstrumentoEsInexistente(nombreInstrumento, tipoInstrumentoFinanciero)) {
            throw new InstrumentoNoEncontradoException("Error: No se encontro un instrumento con el nombre ingresado");
        }
        this.instrumentoFinancieroRepository.editarNombreInstrumento(nuevoNombre, nombreInstrumento);
    }

    public void editarPrecioInstrumento(double nuevoPrecio, String nombreInstrumento, TipoInstrumentoFinanciero tipoInstrumentoFinanciero) throws InstrumentoNoEncontradoException {
        if (nombreInstrumentoEsInexistente(nombreInstrumento, tipoInstrumentoFinanciero)) {
            throw new InstrumentoNoEncontradoException("Error: No se encontro un instrumento con el nombre ingresado");
        }
        this.instrumentoFinancieroRepository.editarPrecioInstrumento(nuevoPrecio, nombreInstrumento);
    }

    public void eliminarInstrumento(String nombreInstrumento, TipoInstrumentoFinanciero tipoInstrumentoFinanciero) throws InstrumentoNoEncontradoException {
        if (nombreInstrumentoEsInexistente(nombreInstrumento, tipoInstrumentoFinanciero)) {
            throw new InstrumentoNoEncontradoException("Error: No se encontro un instrumento con el nombre ingresado");
        }

        this.instrumentoFinancieroRepository.eliminarInstrumento(nombreInstrumento);
    }


    //Validaciones

    private boolean nombreInstrumentoEsInexistente(String nombreInstrumento, TipoInstrumentoFinanciero tipoInstrumentoFinanciero) {
        return this.instrumentoFinancieroRepository
                .listarBonosYAcciones()
                .stream()
                .filter(instrumentoFinanciero -> instrumentoFinanciero.getTipo().equals(tipoInstrumentoFinanciero))
                .noneMatch(instrumentoFinanciero -> instrumentoFinanciero.getNombre().equalsIgnoreCase(nombreInstrumento));
    }


}
