package springApp.java.com.example.gestoralyc.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springApp.java.com.example.gestoralyc.dto.AccionDTO;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoDuplicadoException;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
import springApp.java.com.example.gestoralyc.mappers.AccionMapper;
import springApp.java.com.example.gestoralyc.models.AccionModel;
import springApp.java.com.example.gestoralyc.services.AccionService;
import springApp.java.com.example.gestoralyc.utils.GeneradorCurl;

import java.util.List;

@RestController
@RequestMapping("/api/acciones") // Este es el path base del controller
@Slf4j
@AllArgsConstructor
public class AccionController {

    private final AccionService accionService;

    @PostMapping("/")

    public ResponseEntity<AccionDTO> agregarAccion(@RequestBody AccionDTO accionDTO) throws InstrumentoDuplicadoException, InvalidInstrumentoDataException {
        log.info("Instrumento recibido en formato cURL: \n{}", GeneradorCurl.generarCurlAccion(accionDTO));
        AccionDTO accionCreadaDTO = AccionMapper
                .mapToDTO(accionService.agregarAccion((AccionModel) AccionMapper.mapToModel(accionDTO)));
        log.info("Instrumento creado en formato cURL: \n{}", GeneradorCurl.generarCurlAccion(accionCreadaDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(accionCreadaDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<AccionDTO>> obtenerInstrumentos() {
        log.info("Obteniendo instrumentos");
        List<AccionModel> accionesList = accionService.obtenerAcciones();

        List<AccionDTO> accionesDTOList = accionesList.stream()
                .map(AccionMapper::mapToDTO)
                .toList();  // Convertir el Stream a una lista
        log.info("Instrumentos obtenidos: {}", accionesDTOList);
        return ResponseEntity.ok(accionesDTOList);
    }





}
