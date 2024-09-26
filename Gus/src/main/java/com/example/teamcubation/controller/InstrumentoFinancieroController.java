package com.example.teamcubation.controller;


import com.example.teamcubation.exceptions.InstrumentoDuplicadoException;
import com.example.teamcubation.exceptions.InstrumentoNoEncontradoException;
import com.example.teamcubation.exceptions.ModeloInvalidoException;
import com.example.teamcubation.model.InstrumentoDTO.InstrumentoFinancieroDTO;
import com.example.teamcubation.model.InstrumentoFinanciero;
import com.example.teamcubation.service.InstrumentoFinancieroFactoryService;
import com.example.teamcubation.service.InstrumentoFinancieroService;
import com.example.teamcubation.util.InstrumentoFinancieroMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instrumentos-financieros")
@Slf4j
public class InstrumentoFinancieroController {

    private final InstrumentoFinancieroService instrumentoFinancieroService;
    private final InstrumentoFinancieroFactoryService instrumentoFinancieroFactoryService;

    @Autowired
    public InstrumentoFinancieroController(InstrumentoFinancieroService instrumentoFinancieroService, InstrumentoFinancieroFactoryService instrumentoFinancieroFactoryService) {
        this.instrumentoFinancieroService = instrumentoFinancieroService;
        this.instrumentoFinancieroFactoryService = instrumentoFinancieroFactoryService;
    }


    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody InstrumentoFinancieroDTO nuevoInstrumento) throws InstrumentoDuplicadoException, ModeloInvalidoException {
        log.info("Instrumento a crear: " + nuevoInstrumento.toString());

        InstrumentoFinanciero nuevo = InstrumentoFinancieroMapper.instrumentoDTOtoInstrumentoFinanciero(nuevoInstrumento, instrumentoFinancieroFactoryService);

        instrumentoFinancieroService.crear(nuevo);

        log.info("Instrumento creado: " + nuevo.toString());

        return new ResponseEntity<>(nuevo, null, HttpStatus.CREATED);

    }

    @RequestMapping("/consultar-acciones")
    public ResponseEntity<?> listarAcciones() {

        List<InstrumentoFinancieroDTO> listaDeAcciones = instrumentoFinancieroService
                .listarAcciones()
                .stream()
                .map(InstrumentoFinancieroMapper::instrumentoFinancieroToInstrumentoDTO)
                .toList();

        log.info(listaDeAcciones.toString());
        return new ResponseEntity<>(listaDeAcciones, null, HttpStatus.OK);
    }

    @RequestMapping("/consultar-bonos")
    public ResponseEntity<?> listarBonos() {

        List<InstrumentoFinancieroDTO> listaDeBonos = instrumentoFinancieroService
                .listarBonos()
                .stream()
                .map(InstrumentoFinancieroMapper::instrumentoFinancieroToInstrumentoDTO)
                .toList();

        log.info(listaDeBonos.toString());

        return new ResponseEntity<>(listaDeBonos, null, HttpStatus.OK);
    }


    //devolver el dto
    @PutMapping("/editar/{nombre}")
    public ResponseEntity<?> update(@PathVariable String nombre, @RequestBody InstrumentoFinancieroDTO instrumentoDTO) throws ModeloInvalidoException, InstrumentoNoEncontradoException {

        log.info("PathVariable nombre=  " + nombre);
        log.info(instrumentoDTO.toString());
        InstrumentoFinanciero instrumentoFinanciero = InstrumentoFinancieroMapper.instrumentoDTOtoInstrumentoFinanciero(instrumentoDTO, instrumentoFinancieroFactoryService);
        instrumentoFinancieroService.editar(instrumentoFinanciero, nombre);

        log.info("Instrumento actualizado: " + instrumentoFinanciero.toString());
        return new ResponseEntity<>(instrumentoDTO, null, HttpStatus.OK);

    }

    @DeleteMapping("/eliminar/{nombre}")
    public ResponseEntity<?> eliminarInstrumento(@PathVariable String nombre) throws InstrumentoNoEncontradoException {

        log.info("PathVariable nombre=  " + nombre);
        instrumentoFinancieroService.eliminar(nombre);

        log.info("Instrumento eliminado: " + nombre);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


}
