package com.example.teamcubation.service;


import com.example.teamcubation.exceptions.InstrumentoDuplicadoException;
import com.example.teamcubation.exceptions.InstrumentoNoEncontradoException;
import com.example.teamcubation.model.Accion;
import com.example.teamcubation.model.Bono;
import com.example.teamcubation.repository.interfaces.AccionRepository;
import com.example.teamcubation.repository.interfaces.BonoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InstrumentoFinancieroService {
    private final AccionRepository accionRepository;
    private final BonoRepository bonoRepository;


    @Autowired
    public InstrumentoFinancieroService(AccionRepository accionRepository, BonoRepository bonoRepository) {
        this.accionRepository = accionRepository;
        this.bonoRepository = bonoRepository;
    }


    public void createAccion(Accion nuevaAccion) throws InstrumentoDuplicadoException {

        if (this.accionEsDuplicado(nuevaAccion.getNombre())) {
            throw new InstrumentoDuplicadoException("Error: Ya existe un instrumento con el mismo nombre");
        }

        this.accionRepository.save(nuevaAccion);
    }

    public void createBono(Bono nuevoBono) throws InstrumentoDuplicadoException {

        if (this.bonoEsDuplicado(nuevoBono.getNombre())) {
            throw new InstrumentoDuplicadoException("Error: Ya existe un instrumento con el mismo nombre");
        }
        this.bonoRepository.save(nuevoBono);
    }


    public List<Accion> listarAcciones() {
        return accionRepository.findAll();
    }

    public List<Bono> listarBonos() {
        return this.bonoRepository.findAll();
    }


    public void updateAccion(Accion accion) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {

        if (this.accionEsDuplicado(accion.getNombre())) {

            throw new InstrumentoDuplicadoException("Error: Ya existe un instrumento con el mismo nombre");
        }

        if (this.accionEsInexistente(accion.getId())) {
            throw new InstrumentoNoEncontradoException("Error: El instrumento no se encuentra en la base de datos");
        }
        this.accionRepository.save(accion);

    }

    public void updateBono(Bono bono) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {

        if (this.accionEsDuplicado(bono.getNombre())) {

            throw new InstrumentoDuplicadoException("Error: Ya existe un instrumento con el mismo nombre");
        }

        if (this.bonoEsInexistente(bono.getId())) {
            throw new InstrumentoNoEncontradoException("Error: El instrumento no se encuentra en la base de datos");
        }
        this.bonoRepository.save(bono);

    }


    public void deleteAccion(long id) throws InstrumentoNoEncontradoException {


        if (this.accionEsInexistente(id)) {
            throw new InstrumentoNoEncontradoException("Error: El instrumento no se encuentra en la base de datos");
        }

        this.accionRepository.deleteById(id);
    }

    public void deleteBono(long id) throws InstrumentoNoEncontradoException {


        if (this.bonoEsInexistente(id)) {
            throw new InstrumentoNoEncontradoException("Error: El instrumento no se encuentra en la base de datos");
        }

        this.bonoRepository.deleteById(id);
    }

    //validaciones

    private boolean bonoEsInexistente(long id) {

        return !this.bonoRepository
                .existsById(id);
    }


    private boolean accionEsInexistente(long id) {
        return !this.accionRepository
                .existsById(id);
    }

    private boolean accionEsDuplicado(String nombre) {
        return this.accionRepository
                .findAll()
                .stream()
                .anyMatch(accion -> accion.getNombre().equalsIgnoreCase(nombre));
    }

    private boolean bonoEsDuplicado(String nombre) {
        return this.bonoRepository
                .findAll()
                .stream()
                .anyMatch(bono -> bono.getNombre().equalsIgnoreCase(nombre));
    }


//    //Validaciones
//
//    private boolean instrumentoEsInexistente(String nombreInstrumento) {
//        return this.instrumentoFinancieroRepository
//                .getAll()
//                .stream()
//                .noneMatch(instrumentoFinanciero -> instrumentoFinanciero.getNombre().equalsIgnoreCase(nombreInstrumento));
//    }
//
//    private boolean instrumentoDuplicado(InstrumentoFinanciero nuevoInstrumento) {
//        return this.instrumentoFinancieroRepository
//                .getAll()
//                .stream()
//                .filter(instrumentoFinanciero -> instrumentoFinanciero.getTipo().equals(nuevoInstrumento.getTipo()))
//                .anyMatch(instrumentoFinanciero -> instrumentoFinanciero.getNombre().equalsIgnoreCase(nuevoInstrumento.getNombre()));
//    }
//
//    private void validarModelo(InstrumentoFinanciero nuevoInstrumento) throws ModeloInvalidoException {
//        if (nuevoInstrumento.getNombre().isBlank() || nuevoInstrumento.getNombre().isEmpty()) {
//
//            log.error("Error: El nombre del instrumento no puede ser vacio");
//            throw new ModeloInvalidoException("Error: El nombre del instrumento no puede ser vacio");
//        }
//
//        if (nuevoInstrumento.getNombre().matches("^[a-zA-Z0-9\\s]+$\n")) {
//
//            log.error("Error: El nombre del instrumento no puede contener caracteres especiales");
//            throw new ModeloInvalidoException("Error: El nombre del instrumento no puede contener caracteres especiales");
//        }
//        if (nuevoInstrumento.getPrecio() <= 0) {
//
//            log.error("Error: El precio del instrumento debe ser mayor a 0");
//            throw new ModeloInvalidoException("Error: El precio del instrumento debe ser mayor a 0");
//        }
//    }


}
