package springbootproject.java.com.example.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootproject.java.com.example.project.controller.dto.EditarInstrumentoDTO;
import springbootproject.java.com.example.project.controller.dto.InstrumentoFinancieroDTO;
import springbootproject.java.com.example.project.exceptions.InstrumentoDuplicadoException;
import springbootproject.java.com.example.project.exceptions.InstrumentoNoEncontradoException;
import springbootproject.java.com.example.project.exceptions.NoExisteEseTipoDeInstrumentoException;
import springbootproject.java.com.example.project.service.InstrumentoFinancieroService;

@Slf4j
@RestController
@RequestMapping("/instrumentos-financieros")
public class InstrumentoFinancieroController {
    private final InstrumentoFinancieroService instrumentoFinancieroService;

    @Autowired
    public InstrumentoFinancieroController(InstrumentoFinancieroService instrumentoFinancieroService) {
        this.instrumentoFinancieroService = instrumentoFinancieroService;
    }

    @RequestMapping("/home")
    public String home() {
        return "Hello World";
    }

    @RequestMapping("/")
    public ResponseEntity<?> consultarTodosLosInstrumentosConocidos() {
        log.info("Consultando instrumentos financieros");
        return ResponseEntity.ok(instrumentoFinancieroService.consultarInstrumentosFinancieros());
    }

    @RequestMapping("/{nombre}")
    public ResponseEntity<?> consultarUnInstrumentoFinanciero(@PathVariable("nombre") String nombre) {
        try {
            log.info("Consultando instrumento financiero de nombre {}", nombre);
            return ResponseEntity.ok(this.instrumentoFinancieroService.consultarPorUnInstrumentoFinanciero(nombre));
        } catch (InstrumentoNoEncontradoException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> crearInstrumento(@RequestBody InstrumentoFinancieroDTO instrumentoFinancieroDTO) {
        try {
            log.info("Creando instrumento financiero ",instrumentoFinancieroDTO);
            return ResponseEntity.ok(this.instrumentoFinancieroService.registrarInstrumentoFinanciero(instrumentoFinancieroDTO));
        } catch (NoExisteEseTipoDeInstrumentoException | InstrumentoNoEncontradoException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (InstrumentoDuplicadoException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<?> deleteInstrumento(@PathVariable("nombre") String nombre) {
        try {
            this.instrumentoFinancieroService.eliminarInstrumentoFinanciero(nombre);
            log.info("Eliminando instrumento financiero " + nombre);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (InstrumentoNoEncontradoException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{nombre}")
    public ResponseEntity<?> editarInstrumento(@RequestBody EditarInstrumentoDTO editarInstrumentoDTO, @PathVariable("nombre") String nombre) {
        try {
            log.info("Editando instrumento financiero " + nombre);
            return ResponseEntity.ok(this.instrumentoFinancieroService.editarInstrumentoFinanciero(nombre, editarInstrumentoDTO));
        } catch (InstrumentoNoEncontradoException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
