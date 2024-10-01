package com.example.project.controller.impl;

import com.example.project.controller.dto.InstrumentoFinancieroDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.project.controller.dto.EditarInstrumentoDTO;
import com.example.project.exceptions.InstrumentoNoEncontradoException;
import com.example.project.service.InstrumentoFinancieroService;

@Slf4j
@RestController
@RequestMapping("/instrumentos-financieros")
public class InstrumentoFinancieroControllerImpl {
    private final InstrumentoFinancieroService instrumentoFinancieroService;

    @Autowired
    public InstrumentoFinancieroControllerImpl(InstrumentoFinancieroService instrumentoFinancieroService) {
        this.instrumentoFinancieroService = instrumentoFinancieroService;
    }

//    @RequestMapping("/home")
//    public String home() {
//        return "Hello World";
//    }

    @RequestMapping("/")
    public ResponseEntity<?> consultarTodosLosInstrumentosConocidos() {
        log.info("Consultando instrumentos financieros");
        return ResponseEntity.ok(instrumentoFinancieroService.consultarInstrumentosFinancieros());
    }

    @RequestMapping("/{nombre}")
    public ResponseEntity<?> consultarUnInstrumentoFinanciero(@PathVariable("nombre") String nombre) {
        log.info("Consultando instrumento financiero de nombre {}", nombre);
        return ResponseEntity.ok(this.instrumentoFinancieroService.buscarInstrumentoPorNombre(nombre));
    }

    @PostMapping("/")
    public ResponseEntity<?> crearInstrumento(@RequestBody InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws Exception {
        log.info("Creando instrumento financiero ", instrumentoFinancieroDTO);
        return ResponseEntity.ok(this.instrumentoFinancieroService.registrarInstrumentoFinanciero(instrumentoFinancieroDTO));
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<?> deleteInstrumento(@PathVariable("nombre") String nombre) throws Exception{
        this.instrumentoFinancieroService.eliminarInstrumentoFinanciero(nombre);
        log.info("Eliminando instrumento financiero " + nombre);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{nombre}")
    public ResponseEntity<?> editarInstrumento(@RequestBody EditarInstrumentoDTO editarInstrumentoDTO, @PathVariable("nombre") String nombre) throws InstrumentoNoEncontradoException {
        log.info("Editando instrumento financiero " + nombre);
        return ResponseEntity.ok(this.instrumentoFinancieroService.editarInstrumentoFinanciero(nombre, editarInstrumentoDTO));
    }
}
