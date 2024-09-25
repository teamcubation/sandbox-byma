package springbootApp.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
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




    @PostMapping("/registrarInstrumentoFinanciero")
    public ResponseEntity<InstrumentoDTO> registrarInstrumentoFinanciero(@RequestBody InstrumentoDTO instrumentoFinanciero) {
        try {
            instrumentoFinancieroService.registrarInstrumentoFinanciero(instrumentoFinanciero.getNombre(),
                    instrumentoFinanciero.getPrecio(), instrumentoFinanciero.getTipo());
            System.out.println("instrumento registrado");
            return new ResponseEntity<InstrumentoDTO>(instrumentoFinanciero, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
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
    public String eliminarInstrumento(@PathVariable String nombre)  {
        try {
            instrumentoFinancieroService.eliminarInstrumento(nombre);
            return "Instrumento eliminado";
        } catch (InstrumentoNoEncontradoException e) {
            return e.getMessage();
        }
    }


    @PutMapping("/actualizarInstrumento/{nombre}")
    public InstrumentoDTO actualizarInstrumento(@PathVariable String nombre, @RequestBody InstrumentoDTO instrumento) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {
        instrumentoFinancieroService.actualizarInstrumento(nombre, instrumento);
        return instrumento;
    }
}