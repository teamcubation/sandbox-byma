package com.example.teamcubation.controller;


import com.example.teamcubation.exceptions.InstrumentoDuplicadoException;
import com.example.teamcubation.exceptions.InstrumentoNoEncontradoException;
import com.example.teamcubation.model.InstrumentoDTO.EditarInstrumentoDTO;
import com.example.teamcubation.model.InstrumentoDTO.InstrumentoDTO;
import com.example.teamcubation.model.InstrumentoFinanciero;
import com.example.teamcubation.model.instrumentoEnums.TipoInstrumentoFinanciero;
import com.example.teamcubation.service.InstrumentoFinancieroFactoryService;
import com.example.teamcubation.service.InstrumentoFinancieroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instrumentoFinanciero")
public class InstrumentoFinancieroController {

    private final InstrumentoFinancieroService instrumentoFinancieroService;
    private final InstrumentoFinancieroFactoryService instrumentoFinancieroFactoryService;

    @Autowired
    public InstrumentoFinancieroController(InstrumentoFinancieroService instrumentoFinancieroService, InstrumentoFinancieroFactoryService instrumentoFinancieroFactoryService) {
        this.instrumentoFinancieroService = instrumentoFinancieroService;
        this.instrumentoFinancieroFactoryService = instrumentoFinancieroFactoryService;
    }


    @PostMapping("/crearInstrumento")
    public ResponseEntity<?> crearInstrumento(@RequestBody InstrumentoDTO nuevoInstrumento) {
        InstrumentoFinanciero nuevo = instrumentoFinancieroFactoryService.crearInstrumento(nuevoInstrumento.getNombreInstrumento(), nuevoInstrumento.getPrecio(), TipoInstrumentoFinanciero.valueOf(nuevoInstrumento.getTipo().toUpperCase()));
        try {
            instrumentoFinancieroService.registrarNuevoInstrumento(nuevo, TipoInstrumentoFinanciero.valueOf(nuevoInstrumento.getTipo().toUpperCase()));
            return new ResponseEntity<>("Instrumento: " + nuevo.mostrarInstrumento() + " creado con exito!!!", null, HttpStatus.OK);
        } catch (InstrumentoDuplicadoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping("/consultarAcciones")
    public ResponseEntity<?> listarAcciones() {

        return new ResponseEntity<>(instrumentoFinancieroService.listarBonos(), null, HttpStatus.OK);
    }

    @RequestMapping("/consultarBonos")
    public ResponseEntity<?> listarBonos() {

        return new ResponseEntity<>(instrumentoFinancieroService.listarBonos(), null, HttpStatus.OK);
    }


    @PutMapping("/editarInstrumento")
    public ResponseEntity<?> editarInstrumento(@RequestBody EditarInstrumentoDTO instrumentoDTO) {
        try {
            instrumentoFinancieroService.editarInstrumento(instrumentoDTO.getNuevoNombre(), instrumentoDTO.getNuevoPrecio(), instrumentoDTO.getNombreInstrumento(), TipoInstrumentoFinanciero.valueOf(instrumentoDTO.getTipo().toUpperCase()));

            return new ResponseEntity<>("Instrumento editado con exito!!!", null, HttpStatus.OK);
        } catch (InstrumentoNoEncontradoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/eliminarInstrumento/{tipo}/{nombre}")
    public ResponseEntity<?> eliminarInstrumento(@PathVariable String tipo, @PathVariable String nombre) {
        try {
            InstrumentoFinanciero instrumentoAEliminar = instrumentoFinancieroService.listarInstrumentoPorNombre(nombre, TipoInstrumentoFinanciero.valueOf(tipo.toUpperCase())).get();
            instrumentoFinancieroService.eliminarInstrumento(nombre, TipoInstrumentoFinanciero.valueOf(tipo.toUpperCase()));
            return new ResponseEntity<>("Instrumento: " + instrumentoAEliminar.mostrarInstrumento() + " eliminado con éxito!!!", null, HttpStatus.OK);
        } catch (InstrumentoNoEncontradoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminarInstrumento2")
    public ResponseEntity<?> eliminarInstrumento2(@RequestParam(required = true) String tipo, @RequestParam String nombre) {
        try {
            InstrumentoFinanciero instrumentoAEliminar = instrumentoFinancieroService.listarInstrumentoPorNombre(nombre, TipoInstrumentoFinanciero.valueOf(tipo.toUpperCase())).get();
            instrumentoFinancieroService.eliminarInstrumento(nombre, TipoInstrumentoFinanciero.valueOf(tipo.toUpperCase()));
            return new ResponseEntity<>("Instrumento: " + instrumentoAEliminar.mostrarInstrumento() + " eliminado con éxito!!!", null, HttpStatus.OK);
        } catch (InstrumentoNoEncontradoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
