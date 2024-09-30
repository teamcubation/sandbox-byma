package springApp.java.com.example.gestoralyc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springApp.java.com.example.gestoralyc.dto.BonoDTO;
import springApp.java.com.example.gestoralyc.dto.InstrumentoDTO;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoDuplicadoException;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
import springApp.java.com.example.gestoralyc.mappers.BonoMapper;
import springApp.java.com.example.gestoralyc.models.BonoModel;
import springApp.java.com.example.gestoralyc.services.impl.BonoServiceImpl;
import springApp.java.com.example.gestoralyc.utils.GeneradorCurl;

import java.util.List;

@RestController
@RequestMapping("/api/bonos") // Este es el path base del controller
@Slf4j
public class BonoController
{
    @Autowired
    BonoServiceImpl bonoService;

    @PostMapping("/")
    public ResponseEntity<BonoDTO> agregarBono(@RequestBody BonoDTO bonoDTO) throws InstrumentoDuplicadoException, InvalidInstrumentoDataException {
        log.info("Bono recibido en formato cURL: \n{}", GeneradorCurl.generarCurlBono(bonoDTO));
        BonoDTO bonoCreadoDTO = BonoMapper
                .mapToDTO(bonoService.agregarBono((BonoModel) BonoMapper.mapToModel(bonoDTO)));
        log.info("Bono creado en formato cURL: \n{}", GeneradorCurl.generarCurlBono(bonoCreadoDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(bonoCreadoDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<BonoDTO>> obtenerBonos() {
        log.info("Obteniendo instrumentos");
        List<BonoModel> bonosList = bonoService.obtenerBonos();
        List<BonoDTO> bonosDTOList = bonosList.stream()
                .map(BonoMapper::mapToDTO)
                .toList();  // Convertir el Stream a una lista

        log.info("Instrumentos obtenidos: {}", bonosList);
        return ResponseEntity.ok(bonosDTOList);
    }


}
