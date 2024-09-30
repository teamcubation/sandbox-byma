package  springbootApp.app.controllers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  springbootApp.app.controllers.mappers.InstrumentoMapper;
import  springbootApp.app.DTOs.InstrumentoDTO;
import  springbootApp.app.models.InstrumentoFinanciero;
import  springbootApp.app.services.InstrumentoFinancieroService;

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

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> obtenerPorNombre(@PathVariable String nombre) throws Exception{
            log.info("obteniendo instrumento por nombre...");
            return ResponseEntity.ok(instrumentoFinancieroService.buscarInstrumentoPorNombre(nombre));
        }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorID(@PathVariable Long id) throws Exception{
        log.info("obteniendo instrumento por ID...");
        return ResponseEntity.ok(instrumentoFinancieroService.buscarInstrumentoPorID(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) throws Exception{
            log.info("eliminando instrumento: " + id);
            instrumentoFinancieroService.eliminarInstrumento(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody InstrumentoDTO instrumentoDTO) throws Exception {
            log.info("actualizando instrumento...");
            InstrumentoFinanciero instrumento = InstrumentoMapper.instrumentoDTOToInstrumento(instrumentoDTO);
            return ResponseEntity.ok(instrumentoFinancieroService.actualizarInstrumento(id, instrumento));
    }
}