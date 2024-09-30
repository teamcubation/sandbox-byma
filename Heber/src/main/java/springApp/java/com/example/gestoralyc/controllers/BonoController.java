package springApp.java.com.example.gestoralyc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springApp.java.com.example.gestoralyc.dto.InstrumentoDTO;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoDuplicadoException;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
import springApp.java.com.example.gestoralyc.mappers.InstrumentoMapper;
import springApp.java.com.example.gestoralyc.models.BonoModel;
import springApp.java.com.example.gestoralyc.services.impl.BonoServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/bonos") // Este es el path base del controller
@Slf4j
public class BonoController
{
    @Autowired
    BonoServiceImpl bonoService;

    @PostMapping("/")
    public ResponseEntity<InstrumentoDTO> agregarInstrumento(@RequestBody InstrumentoDTO instrumentoDTO) throws InstrumentoDuplicadoException, InvalidInstrumentoDataException {
        log.info("Instrumento recibido en formato cURL: \n{}", instrumentoDTO);
        InstrumentoDTO instrumentoCreadoDTO = InstrumentoMapper
                .mapToDTO(bonoService.agregarBono((BonoModel) InstrumentoMapper.mapToModel(instrumentoDTO)));
        log.info("Instrumento creado en formato cURL: \n{}", instrumentoCreadoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(instrumentoCreadoDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<BonoModel>> obtenerInstrumentos() {
        log.info("Obteniendo instrumentos");
        List<BonoModel> bonosList = bonoService.obtenerBonos();
        log.info("Instrumentos obtenidos: {}", bonosList);
        return ResponseEntity.ok(bonosList);
    }


}
