package springApp.java.com.example.gestoralyc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springApp.java.com.example.gestoralyc.dto.InstrumentoDTO;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoDuplicadoException;
import springApp.java.com.example.gestoralyc.exceptions.InstrumentoNoEncontradoException;
import springApp.java.com.example.gestoralyc.exceptions.InvalidInstrumentoDataException;
import springApp.java.com.example.gestoralyc.mappers.InstrumentoMapper;
import springApp.java.com.example.gestoralyc.models.InstrumentoFinancieroModel;
import springApp.java.com.example.gestoralyc.services.InstrumentoService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/instrumentos") // Este es el path base del controller
@Slf4j
public class InstrumentoController {

    @Autowired
    InstrumentoService instrumentoService;

    @PostMapping("/")
    public ResponseEntity<InstrumentoDTO> agregarInstrumento(@RequestBody InstrumentoDTO instrumentoDTO) throws InstrumentoDuplicadoException, InvalidInstrumentoDataException {
        log.info("Instrumento recibido en formato cURL: \n{}", generarCurlComando(instrumentoDTO));

        InstrumentoFinancieroModel instrumento = InstrumentoMapper.mapToModel(instrumentoDTO);
        InstrumentoDTO instrumentoCreadoDTO = InstrumentoMapper.mapToDTO(instrumentoService.agregarInstrumento(instrumento));

        log.info("Instrumento creado en formato cURL: \n{}", generarCurlComando(instrumentoCreadoDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(instrumentoCreadoDTO);
    }

    private String generarCurlComando(InstrumentoDTO instrumentoDTO) {
        String tipo = instrumentoDTO.getTipo().toString();
        String nombre = instrumentoDTO.getNombre();
        double precio = instrumentoDTO.getPrecio();
        Double tasaInteres = instrumentoDTO.getTasaInteres();
        Double dividendo = instrumentoDTO.getDividendo();

        StringBuilder curlCommand = new StringBuilder();
        curlCommand.append("curl --location 'http://localhost:5000/api/instrumentos/' \\").append("\n");
        curlCommand.append("--header 'Content-Type: application/json' \\").append("\n");
        curlCommand.append("--data '{").append("\n");
        curlCommand.append("    \"tipo\": \"").append(tipo).append("\",").append("\n");
        curlCommand.append("    \"nombre\": \"").append(nombre).append("\",").append("\n");
        curlCommand.append("    \"precio\": ").append(precio).append(",").append("\n");

        // Añadir sólo los atributos que correspondan
        if (tasaInteres != null) {
            curlCommand.append("    \"tasaInteres\": ").append(tasaInteres).append("\n");
        }
        if (dividendo != null) {
            curlCommand.append("    \"dividendo\": ").append(dividendo).append("\n");
        }

        curlCommand.append("}'");
        return curlCommand.toString();
    }


    @GetMapping("/")
    public ResponseEntity<List<InstrumentoDTO>> obtenerInstrumentos() {
        log.info("Obteniendo instrumentos");
        List<InstrumentoFinancieroModel> instrumentos = instrumentoService.obtenerInstrumentos();
        List<InstrumentoDTO> instrumentosDTO = instrumentos.stream()
                .map(InstrumentoMapper::mapToDTO)
                .collect(Collectors.toList());
        log.info("Instrumentos obtenidos: {}", instrumentosDTO);
        return new ResponseEntity<>(instrumentosDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstrumentoDTO> obtenerInstrumento(@PathVariable("id") Long id) throws InvalidInstrumentoDataException {
        InstrumentoFinancieroModel instrumentoModel = instrumentoService.obtenerInstrumento(id);
        InstrumentoDTO instrumentoDTO = InstrumentoMapper.mapToDTO(instrumentoModel);
        return new ResponseEntity<>(instrumentoDTO, HttpStatus.OK);
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
    }
}
