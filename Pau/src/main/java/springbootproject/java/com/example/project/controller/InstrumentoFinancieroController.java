package springbootproject.java.com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootproject.java.com.example.project.exceptions.InstrumentoDuplicadoException;
import springbootproject.java.com.example.project.exceptions.InstrumentoNoEncontradoException;
import springbootproject.java.com.example.project.exceptions.NoExisteEseTipoDeInstrumentoException;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.InstrumentoFinanciero;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.InstrumentoFinancieroDTO;
import springbootproject.java.com.example.project.service.InstrumentoFinancieroService;

import java.util.List;

@RestController
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

    @RequestMapping("/get-all")
    public List<InstrumentoFinanciero> consultarTodosLosInstrumentosConocidos() {
        return this.instrumentoFinancieroService.consultarInstrumentosFinancieros();
    }

    @RequestMapping("/consultar-por-nombre/{nombre}")
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

    @PostMapping("/create-instrumento")
    public ResponseEntity<?> crearInstrumento(@RequestBody InstrumentoFinancieroDTO instrumentoFinancieroDTO) {
        try {
            return ResponseEntity.ok(this.instrumentoFinancieroService.registrarInstrumentoFinanciero(instrumentoFinancieroDTO.getNombre(), instrumentoFinancieroDTO.getPrecio(), instrumentoFinancieroDTO.getFechaDeEmision(),instrumentoFinancieroDTO.getTipoInstrumentoFinanciero()));
        } catch (NoExisteEseTipoDeInstrumentoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (InstrumentoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (InstrumentoDuplicadoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-instrumento/{nombre}")
    public ResponseEntity<?> deleteInstrumento(@PathVariable("nombre") String nombre) {
        try {
            this.instrumentoFinancieroService.eliminarInstrumentoFinanciero(nombre);
            return ResponseEntity.ok("El instrumento financiero de nombre " + nombre + " fue eliminado exitosamente.");
        } catch (InstrumentoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/editar-instrumento-nombre/{nombre}/{nuevoNombre}")
    public ResponseEntity<?> editarNombreInstrumento(@PathVariable("nombre") String nombre, @PathVariable("nuevoNombre") String nuevoNombre) {
        try {
            return ResponseEntity.ok(this.instrumentoFinancieroService.editarNombreInstrumentoFinanciero(nombre, nuevoNombre));
        } catch (InstrumentoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/editar-instrumento-precio/{nombre}/{nuevoPrecio}")
    public ResponseEntity<?> editarPrecioInstrumento(@PathVariable("nombre") String nombre, @PathVariable("nuevoPrecio") Double nuevoPrecio) {
        try {
            return ResponseEntity.ok(this.instrumentoFinancieroService.editarPrecioInstrumentoFinanciero(nombre, nuevoPrecio));
        } catch (InstrumentoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
