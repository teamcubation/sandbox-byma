package springApp.java.com.example.gestoralyc.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springApp.java.com.example.gestoralyc.dto.BonoDTO;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoDuplicadoException;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoNoEncontradoException;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
import springApp.java.com.example.gestoralyc.mappers.BonoMapper;
import springApp.java.com.example.gestoralyc.models.BonoModel;
import springApp.java.com.example.gestoralyc.services.BonoService;
import springApp.java.com.example.gestoralyc.utils.GeneradorCurl;

import java.util.List;

@RestController
@RequestMapping("/api/bonos")
@Slf4j
@AllArgsConstructor
public class BonoController {
    private final BonoService bonoService;

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
                .toList();

        log.info("Instrumentos obtenidos: {}", bonosList);
        return ResponseEntity.ok(bonosDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BonoDTO> obtenerBonoPorId(@PathVariable Long id) throws InstrumentoNoEncontradoException {
        log.info("Obteniendo instrumento con id: {}", id);
        BonoModel bono = bonoService.obtenerBonoPorId(id);
        BonoDTO bonoDTO = BonoMapper.mapToDTO(bono);
        log.info("Instrumento obtenido: {}", bonoDTO);
        return ResponseEntity.ok(bonoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBonoPorId(@PathVariable Long id) throws InstrumentoNoEncontradoException {
        log.info("Eliminando bono con id: {}", id);
        bonoService.eliminarBonoPorId(id);
        log.info("Bono eliminado con id: {}", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BonoDTO> editarBono(@PathVariable Long id, @RequestBody BonoDTO bonoDTO) throws InvalidInstrumentoDataException, InstrumentoNoEncontradoException, InstrumentoDuplicadoException {
        log.info("Editando bono con id: {}", id);
        BonoModel bonoModel = BonoMapper.mapToModel(bonoDTO);
        BonoModel bonoEditado = bonoService.editarBono(id, bonoModel);
        BonoDTO bonoEditadoDTO = BonoMapper.mapToDTO(bonoEditado);
        log.info("Bono editado: {}", bonoEditadoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bonoEditadoDTO);
    }


}
