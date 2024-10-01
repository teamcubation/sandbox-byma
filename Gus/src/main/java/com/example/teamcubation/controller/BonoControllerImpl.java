package com.example.teamcubation.controller;

import com.example.teamcubation.exceptions.InstrumentoDuplicadoException;
import com.example.teamcubation.exceptions.InstrumentoNoEncontradoException;
import com.example.teamcubation.exceptions.ModeloInvalidoException;
import com.example.teamcubation.model.Bono;
import com.example.teamcubation.model.InstrumentoDTO.BonoDTO;
import com.example.teamcubation.service.BonoService;
import com.example.teamcubation.util.mappers.BonoMapper;
import com.example.teamcubation.util.validaciones.ValidatorBonoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bono")
@Slf4j
public class BonoControllerImpl implements BonoController {

    private final BonoService bonoService;

    public BonoControllerImpl(BonoService bonoService) {
        this.bonoService = bonoService;
    }

    @PostMapping("")
    @Override
    public ResponseEntity<?> createBono(@RequestBody BonoDTO nuevoBono) throws ModeloInvalidoException, InstrumentoDuplicadoException {

        log.info("Instrumento a crear: " + nuevoBono.toString());

        ValidatorBonoDTO.validarBonoDTO(nuevoBono);

        Bono nuevo = BonoMapper.toBono(nuevoBono);

        Bono bonoCreado = this.bonoService.createBono(nuevo);

        log.info("Instrumento creado: " + bonoCreado);
        return ResponseEntity.status(HttpStatus.CREATED).body(bonoCreado);

    }


    @GetMapping("")
    @Override
    public ResponseEntity<?> listarBonos() {

        List<Bono> listaDeBonos = bonoService.getAllBonos();

        List<BonoDTO> res = listaDeBonos
                .stream()
                .map(BonoMapper::toBonoDTO)
                .toList();

        log.info(res.toString());

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    @PutMapping("/{id}")
    @Override
    public ResponseEntity<?> updateBono(@PathVariable Long id, @RequestBody BonoDTO bonoDTO) throws InstrumentoNoEncontradoException, ModeloInvalidoException, InstrumentoDuplicadoException {

        log.info("PathVariable id=  " + id);
        log.info(bonoDTO.toString());
        ValidatorBonoDTO.validarBonoDTO(bonoDTO);

        Bono bonoPorActualizar = BonoMapper.toBono(bonoDTO);
        bonoPorActualizar.setId(id);
        Bono bonoActualizado = this.bonoService.updateBono(bonoPorActualizar);
        log.info("Instrumento actualizado: " + bonoActualizado.toString());
        return ResponseEntity.status(HttpStatus.OK).body(bonoActualizado);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> deleteBono(@PathVariable long id) throws InstrumentoNoEncontradoException {

        log.info("PathVariable id=  " + id);
        this.bonoService.deleteBono(id);

        log.info("Instrumento eliminado: " + id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
