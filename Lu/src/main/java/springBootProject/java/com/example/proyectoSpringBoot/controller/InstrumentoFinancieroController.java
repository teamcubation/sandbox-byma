package com.example.proyectoSpringBoot.controller;

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
public class InstrumentoFinancieroController implements InstrumentoFinancieroApi {

    @Autowired
    private InstrumentoFinancieroServiceImpl instrumentoFinancieroService;

    @Override
    @GetMapping("")
    public List<InstrumentoFinancieroDTO> consultarTodos() {
        log.info("Consultando instrumentos financieros");
        List<InstrumentoFinancieroDTO> instrumentosFinancieros = this.instrumentoFinancieroService.consultarTodos();
        log.info("Instrumentos financieros consultados: {}", instrumentosFinancieros.size());
        return instrumentosFinancieros;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> consultar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.instrumentoFinancieroService.consultar(id));
    }

    @Override
    @PostMapping("")
    public ResponseEntity<?> registrar (@RequestBody InstrumentoFinancieroDTO instrumentoDTO) {
        log.info("registrando instrumento financiero");
        InstrumentoFinancieroDTO instrumentoFinancieroNuevo = this.instrumentoFinancieroService.registrar(instrumentoDTO);
        log.info("instrumento financiero registrado");
        return ResponseEntity.status(HttpStatus.CREATED).body(instrumentoFinancieroNuevo);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Long id) {
        this.instrumentoFinancieroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") Long id, @RequestBody InstrumentoFinancieroDTO instrumentoDTO) {
        InstrumentoFinancieroDTO instrumentoFinancieroEditado = this.instrumentoFinancieroService.editar(id, instrumentoDTO);
        return ResponseEntity.ok(instrumentoFinancieroEditado);
    }
}
