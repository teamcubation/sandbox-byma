package springbootproject.java.com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springbootproject.java.com.example.project.exceptions.InstrumentoDuplicadoException;
import springbootproject.java.com.example.project.exceptions.InstrumentoNoEncontradoException;
import springbootproject.java.com.example.project.exceptions.NoExisteEseTipoDeInstrumentoException;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.InstrumentoFinanciero;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.TipoInstrumentoFinanciero;
import springbootproject.java.com.example.project.service.InstrumentoFinancieroService;

import java.time.LocalDate;

@RestController
public class Controller {
    private final InstrumentoFinancieroService instrumentoFinancieroService;

    @Autowired
    public Controller(InstrumentoFinancieroService instrumentoFinancieroService) {
        this.instrumentoFinancieroService = instrumentoFinancieroService;
    }

    @RequestMapping("/home")
    public String home() {
        return "Hello World";
    }

    @RequestMapping("/get-all")
    public String consultarTodosLosInstrumentosConocidos() {
        return instrumentoFinancieroService.consultarInstrumentosFinancierosToString();
    }

    @RequestMapping("/get-by-nombre/{nombre}")
    public String consultarUnInstrumentoFinanciero(@PathVariable("nombre") String nombre) {
        try {
            return this.consultarPorUnInstrumentoFinancieroToString(nombre);
        } catch (InstrumentoNoEncontradoException e) {
            return e.getMessage();
        }
    }

    @RequestMapping("/consultar-por-nombre/{nombre}")
    public InstrumentoFinanciero consultarUnInstrumentoFinanciero2(@PathVariable("nombre") String nombre) {
        try {
            return this.instrumentoFinancieroService.consultarPorUnInstrumentoFinanciero(nombre);
        } catch (InstrumentoNoEncontradoException e) {
            //return e.getMessage();
        }
        return null;
    }


    @PostMapping("/create-accion/{nombre}/{precio}")
    public String crearAccion(@PathVariable("nombre") String nombre, @PathVariable("precio") Double precio) {
        try {
            return this.instrumentoFinancieroService.registrarInstrumentoFinanciero(nombre,precio, LocalDate.now(), TipoInstrumentoFinanciero.ACCION).toString();
        } catch (NoExisteEseTipoDeInstrumentoException e) {
            return e.getMessage();
        } catch (InstrumentoNoEncontradoException e) {
            return e.getMessage();
        } catch (InstrumentoDuplicadoException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/create-bono/{nombre}/{precio}")
    public String crearBono(@PathVariable("nombre") String nombre, @PathVariable("precio") Double precio) {
        try {
            return this.instrumentoFinancieroService.registrarInstrumentoFinanciero(nombre,precio, LocalDate.now(), TipoInstrumentoFinanciero.ACCION).toString();
        } catch (NoExisteEseTipoDeInstrumentoException e) {
            return e.getMessage();
        } catch (InstrumentoNoEncontradoException e) {
            return e.getMessage();
        } catch (InstrumentoDuplicadoException e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("/delete-instrumento/{nombre}")
    public String deleteInstrumento(@PathVariable("nombre") String nombre) {
        try {
            this.instrumentoFinancieroService.eliminarInstrumentoFinanciero(nombre);
            return "El instrumento financiero de nombre " + nombre + " fue eliminado exitosamente.";
        } catch (InstrumentoNoEncontradoException e) {
            return e.getMessage();
        }
    }

    @PutMapping("/editar-instrumento-nombre/{nombre}/{nuevoNombre}")
    public String editarNombreInstrumento(@PathVariable("nombre") String nombre, @PathVariable("nuevoNombre") String nuevoNombre) {
        try {
            return this.instrumentoFinancieroService.editarNombreInstrumentoFinanciero(nombre, nuevoNombre).toString();
        } catch (InstrumentoNoEncontradoException e) {
            return e.getMessage();
        }
    }

    @PutMapping("/editar-instrumento-precio/{nombre}/{nuevoPrecio}")
    public String editarPrecioInstrumento(@PathVariable("nombre") String nombre, @PathVariable("nuevoPrecio") Double nuevoPrecio) {
        try {
            return this.instrumentoFinancieroService.editarPrecioInstrumentoFinanciero(nombre, nuevoPrecio).toString();
        } catch (InstrumentoNoEncontradoException e) {
            return e.getMessage();
        }
    }

    public String consultarPorUnInstrumentoFinancieroToString(String nombre) throws InstrumentoNoEncontradoException {
            return this.instrumentoFinancieroService.consultarPorUnInstrumentoFinanciero(nombre).toString();
    }



}
