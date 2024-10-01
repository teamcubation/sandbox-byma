package com.example.proyectoSpringBoot.controller;

import com.example.proyectoSpringBoot.excepciones.customExcepcions.InstrumentoNoEncontradoException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.proyectoSpringBoot.dto.InstrumentoFinancieroDTO;
import com.example.proyectoSpringBoot.service.serviceImpl.InstrumentoFinancieroServiceImpl;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/instrumentos-financieros")
public class InstrumentoFinancieroController {

    @Autowired
    private InstrumentoFinancieroServiceImpl instrumentoFinancieroService;

    @GetMapping("")
    private List<InstrumentoFinancieroDTO> consultarTodos() {
        log.info("Consultando instrumentos financieros");
        List<InstrumentoFinancieroDTO> instrumentosFinancieros = this.instrumentoFinancieroService.consultarTodos();
        log.info("Instrumentos financieros consultados: {}", instrumentosFinancieros.size());
        return instrumentosFinancieros;
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> consultar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.instrumentoFinancieroService.consultar(id));
    }

    @PostMapping("")
    private ResponseEntity<?> registrar (@RequestBody InstrumentoFinancieroDTO instrumentoDTO) {
        log.info("registrando instrumento financiero");
        InstrumentoFinancieroDTO instrumentoFinancieroNuevo = this.instrumentoFinancieroService.registrar(instrumentoDTO);
        log.info("instrumento financiero registrado");
        return ResponseEntity.status(HttpStatus.CREATED).body(instrumentoFinancieroNuevo);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> eliminar(@PathVariable("id") Long id) {
        this.instrumentoFinancieroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> editar(@PathVariable("id") Long id, @RequestBody InstrumentoFinancieroDTO instrumentoDTO) {
        InstrumentoFinancieroDTO instrumentoFinancieroEditado = this.instrumentoFinancieroService.editar(id, instrumentoDTO);
        return ResponseEntity.ok(instrumentoFinancieroEditado);
    }
}
