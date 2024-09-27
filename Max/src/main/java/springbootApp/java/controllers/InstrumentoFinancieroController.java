package springbootApp.java.controllers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootApp.java.controllers.mappers.InstrumentoMapper;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import springbootApp.java.DTOs.InstrumentoDTO;
import springbootApp.java.models.InstrumentoFinanciero;
import springbootApp.java.services.InstrumentoFinancieroService;

@Slf4j
@RestController
@RequestMapping("/instrumento-Financiero")
public class InstrumentoFinancieroController {

    private static final Logger log = LoggerFactory.getLogger(InstrumentoFinancieroController.class);
    @Autowired
    private InstrumentoFinancieroService instrumentoFinancieroService;


    @PostMapping("/")
    public ResponseEntity<?> registrar(@RequestBody InstrumentoDTO instrumentoDTO) throws Exception{
            log.info("registrando instrumento...");
            InstrumentoFinanciero instrumento = InstrumentoMapper.instrumentoDTOToInstrumento(instrumentoDTO);
            instrumentoFinancieroService.registrarInstrumentoFinanciero(instrumento);
            log.info("nuevo instrumento creado, de tipo: " + instrumentoDTO.getTipo() + " con nombre: " + instrumentoDTO.getNombre() + " y precio: " + instrumentoDTO.getPrecio());
            return new ResponseEntity<InstrumentoDTO>(instrumentoDTO, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> obtenerTodos() throws Exception{
            log.info("obteniendo todos los instrumentos");
            return ResponseEntity.ok(instrumentoFinancieroService.consultarTodosLosInstrumentos());
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<?> obtenerPorNombre(@PathVariable String nombre) throws Exception{
            log.info("obteniendo instrumento por nombre...");
            return ResponseEntity.ok(instrumentoFinancieroService.buscarInstrumento(nombre));
        }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<?> eliminar(@PathVariable String nombre) throws Exception{
            log.info("eliminando instrumento: " + nombre);
            instrumentoFinancieroService.eliminarInstrumento(nombre);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{nombre}")
    public ResponseEntity<?> actualizar(@PathVariable String nombre, @RequestBody InstrumentoDTO instrumentoDTO) throws Exception {
            log.info("actualizando instrumento...");
            InstrumentoFinanciero instrumento = InstrumentoMapper.instrumentoDTOToInstrumento(instrumentoDTO);
            return ResponseEntity.ok(instrumentoFinancieroService.actualizarInstrumento(nombre, instrumento));
    }
}