package springApp.java.com.example.gestoralyc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springApp.java.com.example.gestoralyc.dto.InstrumentoDTO;
import springApp.java.com.example.gestoralyc.mappers.InstrumentoMapper;
import springApp.java.com.example.gestoralyc.models.InstrumentoFinancieroModel;
import springApp.java.com.example.gestoralyc.services.InstrumentoService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/instrumentos") // Este es el path base del controller
public class InstrumentoController {

    @Autowired
    InstrumentoService instrumentoService;

    @PostMapping("/")
    public ResponseEntity<InstrumentoDTO> agregarInstrumento(@RequestBody InstrumentoDTO instrumentoDTO) {
        InstrumentoFinancieroModel instrumento = InstrumentoMapper.mapToModel(instrumentoDTO);
        InstrumentoDTO instrumentoCreadoDTO = InstrumentoMapper.mapToDTO(instrumentoService.agregarInstrumento(instrumento));
        return ResponseEntity.status(HttpStatus.CREATED).body(instrumentoCreadoDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<InstrumentoDTO>> obtenerInstrumentos() {
        List<InstrumentoFinancieroModel> instrumentos = instrumentoService.obtenerInstrumentos();

        List<InstrumentoDTO> instrumentosDTO = instrumentos.stream()
                .map(InstrumentoMapper::mapToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(instrumentosDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstrumentoDTO> obtenerInstrumento(@PathVariable("id") Long id) {
        InstrumentoFinancieroModel instrumentoModel = instrumentoService.obtenerInstrumento(id);
        InstrumentoDTO instrumentoDTO = InstrumentoMapper.mapToDTO(instrumentoModel);
        return new ResponseEntity<>(instrumentoDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarInstrumentoPorId(@PathVariable("id") Long id) {
        instrumentoService.eliminarInstrumentoPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstrumentoDTO> editarInstrumento(@PathVariable("id") Long id, @RequestBody InstrumentoDTO instrumento) {
        InstrumentoFinancieroModel instrumentoFinancieroModel = InstrumentoMapper.mapToModel(instrumento);
        InstrumentoFinancieroModel instrumentoEditado = instrumentoService.editarInstrumento(id, instrumentoFinancieroModel);
        InstrumentoDTO instrumentoNuevoADto = InstrumentoMapper.mapToDTO(instrumentoEditado);
        return ResponseEntity.status(HttpStatus.CREATED).body(instrumentoNuevoADto);
    }
}
