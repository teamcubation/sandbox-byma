package com.example.project.service;

import com.example.project.repository.interfaces.InstrumentoFinancieroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.project.controller.dto.EditarInstrumentoDTO;
import com.example.project.controller.dto.InstrumentoFinancieroDTO;
import com.example.project.exceptions.InstrumentoDuplicadoException;
import com.example.project.exceptions.InstrumentoNoEncontradoException;
import com.example.project.exceptions.NoExisteEseTipoDeInstrumentoException;
import com.example.project.model.instrumentofinanciero.InstrumentoFinanciero;
import com.example.project.model.instrumentofinanciero.factoryInstrumentos.AccionFactory;
import com.example.project.model.instrumentofinanciero.factoryInstrumentos.BonoFactory;

import java.util.List;

@Service
public class InstrumentoFinancieroService {
    private InstrumentoFinancieroRepository instrumentosFinancierosRepository;

    @Autowired
    public InstrumentoFinancieroService(InstrumentoFinancieroRepository instrumentosFinancierosRepository) {
        this.instrumentosFinancierosRepository = instrumentosFinancierosRepository;
    }

    public InstrumentoFinanciero registrarInstrumentoFinanciero(InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws InstrumentoDuplicadoException, NoExisteEseTipoDeInstrumentoException{
        InstrumentoFinanciero instrumentoFinanciero = this.buscarInstrumentoPorNombre(instrumentoFinancieroDTO.getNombre());
        if (instrumentoFinanciero != null) {
            throw new InstrumentoDuplicadoException("No se puede registrar el instrumento debido a que este ya fue registrado en el sistema con anterioridad.");
        }
        switch (instrumentoFinancieroDTO.getTipoInstrumentoFinanciero()) {
            case BONO:
                BonoFactory bonoFactory = new BonoFactory();
                instrumentoFinanciero = bonoFactory.createInstrumentoFinanciero(instrumentoFinancieroDTO.getNombre(), instrumentoFinancieroDTO.getPrecio(), instrumentoFinancieroDTO.getFechaDeEmision());
                break;
            case ACCION:
                AccionFactory accionFactory = new AccionFactory();
                instrumentoFinanciero = accionFactory.createInstrumentoFinanciero(instrumentoFinancieroDTO.getNombre(), instrumentoFinancieroDTO.getPrecio(), instrumentoFinancieroDTO.getFechaDeEmision());
                break;
        }
        return this.instrumentosFinancierosRepository.save(instrumentoFinanciero);
    }

    public InstrumentoFinanciero buscarInstrumentoPorNombre(String nombre) {
        return this.instrumentosFinancierosRepository.findAll().stream().filter(x -> x.getNombre().equals(nombre)).findFirst().orElse(null);
    }

    public List<InstrumentoFinanciero> consultarInstrumentosFinancieros() {
        return this.instrumentosFinancierosRepository.findAll();
    }

    public void eliminarInstrumentoFinanciero(String nombre) throws InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumentoFinanciero = this.buscarInstrumentoPorNombre(nombre);
        if (instrumentoFinanciero == null) {
            throw new InstrumentoNoEncontradoException("No se puede eliminar el instrumento financiero porque no existe en el sistema");
        }
        this.instrumentosFinancierosRepository.delete(instrumentoFinanciero);
    }

    public InstrumentoFinanciero editarInstrumentoFinanciero(String nombre, EditarInstrumentoDTO editarInstrumentoDTO) throws InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumentoFinanciero = this.buscarInstrumentoPorNombre(nombre);
        if (instrumentoFinanciero == null) {
            throw new InstrumentoNoEncontradoException("El instrumento con nombre " + nombre + " no fue encontrado.");
        }
        if(editarInstrumentoDTO.getNuevoNombre() != null) {
            instrumentoFinanciero.setNombre(editarInstrumentoDTO.getNuevoNombre());
        }
        if(editarInstrumentoDTO.getNuevoPrecio() != null) {
            instrumentoFinanciero.setPrecio(editarInstrumentoDTO.getNuevoPrecio());
        }
        if(editarInstrumentoDTO.getNuevaFechaDeEmision() != null) {
            instrumentoFinanciero.setFechaDeEmision(editarInstrumentoDTO.getNuevaFechaDeEmision());
        }
        return this.instrumentosFinancierosRepository.save(instrumentoFinanciero);
    }
}
