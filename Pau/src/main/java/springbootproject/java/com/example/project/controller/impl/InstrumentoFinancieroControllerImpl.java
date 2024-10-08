package com.example.project.controller.impl;

import com.example.project.controller.InstrumentoFinancieroController;
import com.example.project.controller.dto.InstrumentoFinancieroDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.project.controller.dto.EditarInstrumentoDTO;
import com.example.project.service.InstrumentoFinancieroService;

@Slf4j
@RestController
@RequestMapping("/instrumentos-financieros")
public class InstrumentoFinancieroControllerImpl implements InstrumentoFinancieroController {
    private final InstrumentoFinancieroService instrumentoFinancieroService;

    @Autowired
    public InstrumentoFinancieroControllerImpl(InstrumentoFinancieroService instrumentoFinancieroService) {
        this.instrumentoFinancieroService = instrumentoFinancieroService;
    }

    @GetMapping("/")
    public ResponseEntity<?> consultarTodosLosInstrumentosConocidos() {
        log.info("Consultando instrumentos financieros");
        return ResponseEntity.ok(instrumentoFinancieroService.consultarInstrumentosFinancieros());
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<?> consultarUnInstrumentoFinanciero(@PathVariable("nombre") String nombre) {
        log.info("Consultando instrumento financiero de nombre {}", nombre);
        return ResponseEntity.ok(this.instrumentoFinancieroService.buscarInstrumentoPorNombre(nombre));
    }

    @PostMapping("/")
    @Override
    public ResponseEntity<?> crearInstrumento(@RequestBody InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws Exception {
        log.info("Creando instrumento financiero ", instrumentoFinancieroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.instrumentoFinancieroService.registrarInstrumentoFinanciero(instrumentoFinancieroDTO));
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<?> deleteInstrumento(@PathVariable("nombre") String nombre) throws Exception{
        this.instrumentoFinancieroService.eliminarInstrumentoFinanciero(nombre);
        log.info("Eliminando instrumento financiero " + nombre);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{nombre}")
    public ResponseEntity<?> editarInstrumento(@RequestBody EditarInstrumentoDTO editarInstrumentoDTO, @PathVariable("nombre") String nombre) throws Exception {
        log.info("Editando instrumento financiero " + nombre);
        return ResponseEntity.ok(this.instrumentoFinancieroService.editarInstrumentoFinanciero(nombre, editarInstrumentoDTO));
    }
}
