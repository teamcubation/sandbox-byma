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
import springApp.java.com.example.gestoralyc.models.AccionModel;
import springApp.java.com.example.gestoralyc.services.impl.AccionServiceImpl;
import springApp.java.com.example.gestoralyc.utils.GeneradorCurl;

import java.util.List;

@RestController
@RequestMapping("/api/acciones") // Este es el path base del controller
@Slf4j
public class AccionController {
    @Autowired
    AccionServiceImpl accionService;

    @PostMapping("/")

    public ResponseEntity<InstrumentoDTO> agregarInstrumento(@RequestBody InstrumentoDTO instrumentoDTO) throws InstrumentoDuplicadoException, InvalidInstrumentoDataException {
        log.info("Instrumento recibido en formato cURL: \n{}", GeneradorCurl.generarCurlComando(instrumentoDTO));
        InstrumentoDTO instrumentoCreadoDTO = InstrumentoMapper
                .mapToDTO(accionService.agregarAccion((AccionModel) InstrumentoMapper.mapToModel(instrumentoDTO)));
        log.info("Instrumento creado en formato cURL: \n{}", GeneradorCurl.generarCurlComando(instrumentoCreadoDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(instrumentoCreadoDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<AccionModel>> obtenerInstrumentos() {
        log.info("Obteniendo instrumentos");
        List<AccionModel> accionesList = accionService.obtenerAcciones();
        log.info("Instrumentos obtenidos: {}", accionesList);
        return ResponseEntity.ok(accionesList);
    }




}
