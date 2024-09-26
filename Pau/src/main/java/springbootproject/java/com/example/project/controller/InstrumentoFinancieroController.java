package springbootproject.java.com.example.project.controller;

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
        return ResponseEntity.ok(instrumentoFinancieroService.consultarInstrumentosFinancieros());
    }

    @RequestMapping("/{nombre}")
    public ResponseEntity<?> consultarUnInstrumentoFinanciero(@PathVariable("nombre") String nombre) {
        try {
            return ResponseEntity.ok(this.instrumentoFinancieroService.consultarPorUnInstrumentoFinanciero(nombre));
        } catch (InstrumentoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/create-accion/{nombre}/{precio}")
//    public String crearAccion(@PathVariable("nombre") String nombre, @PathVariable("precio") Double precio) {
//        try {
//            return this.instrumentoFinancieroService.registrarInstrumentoFinanciero(nombre,precio, LocalDate.now(), TipoInstrumentoFinanciero.ACCION).toString();
//        } catch (NoExisteEseTipoDeInstrumentoException e) {
//            return e.getMessage();
//        } catch (InstrumentoNoEncontradoException e) {
//            return e.getMessage();
//        } catch (InstrumentoDuplicadoException e) {
//            return e.getMessage();
//        }
//    }
//
//    @PostMapping("/create-bono/{nombre}/{precio}")
//    public String crearBono(@PathVariable("nombre") String nombre, @PathVariable("precio") Double precio) {
//        try {
//            return this.instrumentoFinancieroService.registrarInstrumentoFinanciero(nombre,precio, LocalDate.now(), TipoInstrumentoFinanciero.ACCION).toString();
//        } catch (NoExisteEseTipoDeInstrumentoException e) {
//            return e.getMessage();
//        } catch (InstrumentoNoEncontradoException e) {
//            return e.getMessage();
//        } catch (InstrumentoDuplicadoException e) {
//            return e.getMessage();
//        }
//    }

    @PostMapping("/")
    public ResponseEntity<?> crearInstrumento(@RequestBody InstrumentoFinancieroDTO instrumentoFinancieroDTO) {
        try {
            return ResponseEntity.ok(this.instrumentoFinancieroService.registrarInstrumentoFinanciero(instrumentoFinancieroDTO));
        } catch (NoExisteEseTipoDeInstrumentoException | InstrumentoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (InstrumentoDuplicadoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<?> deleteInstrumento(@PathVariable("nombre") String nombre) {
        try {
            this.instrumentoFinancieroService.eliminarInstrumentoFinanciero(nombre);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (InstrumentoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{nombre}")
    public ResponseEntity<?> editarInstrumento(@RequestBody EditarInstrumentoDTO editarInstrumentoDTO, @PathVariable("nombre") String nombre) {
        try {
            return ResponseEntity.ok(this.instrumentoFinancieroService.editarInstrumentoFinanciero(nombre, editarInstrumentoDTO));
        } catch (InstrumentoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping("/precio/{nombre}/{nuevoPrecio}")
//    public ResponseEntity<?> editarPrecioInstrumento(@PathVariable("nombre") String nombre, @PathVariable("nuevoPrecio") Double nuevoPrecio) {
//        try {
//            return ResponseEntity.ok(this.instrumentoFinancieroService.editarPrecioInstrumentoFinanciero(nombre, nuevoPrecio));
//        } catch (InstrumentoNoEncontradoException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
}
