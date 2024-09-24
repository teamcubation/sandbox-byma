package springBootProject.java.com.example.proyectoSpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springBootProject.java.com.example.proyectoSpringBoot.excepciones.InstrumentoDuplicadoException;
import springBootProject.java.com.example.proyectoSpringBoot.excepciones.InstrumentoNoEncontradoException;
import springBootProject.java.com.example.proyectoSpringBoot.model.InstrumentoFinanciero;
import springBootProject.java.com.example.proyectoSpringBoot.service.serviceImpl.InstrumentoFinancieroServiceImpl;

import java.util.List;

@RestController
public class InstrumentoFinancieroController {

    @Autowired
    private InstrumentoFinancieroServiceImpl instrumentoFinancieroService;

    @RequestMapping("/consultar_todos")
    private List<InstrumentoFinanciero> consultarTodos() {
        return this.instrumentoFinancieroService.consultarTodos();
    }

    @RequestMapping("/consultar/{nombre}")
    private String consultar(@PathVariable("nombre") String nombre) {
        try {
            return this.instrumentoFinancieroService.consultar(nombre).toString();
        } catch (InstrumentoNoEncontradoException mensaje) {
            return mensaje.getMessage();
        }
    }

    @PostMapping("/crear/{nombre}/{precio}")
    private String registrar (@PathVariable("nombre") String nombre, @PathVariable("precio") double precio) {
        try {
            return "Instrumento financiero creado con éxito: " + this.instrumentoFinancieroService.registrar(nombre, precio, 1).toString();
        } catch (InstrumentoDuplicadoException mensaje) {
            return mensaje.getMessage();
        }
    }

    @DeleteMapping("/eliminar/{nombre}")
    private String eliminar(@PathVariable("nombre") String nombre){
        try {
            return "Instrumento financiero eliminado con éxito: " + this.instrumentoFinancieroService.eliminar(nombre).toString();
        } catch (InstrumentoNoEncontradoException mensaje) {
            return mensaje.getMessage();
        }
    }

    @PutMapping("/editar/{instrumentoAEditar}/{nombre}/{precio}")
    private String editar(@PathVariable("instrumentoAEditar") String instrumentoAEditar, @PathVariable("nombre") String nombreNuevo, @PathVariable("precio") double precio) {
        try {
            return "Instrumento financiero editado con éxito: " + this.instrumentoFinancieroService.editar(instrumentoAEditar, nombreNuevo, precio).toString();
        } catch (InstrumentoNoEncontradoException mensaje) {
            return mensaje.getMessage();
        }
    }
}
