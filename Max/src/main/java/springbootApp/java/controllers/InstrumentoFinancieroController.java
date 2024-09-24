package springbootApp.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import springbootApp.java.models.Accion;
import springbootApp.java.models.InstrumentoDTO;
import springbootApp.java.models.InstrumentoFinanciero;
import springbootApp.java.models.Tipo;
import springbootApp.java.services.InstrumentoFinancieroService;

import java.util.ArrayList;

@RestController
@RequestMapping("/instrumentoFinanciero")
public class InstrumentoFinancieroController {

    @Autowired
    private InstrumentoFinancieroService instrumentoFinancieroService;

    public InstrumentoFinancieroController(InstrumentoFinancieroService instrumentoFinancieroService) {
        this.instrumentoFinancieroService = instrumentoFinancieroService;
    }

    @GetMapping("/prueba")
    public String hello() {
        return "holamundo";
    }

    @PostMapping("/prueba")
    public String prueba(@RequestBody String variable) {
        return variable;
    }

    @PostMapping("/registrarInstrumentoFinanciero")
    public String registrarInstrumentoFinanciero(@RequestBody InstrumentoDTO instrumentoFinanciero) {
        try {
            instrumentoFinancieroService.registrarInstrumentoFinanciero(instrumentoFinanciero.getNombre(),
                    instrumentoFinanciero.getPrecio(), instrumentoFinanciero.getTipo());
            System.out.println("instrumento registrado");
            return instrumentoFinanciero.getNombre();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "error";
        }

        // return "Nuevo instrumento registrado de tipo " + instrumentoFinanciero.getTipo() +
        //       " con nombre " + instrumentoFinanciero.getNombre() + " y precio de " + instrumentoFinanciero.getPrecio();
    }

    @GetMapping("/obtenerTodosLosInstrumentos")
    public ArrayList<InstrumentoFinanciero> obtenerTodosLosInstrumentos() {
        return instrumentoFinancieroService.consultarTodosLosInstrumentos();
    }

    @GetMapping("/obtenerInstrumentoPorNombre/{nombre}")
    public InstrumentoFinanciero obtenerInstrumentoPorNombre(@PathVariable String nombre) {
        return instrumentoFinancieroService.buscarInstrumento(nombre);
    }

    @DeleteMapping("/eliminarInstrumento/{nombre}")
    public String eliminarInstrumento(@PathVariable String nombre) throws InstrumentoNoEncontradoException {
        instrumentoFinancieroService.eliminarInstrumento(nombre);
        return "Instrumento eliminado";
    }

    @PutMapping("/modificarInstrumento/{nombre}/variable/{variable}")
    public String modificarInstrumento(@PathVariable String nombre, @PathVariable String variable, @RequestBody String modificacion) throws InstrumentoNoEncontradoException {
        instrumentoFinancieroService.modificarInstrumento(nombre, variable, modificacion);
        return "Instrumento modificado";
    }
    @PutMapping("/actualizarInstrumento/{nombre}")
    public String actualizarInstrumento(@PathVariable String nombre, @RequestBody InstrumentoFinanciero instrumento) throws InstrumentoNoEncontradoException {
        instrumentoFinancieroService.actualizarInstrumento(nombre, instrumento);
        return "Instrumento modificado";
    }
}