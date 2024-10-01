package com.example.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.project.controller.dto.InversorDTO;
import com.example.project.exceptions.InversorNoEncontradoException;
import com.example.project.exceptions.InversorYaRegistradoException;
import com.example.project.model.Inversor;
import com.example.project.service.InversorService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/inversores")
public class InversorController {
    private final InversorService inversorService;

    @Autowired
    public InversorController(InversorService inversorService) {
        this.inversorService = inversorService;
    }

    @RequestMapping("/")
    public List<Inversor> obtenerInversores() {
        log.info("Consultando inversores.");
        return inversorService.obtenerInversores();
    }

    @PostMapping("/")
    public ResponseEntity<?> registrarInversor(@RequestBody InversorDTO inversorDTO) throws Exception {

        log.info("Registrando inversor: {}", inversorDTO);
        return ResponseEntity.ok(this.inversorService.registrarInversor(inversorDTO));

    }

    @RequestMapping("/{nombre}")
    public ResponseEntity<?> obtenerInversorPorNombre(@PathVariable String nombre) throws Exception {
        log.info("Buscando inversor por nombre: {}", nombre);
        return ResponseEntity.ok(this.inversorService.consultarPorUnInversor(nombre));
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<?> eliminarInversorPorNombre(@PathVariable String nombre) throws Exception {
        this.inversorService.eliminarInversor(nombre);
        log.info("Eliminando inversor de nombre: {}", nombre);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
