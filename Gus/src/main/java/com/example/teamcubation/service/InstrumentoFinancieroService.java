package com.example.teamcubation.service;


import com.example.teamcubation.model.Accion;
import com.example.teamcubation.model.Bono;
import com.example.teamcubation.model.InstrumentoFinanciero;
import com.example.teamcubation.repository.interfaces.AccionRepository;
import com.example.teamcubation.repository.interfaces.BonoRepository;
import com.example.teamcubation.repository.interfaces.InstrumentoFinancieroRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InstrumentoFinancieroService {
    private final InstrumentoFinancieroRepository instrumentoFinancieroRepository;
    private final AccionRepository accionRepository;
    private final BonoRepository bonoRepository;


    @Autowired
    public InstrumentoFinancieroService(InstrumentoFinancieroRepository instrumentoFinancieroRepository, AccionRepository accionRepository, BonoRepository bonoRepository) {
        this.instrumentoFinancieroRepository = instrumentoFinancieroRepository;
        this.accionRepository = accionRepository;
        this.bonoRepository = bonoRepository;
    }


    public void crear(InstrumentoFinanciero nuevoInstrumento) {

        this.instrumentoFinancieroRepository.save(nuevoInstrumento);
    }

    public void createAccion(Accion nuevaAccion) {

        this.accionRepository.save(nuevaAccion);
    }

    public void createBono(Bono nuevoBono) {

        this.bonoRepository.save(nuevoBono);
    }


    public List<InstrumentoFinanciero> getAll() {
        return instrumentoFinancieroRepository.findAll();
    }

    public List<Accion> listarAcciones() {
        return accionRepository.findAll();
    }

    public List<Bono> listarBonos() {
        return this.bonoRepository.findAll();
    }

    public Optional<InstrumentoFinanciero> getById(long id) {

        return this.instrumentoFinancieroRepository.findById(id);

    }


    public void update(InstrumentoFinanciero instrumentoFinanciero) {

        this.instrumentoFinancieroRepository.save(instrumentoFinanciero);
    }


    public void delete(long id) {

        this.instrumentoFinancieroRepository.deleteById(id);
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
