package com.example.teamcubation.controller;


import com.example.teamcubation.exceptions.InstrumentoDuplicadoException;
import com.example.teamcubation.exceptions.InstrumentoNoEncontradoException;
import com.example.teamcubation.model.InstrumentoDTO.InstrumentoFinancieroDTO;
import com.example.teamcubation.model.InstrumentoFinanciero;
import com.example.teamcubation.service.InstrumentoFinancieroFactoryService;
import com.example.teamcubation.service.InstrumentoFinancieroService;
import com.example.teamcubation.util.InstrumentoFinancieroMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instrumentos-financieros")
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

        try {
            InstrumentoFinanciero nuevo = InstrumentoFinancieroMapper.instrumentoDTOtoInstrumentoFinanciero(nuevoInstrumento, instrumentoFinancieroFactoryService);
            instrumentoFinancieroService.registrarNuevoInstrumento(nuevo);
            return new ResponseEntity<>(nuevo, null, HttpStatus.CREATED);
        } catch (InstrumentoDuplicadoException e) {
            return new ResponseEntity<>(e.getMessage(), null, HttpStatus.CONFLICT);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error: Datos ingresados invalidos. Intente nuevamente", null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/consultar-acciones")
    public ResponseEntity<?> listarAcciones() {

        List<InstrumentoFinancieroDTO> listaDeAcciones = instrumentoFinancieroService
                .listarAcciones()
                .stream()
                .map(InstrumentoFinancieroMapper::instrumentoFinancieroToInstrumentoDTO)
                .toList();

        return new ResponseEntity<>(listaDeAcciones, null, HttpStatus.OK);
    }

    @RequestMapping("/consultar-bonos")
    public ResponseEntity<?> listarBonos() {

        List<InstrumentoFinancieroDTO> listaDeBonos = instrumentoFinancieroService
                .listarBonos()
                .stream()
                .map(InstrumentoFinancieroMapper::instrumentoFinancieroToInstrumentoDTO)
                .toList();
        return new ResponseEntity<>(listaDeBonos, null, HttpStatus.OK);
    }


    @PutMapping("/editar/{nombre}")
    public ResponseEntity<?> update(@PathVariable String nombre, @RequestBody InstrumentoFinancieroDTO instrumentoDTO) {

        try {
            InstrumentoFinanciero instrumentoFinanciero = InstrumentoFinancieroMapper.instrumentoDTOtoInstrumentoFinanciero(instrumentoDTO, instrumentoFinancieroFactoryService);
            instrumentoFinancieroService.editarInstrumento(instrumentoFinanciero, nombre);

            return new ResponseEntity<>(instrumentoFinanciero, null, HttpStatus.OK);
        } catch (InstrumentoNoEncontradoException e) {
            System.err.println("Error: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), null, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error: Datos ingresados invalidos. Intente nuevamente", null, HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/eliminar-accion/{nombre}")
    public ResponseEntity<?> eliminarAccion(@PathVariable String nombre) {
        try {
            instrumentoFinancieroService.eliminarInstrumento(nombre);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (InstrumentoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar-bono/{nombre}")
    public ResponseEntity<?> eliminarBono(@PathVariable String nombre) {
        try {
            instrumentoFinancieroService.eliminarInstrumento(nombre);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (InstrumentoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{nombre}")
    public ResponseEntity<?> eliminarInstrumento(@PathVariable String nombre) {
        try {
            instrumentoFinancieroService.eliminarInstrumento(nombre);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (InstrumentoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), null, HttpStatus.NOT_FOUND);
        }
    }


}
