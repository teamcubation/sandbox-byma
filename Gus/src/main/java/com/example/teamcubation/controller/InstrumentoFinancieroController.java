package com.example.teamcubation.controller;


import com.example.teamcubation.exceptions.InstrumentoDuplicadoException;
import com.example.teamcubation.exceptions.InstrumentoNoEncontradoException;
import com.example.teamcubation.exceptions.ModeloInvalidoException;
import com.example.teamcubation.model.Accion;
import com.example.teamcubation.model.Bono;
import com.example.teamcubation.model.InstrumentoDTO.AccionDTO;
import com.example.teamcubation.model.InstrumentoDTO.BonoDTO;
import com.example.teamcubation.model.InstrumentoFinanciero;
import com.example.teamcubation.service.InstrumentoFinancieroService;
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

    @Autowired
    public InstrumentoFinancieroController(InstrumentoFinancieroService instrumentoFinancieroService) {
        this.instrumentoFinancieroService = instrumentoFinancieroService;
    }


    //Create
    @PostMapping("/accion")
    public ResponseEntity<?> createAccion(@RequestBody AccionDTO nuevaAccion) throws ModeloInvalidoException, InstrumentoDuplicadoException {
        log.info("Instrumento a crear: " + nuevaAccion.toString());

        this.validarAccionDTO(nuevaAccion);

        Accion nuevo = Accion
                .builder()
                .nombre(nuevaAccion.getNombre())
                .precio(nuevaAccion.getPrecio())
                .dividendo(nuevaAccion.getDividendo())
                .build();
        instrumentoFinancieroService.createAccion(nuevo);

        log.info("Instrumento creado: " + nuevo);

        return new ResponseEntity<>(nuevo, null, HttpStatus.CREATED);

    }

    @PostMapping("/bono")
    public ResponseEntity<?> createBono(@RequestBody BonoDTO nuevoBono) throws ModeloInvalidoException, InstrumentoDuplicadoException {

        log.info("Instrumento a crear: " + nuevoBono.toString());
        this.validarBonoDTO(nuevoBono);

        Bono nuevo = Bono
                .builder()
                .nombre(nuevoBono.getNombre())
                .precio(nuevoBono.getPrecio())
                .tasaInteres(nuevoBono.getTasaInteres())
                .build();

        this.instrumentoFinancieroService.createBono(nuevo);

        log.info("Instrumento creado: " + nuevo);
        return new ResponseEntity<>(nuevo, null, HttpStatus.CREATED);

    }


    //Read
    @RequestMapping("/accion")
    public ResponseEntity<?> listarAcciones() {

        List<Accion> listaDeAcciones = instrumentoFinancieroService.listarAcciones();

        log.info(listaDeAcciones.toString());
        return new ResponseEntity<>(listaDeAcciones, null, HttpStatus.OK);
    }

    @RequestMapping("/bono")
    public ResponseEntity<?> listarBonos() {

        List<Bono> listaDeBonos = instrumentoFinancieroService.listarBonos();

        log.info(listaDeBonos.toString());

        return new ResponseEntity<>(listaDeBonos, null, HttpStatus.OK);
    }

    @RequestMapping("/instrumentos")
    public ResponseEntity<?> listarTodos() {
        List<InstrumentoFinanciero> listaDeInstrumentos = instrumentoFinancieroService.getAll();

        return new ResponseEntity<>(listaDeInstrumentos, null, HttpStatus.OK);
    }


    //update
    @PutMapping("/accion/{id}")
    public ResponseEntity<?> updateAccion(@PathVariable Long id, @RequestBody AccionDTO accionDTO) throws InstrumentoNoEncontradoException, ModeloInvalidoException, InstrumentoDuplicadoException {

        this.validarAccionDTO(accionDTO);

        log.info("PathVariable id=  " + id);
        log.info(accionDTO.toString());

        Accion accionActualizada = Accion
                .builder()
                .id(id)
                .nombre(accionDTO.getNombre())
                .precio(accionDTO.getPrecio())
                .dividendo(accionDTO.getDividendo())
                .build();


        instrumentoFinancieroService.updateAccion(accionActualizada);

        log.info("Instrumento actualizado: " + accionActualizada.toString());
        return new ResponseEntity<>(accionActualizada, null, HttpStatus.OK);

    }

    @PutMapping("/bono/{id}")
    public ResponseEntity<?> updateBono(@PathVariable Long id, @RequestBody BonoDTO bonoDTO) throws InstrumentoNoEncontradoException, ModeloInvalidoException, InstrumentoDuplicadoException {

        log.info("PathVariable id=  " + id);
        log.info(bonoDTO.toString());
        this.validarBonoDTO(bonoDTO);

        Bono bonoActualizado = Bono
                .builder()
                .id(id)
                .nombre(bonoDTO.getNombre())
                .precio(bonoDTO.getPrecio())
                .tasaInteres(bonoDTO.getTasaInteres())
                .build();

        instrumentoFinancieroService.updateBono(bonoActualizado);
        log.info("Instrumento actualizado: " + bonoActualizado.toString());
        return new ResponseEntity<>(bonoActualizado, null, HttpStatus.OK);
    }


    //delete
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarInstrumento(@PathVariable long id) throws InstrumentoNoEncontradoException {

        log.info("PathVariable id=  " + id);
        instrumentoFinancieroService.delete(id);

        log.info("Instrumento eliminado: " + id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    private void validarAccionDTO(AccionDTO nuevaAccion) throws ModeloInvalidoException {
        if (nuevaAccion.getNombre().isBlank() || nuevaAccion.getNombre().isEmpty()) {

            log.error("Error: El nombre del instrumento no puede ser vacio");
            throw new ModeloInvalidoException("Error: El nombre del instrumento no puede ser vacio");
        }

        if (nuevaAccion.getNombre().matches("^[a-zA-Z0-9\\s]+$\n")) {

            log.error("Error: El nombre del instrumento no puede contener caracteres especiales");
            throw new ModeloInvalidoException("Error: El nombre del instrumento no puede contener caracteres especiales");
        }
        if (nuevaAccion.getPrecio() <= 0) {

            log.error("Error: El precio del instrumento debe ser mayor a 0");
            throw new ModeloInvalidoException("Error: El precio del instrumento debe ser mayor a 0");
        }

        if (nuevaAccion.getDividendo() <= 0) {

            log.error("Error: El dividendo del instrumento debe ser mayor a 0");
            throw new ModeloInvalidoException("Error: El dividendo del instrumento debe ser mayor a 0");
        }
    }

    private void validarBonoDTO(BonoDTO nuevoBono) throws ModeloInvalidoException {
        if (nuevoBono.getNombre().isBlank() || nuevoBono.getNombre().isEmpty()) {

            log.error("Error: El nombre del instrumento no puede ser vacio");
            throw new ModeloInvalidoException("Error: El nombre del instrumento no puede ser vacio");
        }

        if (nuevoBono.getNombre().matches("^[a-zA-Z0-9\\s]+$\n")) {

            log.error("Error: El nombre del instrumento no puede contener caracteres especiales");
            throw new ModeloInvalidoException("Error: El nombre del instrumento no puede contener caracteres especiales");
        }
        if (nuevoBono.getPrecio() <= 0) {

            log.error("Error: El precio del instrumento debe ser mayor a 0");
            throw new ModeloInvalidoException("Error: El precio del instrumento debe ser mayor a 0");
        }

        if (nuevoBono.getTasaInteres() <= 0) {

            log.error("Error: El dividendo del instrumento debe ser mayor a 0");
            throw new ModeloInvalidoException("Error: El dividendo del instrumento debe ser mayor a 0");
        }
    }


}
