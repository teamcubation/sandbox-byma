package springbootApp.java.controllers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import springbootApp.java.DTOs.InstrumentoDTO;
import springbootApp.java.services.InstrumentoFinancieroService;

@Slf4j
@RestController
@RequestMapping("/instrumentoFinanciero")
public class InstrumentoFinancieroController {

    private static final Logger log = LoggerFactory.getLogger(InstrumentoFinancieroController.class);
    @Autowired
    private InstrumentoFinancieroService instrumentoFinancieroService;


    @PostMapping("/")
    public ResponseEntity<?> registrarInstrumentoFinanciero(@RequestBody InstrumentoDTO instrumentoFinanciero) {
        try {
            log.info("registrando instrumento...");
            instrumentoFinancieroService.registrarInstrumentoFinanciero(instrumentoFinanciero.getNombre(),
                    instrumentoFinanciero.getPrecio(), instrumentoFinanciero.getTipo());
            log.info("nuevo instrumento creado, de tipo: " + instrumentoFinanciero.getTipo() + " con nombre: " + instrumentoFinanciero.getNombre() + " y precio: " + instrumentoFinanciero.getPrecio());
            return new ResponseEntity<InstrumentoDTO>(instrumentoFinanciero, HttpStatus.CREATED);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> obtenerTodosLosInstrumentos() {
        try {
            log.info("obteniendo todos los instrumentos");
            return ResponseEntity.ok(instrumentoFinancieroService.consultarTodosLosInstrumentos());
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<?> obtenerInstrumentoPorNombre(@PathVariable String nombre) {
        try {
            log.info("obteniendo instrumento por nombre...");
            return ResponseEntity.ok(instrumentoFinancieroService.buscarInstrumento(nombre));
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<?> eliminarInstrumento(@PathVariable String nombre) {
        try {
            log.info("eliminando instrumento: " + nombre);
            instrumentoFinancieroService.eliminarInstrumento(nombre);
            return ResponseEntity.ok("Instrumento eliminado");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{nombre}")
    public ResponseEntity<?> actualizarInstrumento(@PathVariable String nombre, @RequestBody InstrumentoDTO instrumento) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {
        try {
            log.info("actualizando instrumento...");
            instrumentoFinancieroService.actualizarInstrumento(nombre, instrumento);
            return ResponseEntity.ok(instrumento);
        } catch (InstrumentoNoEncontradoException e) {
            log.info("Error al actualizar instrumento: " + e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (InstrumentoDuplicadoException e) {
            log.info("Error al actualizar instrumento: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.info("Error al actualizar instrumento: "+ e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}