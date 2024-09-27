package com.example.teamcubation.controller;


import com.example.teamcubation.exceptions.InstrumentoNoEncontradoException;
import com.example.teamcubation.exceptions.ModeloInvalidoException;
import com.example.teamcubation.model.Accion;
import com.example.teamcubation.model.Bono;
import com.example.teamcubation.model.InstrumentoDTO.CreateAccionDTO;
import com.example.teamcubation.model.InstrumentoDTO.CreateBonoDTO;
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
    public ResponseEntity<?> createAccion(@RequestBody CreateAccionDTO nuevaAccion) throws ModeloInvalidoException {
        log.info("Instrumento a crear: " + nuevaAccion.toString());

        Accion nuevo = Accion
                .builder()
                .nombre(nuevaAccion.getNombre())
                .precio(nuevaAccion.getPrecio())
                .dividendo(nuevaAccion.getDividendo())
                .build();
        instrumentoFinancieroService.createAccion(nuevo);

        log.info("Instrumento creado: " + nuevo.toString());

        return new ResponseEntity<>(nuevo, null, HttpStatus.CREATED);

    }

    @PostMapping("/bono")
    public ResponseEntity<?> createBono(@RequestBody CreateBonoDTO nuevoBono) throws ModeloInvalidoException {

        log.info("Instrumento a crear: " + nuevoBono.toString());

        Bono nuevo = Bono
                .builder()
                .nombre(nuevoBono.getNombre())
                .precio(nuevoBono.getPrecio())
                .tasaInteres(nuevoBono.getTasaInteres())
                .build();


        this.instrumentoFinancieroService.createBono(nuevo);

        log.info("Instrumento creado: " + nuevo.toString());
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
    public ResponseEntity<?> updateAccion(@PathVariable Long id, @RequestBody CreateAccionDTO AccionDTO) throws ModeloInvalidoException, InstrumentoNoEncontradoException {

        log.info("PathVariable id=  " + id);
        log.info(AccionDTO.toString());

        Accion accionActualizada = Accion
                .builder()
                .id(id)
                .nombre(AccionDTO.getNombre())
                .precio(AccionDTO.getPrecio())
                .dividendo(AccionDTO.getDividendo())
                .build();


        instrumentoFinancieroService.update(accionActualizada);

        log.info("Instrumento actualizado: " + accionActualizada.toString());
        return new ResponseEntity<>(accionActualizada, null, HttpStatus.OK);

    }

    @PutMapping("/bono/{id}")
    public ResponseEntity<?> updateBono(@PathVariable Long id, @RequestBody CreateBonoDTO BonoDTO) {

        log.info("PathVariable id=  " + id);
        log.info(BonoDTO.toString());

        Bono bonoActualizado = Bono
                .builder()
                .id(id)
                .nombre(BonoDTO.getNombre())
                .precio(BonoDTO.getPrecio())
                .tasaInteres(BonoDTO.getTasaInteres())
                .build();

        instrumentoFinancieroService.update(bonoActualizado);
        log.info("Instrumento actualizado: " + bonoActualizado.toString());
        return new ResponseEntity<>(bonoActualizado, null, HttpStatus.OK);
    }


    //delete
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarInstrumento(@PathVariable long id) {

        log.info("PathVariable id=  " + id);
        instrumentoFinancieroService.delete(id);

        log.info("Instrumento eliminado: " + id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


}
