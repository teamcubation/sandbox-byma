package springbootMigracion.java.com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootMigracion.java.com.example.demo.dto.InstrumentoDTO;
import springbootMigracion.java.com.example.demo.exception.InstrumentoDuplicadoException;
import springbootMigracion.java.com.example.demo.exception.InstrumentoNoEncontradoException;
import springbootMigracion.java.com.example.demo.factory.InstrumentoFinancieroFactory;
import springbootMigracion.java.com.example.demo.model.InstrumentoFinanciero;
import springbootMigracion.java.com.example.demo.service.IInstrumentoFinancieroService;

import java.util.List;

@RestController
@RequestMapping("/api/instrumentos")
public class InstrumentoFinancieroController {
    @Autowired
    private IInstrumentoFinancieroService instrumentoFinancieroService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarInstrumento(@RequestBody InstrumentoDTO instrumentoDTO) {
        try {
            InstrumentoFinanciero instrumento = InstrumentoFinancieroFactory.crearInstrumento(instrumentoDTO);
            instrumentoFinancieroService.registrarInstrumento(instrumento);
            return ResponseEntity.status(HttpStatus.CREATED).body("Instrumento registrado correctamente.");
        } catch (InstrumentoDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<InstrumentoFinanciero>> listarInstrumentos() {
        return ResponseEntity.ok(instrumentoFinancieroService.listarTodosLosInstrumentos());
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<?> buscarInstrumento(@PathVariable String nombre) {
        try {
            InstrumentoFinanciero instrumento = instrumentoFinancieroService.buscarInstrumentoPorNombre(nombre)
                    .orElseThrow(() -> new InstrumentoNoEncontradoException("Instrumento no encontrado"));
            return ResponseEntity.ok(instrumento);
        } catch (InstrumentoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<String> eliminarInstrumento(@PathVariable String nombre) {
        try {
            instrumentoFinancieroService.eliminarInstrumento(nombre);
            return ResponseEntity.ok("Instrumento eliminado.");
        } catch (InstrumentoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{nombre}")
    public ResponseEntity<String> editarInstrumento(@PathVariable String nombre, @RequestBody InstrumentoDTO instrumentoDTO) {
        try {
            InstrumentoFinanciero nuevoInstrumento = InstrumentoFinancieroFactory.crearInstrumento(instrumentoDTO);
            instrumentoFinancieroService.editarInstrumento(nombre, nuevoInstrumento);
            return ResponseEntity.ok("Instrumento editado correctamente.");
        } catch (InstrumentoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
