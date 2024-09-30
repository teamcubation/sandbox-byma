package com.example.teamcubation.controller;

import com.example.teamcubation.exceptions.InstrumentoDuplicadoException;
import com.example.teamcubation.exceptions.InstrumentoNoEncontradoException;
import com.example.teamcubation.exceptions.ModeloInvalidoException;
import com.example.teamcubation.model.Accion;
import com.example.teamcubation.model.InstrumentoDTO.AccionDTO;
import com.example.teamcubation.service.AccionService;
import com.example.teamcubation.util.mappers.AccionMapper;
import com.example.teamcubation.util.validaciones.ValidatorAccionDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instrumentos-financieros/accion")
@Slf4j
@AllArgsConstructor
public class AccionController {

    private final AccionService accionService;


    //create
    @PostMapping("")
    public ResponseEntity<?> createAccion(@RequestBody AccionDTO nuevaAccion) throws ModeloInvalidoException, InstrumentoDuplicadoException {
        log.info("Instrumento a crear: " + nuevaAccion.toString());

        ValidatorAccionDTO.validarAccionDTO(nuevaAccion);

        Accion accionCreada = this.accionService.createAccion(AccionMapper.toAccion(nuevaAccion));

        log.info("Instrumento creado: " + accionCreada);


        return ResponseEntity.status(HttpStatus.CREATED).body(accionCreada);

    }


    //Read
    @RequestMapping("")
    public ResponseEntity<?> listarAcciones() {

        List<Accion> listaDeAcciones = accionService.getAllAcciones();

        List<AccionDTO> res = listaDeAcciones
                .stream()
                .map(AccionMapper::toAccionDTO)
                .toList();

        log.info(res.toString());
        return new ResponseEntity<>(res, null, HttpStatus.OK);
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccion(@PathVariable Long id, @RequestBody AccionDTO accionDTO) throws InstrumentoNoEncontradoException, ModeloInvalidoException, InstrumentoDuplicadoException {


        ValidatorAccionDTO.validarAccionDTO(accionDTO);

        log.info("PathVariable id=  " + id);
        log.info(accionDTO.toString());

        Accion accionPorActualizar = AccionMapper.toAccion(accionDTO);
        accionPorActualizar.setId(id);


        Accion accionActualizada = this.accionService.updateAccion(accionPorActualizar);

        log.info("Instrumento actualizado: " + accionActualizada.toString());
        return new ResponseEntity<>(accionActualizada, null, HttpStatus.OK);

    }


    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccion(@PathVariable long id) throws InstrumentoNoEncontradoException {

        log.info("PathVariable id=  " + id);
        this.accionService.deleteAccion(id);

        log.info("Instrumento eliminado: " + id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
