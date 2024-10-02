package springApp.java.com.example.gestoralyc.controllers.impl;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springApp.java.com.example.gestoralyc.dto.AccionDTO;
import springApp.java.com.example.gestoralyc.mappers.AccionMapper;
import springApp.java.com.example.gestoralyc.models.AccionModel;
import springApp.java.com.example.gestoralyc.services.AccionService;
import springApp.java.com.example.gestoralyc.utils.GeneradorCurl;

import java.util.List;

@RestController
@RequestMapping("/api/acciones")
@Slf4j
@AllArgsConstructor
public class AccionController {

    private final AccionService accionService;


    @PostMapping("/")
    public ResponseEntity<AccionDTO> agregarAccion(@RequestBody AccionDTO accionDTO) {
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

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<AccionDTO> obtenerInstrumentoPorId(@PathVariable Long id) {
        log.info("Obteniendo instrumento con id: {}", id);
        AccionModel accion = accionService.obtenerAccionPorId(id);
        AccionDTO accionDTO = AccionMapper.mapToDTO(accion);
        log.info("Instrumento obtenido: {}", accionDTO);
        return ResponseEntity.ok(accionDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInstrumentoPorId(@PathVariable Long id) {
        log.info("Eliminando instrumento con id: {}", id);
        accionService.eliminarAccionPorId(id);
        log.info("Instrumento eliminado con id: {}", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccionDTO> editarInstrumento(@PathVariable Long id, @RequestBody AccionDTO accionDTO) {
        log.info("Editando instrumento con id: {}", id);
        AccionModel accionModel = AccionMapper.mapToModel(accionDTO);
        AccionModel accionEditada = accionService.editarAccion(id, accionModel);
        AccionDTO accionEditadaDTO = AccionMapper.mapToDTO(accionEditada);
        log.info("Instrumento editado: {}", accionEditadaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(accionEditadaDTO);
    }


}
