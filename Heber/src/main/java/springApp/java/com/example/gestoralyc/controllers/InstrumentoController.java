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
import springApp.java.com.example.gestoralyc.models.InstrumentoFinancieroModel;
import springApp.java.com.example.gestoralyc.services.InstrumentoService;
import springApp.java.com.example.gestoralyc.utils.GeneradorCurl;
import java.util.List;

@RestController
@RequestMapping("/api/instrumentos") // Este es el path base del controller
@Slf4j
public class InstrumentoController {

    @Autowired
    InstrumentoService instrumentoService;

    @PostMapping("/")
    public ResponseEntity<InstrumentoDTO> agregarInstrumento(@RequestBody InstrumentoDTO instrumentoDTO) throws InstrumentoDuplicadoException, InvalidInstrumentoDataException {
        log.info("Instrumento recibido en formato cURL: \n{}", GeneradorCurl.generarCurlComando(instrumentoDTO));
        InstrumentoDTO instrumentoCreadoDTO = InstrumentoMapper
                .mapToDTO(instrumentoService.agregarInstrumento(InstrumentoMapper.mapToModel(instrumentoDTO)));
        log.info("Instrumento creado en formato cURL: \n{}", GeneradorCurl.generarCurlComando(instrumentoCreadoDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(instrumentoCreadoDTO);
    }


    @GetMapping("/")
    public ResponseEntity<List<InstrumentoFinancieroModel>> obtenerInstrumentos() {
        log.info("Obteniendo instrumentos");
        List<InstrumentoFinancieroModel> instrumentosFinancieroList = instrumentoService.obtenerInstrumentos();
        log.info("Instrumentos obtenidos: {}", instrumentosFinancieroList);
        return ResponseEntity.ok(instrumentosFinancieroList);
    }

   /* @GetMapping("/{id}")
    public ResponseEntity<InstrumentoDTO> obtenerInstrumento(@PathVariable("id") Long id) throws InvalidInstrumentoDataException {
        InstrumentoFinancieroModel instrumentoModel = instrumentoService.obtenerInstrumento(id);
        InstrumentoDTO instrumentoDTO = InstrumentoMapper.mapToDTO(instrumentoModel);
        return ResponseEntity.ok(instrumentoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarInstrumentoPorId(@PathVariable("id") Long id) throws InstrumentoNoEncontradoException {
        instrumentoService.eliminarInstrumentoPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstrumentoDTO> editarInstrumento(@PathVariable("id") Long id, @RequestBody InstrumentoDTO instrumento) throws InvalidInstrumentoDataException {
        InstrumentoFinancieroModel instrumentoFinancieroModel = InstrumentoMapper.mapToModel(instrumento);
        InstrumentoFinancieroModel instrumentoEditado = instrumentoService.editarInstrumento(id, instrumentoFinancieroModel);
        InstrumentoDTO instrumentoNuevoADto = InstrumentoMapper.mapToDTO(instrumentoEditado);
        return ResponseEntity.status(HttpStatus.CREATED).body(instrumentoNuevoADto);
    }*/
}
