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
    public ResponseEntity<?> create(@RequestBody InstrumentoFinancieroDTO nuevoInstrumento) {
        log.info("Instrumento a crear: " + nuevoInstrumento.toString());

        try {


            InstrumentoFinanciero nuevo = InstrumentoFinancieroMapper.instrumentoDTOtoInstrumentoFinanciero(nuevoInstrumento, instrumentoFinancieroFactoryService);
            instrumentoFinancieroService.crear(nuevo);

            log.info("Instrumento creado: " + nuevo.toString());

            return new ResponseEntity<>(nuevo, null, HttpStatus.CREATED);
        } catch (InstrumentoDuplicadoException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), null, HttpStatus.CONFLICT);
        } catch (ModeloInvalidoException e) {
            log.error(e.getMessage());

            return new ResponseEntity<>(e.getMessage(), null, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            log.error(ex.getMessage());

            return new ResponseEntity<>("Error: datos ingresados invalidos, intente de nuevo!!!", null, HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity<?> update(@PathVariable String nombre, @RequestBody InstrumentoFinancieroDTO instrumentoDTO) {

        log.info("PathVariable nombre=  " + nombre);
        log.info(instrumentoDTO.toString());

        try {
            InstrumentoFinanciero instrumentoFinanciero = InstrumentoFinancieroMapper.instrumentoDTOtoInstrumentoFinanciero(instrumentoDTO, instrumentoFinancieroFactoryService);
            instrumentoFinancieroService.editar(instrumentoFinanciero, nombre);

            log.info("Instrumento actualizado: " + instrumentoFinanciero.toString());
            return new ResponseEntity<>(instrumentoDTO, null, HttpStatus.OK);
        } catch (InstrumentoNoEncontradoException e) {

            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), null, HttpStatus.NOT_FOUND);
        } catch (ModeloInvalidoException e) {

            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), null, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {

            log.error(ex.getMessage());
            return new ResponseEntity<>("Error: Datos ingresados invalidos. Intente nuevamente", null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminar/{nombre}")
    public ResponseEntity<?> eliminarInstrumento(@PathVariable String nombre) {

        log.info("PathVariable nombre=  " + nombre);

        try {
            instrumentoFinancieroService.eliminar(nombre);

            log.info("Instrumento eliminado: " + nombre);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (InstrumentoNoEncontradoException e) {

            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), null, HttpStatus.NOT_FOUND);
        }
    }

//
//    @DeleteMapping("/eliminar-accion/{nombre}")
//    public ResponseEntity<?> eliminarAccion(@PathVariable String nombre) {
//        try {
//            instrumentoFinancieroService.eliminar(nombre);
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        } catch (InstrumentoNoEncontradoException e) {
//            return new ResponseEntity<>(e.getMessage(), null, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/eliminar-bono/{nombre}")
//    public ResponseEntity<?> eliminarBono(@PathVariable String nombre) {
//        try {
//            instrumentoFinancieroService.eliminar(nombre);
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        } catch (InstrumentoNoEncontradoException e) {
//            return new ResponseEntity<>(e.getMessage(), null, HttpStatus.NOT_FOUND);
//        }
//    }


}
