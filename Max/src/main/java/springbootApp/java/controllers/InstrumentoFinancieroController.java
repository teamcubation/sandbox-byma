package springbootApp.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import springbootApp.java.models.InstrumentoDTO;
import springbootApp.java.models.InstrumentoFinanciero;
import springbootApp.java.services.InstrumentoFinancieroService;

import java.util.List;

@RestController
@RequestMapping("/instrumentoFinanciero")
public class InstrumentoFinancieroController {

    @Autowired
    private InstrumentoFinancieroService instrumentoFinancieroService;


    @PostMapping("/")
    public ResponseEntity<InstrumentoDTO> registrarInstrumentoFinanciero(@RequestBody InstrumentoDTO instrumentoFinanciero) {
        try {
            System.out.println("registrando instrumento...");
            instrumentoFinancieroService.registrarInstrumentoFinanciero(instrumentoFinanciero.getNombre(),
                    instrumentoFinanciero.getPrecio(), instrumentoFinanciero.getTipo());
            System.out.println("nuevo instrumento creado, de tipo: " + instrumentoFinanciero.getTipo() + " con nombre: " + instrumentoFinanciero.getNombre() + " y precio: " + instrumentoFinanciero.getPrecio());
            return new ResponseEntity<InstrumentoDTO>(instrumentoFinanciero, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<InstrumentoFinanciero>> obtenerTodosLosInstrumentos() {
        try {
            System.out.println("obteniendo todos los instrumentos");
            return ResponseEntity.ok(instrumentoFinancieroService.consultarTodosLosInstrumentos());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<InstrumentoFinanciero> obtenerInstrumentoPorNombre(@PathVariable String nombre) {
        try {
            System.out.println("obteniendo instrumento por nombre...");
            return ResponseEntity.ok(instrumentoFinancieroService.buscarInstrumento(nombre));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<String> eliminarInstrumento(@PathVariable String nombre) {
        try {
            System.out.println("eliminando instrumento: " + nombre);
            instrumentoFinancieroService.eliminarInstrumento(nombre);
            return ResponseEntity.ok("Instrumento eliminado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{nombre}")
    public ResponseEntity<InstrumentoDTO> actualizarInstrumento(@PathVariable String nombre, @RequestBody InstrumentoDTO instrumento) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {
        try {
            System.out.println("actualizando instrumento");
            instrumentoFinancieroService.actualizarInstrumento(nombre, instrumento);
            return ResponseEntity.ok(instrumento);
        } catch (InstrumentoNoEncontradoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (InstrumentoDuplicadoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}