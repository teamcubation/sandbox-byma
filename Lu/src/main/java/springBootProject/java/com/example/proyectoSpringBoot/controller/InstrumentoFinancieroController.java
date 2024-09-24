package springBootProject.java.com.example.proyectoSpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springBootProject.java.com.example.proyectoSpringBoot.dto.InstrumentoFinancieroDTO;
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
    private ResponseEntity<?> consultar(@PathVariable("nombre") String nombre) {
        try {
            return ResponseEntity.ok(this.instrumentoFinancieroService.consultar(nombre));
        } catch (InstrumentoNoEncontradoException mensaje) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje.getMessage());
        }
    }

    @PostMapping("/crear/instrumentoFinanciero")
    private ResponseEntity<?> registrar (@RequestBody InstrumentoFinancieroDTO instrumentoDTO) {
        try {
            String instrumentoFinancieroNuevo = this.instrumentoFinancieroService.registrar(instrumentoDTO).toString();
            return ResponseEntity.status(HttpStatus.CREATED).body("Instrumento financiero creado con éxito: " + instrumentoFinancieroNuevo);
        } catch (InstrumentoDuplicadoException mensaje) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(mensaje.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{nombre}")
    private ResponseEntity<?> eliminar(@PathVariable("nombre") String nombre){
        try {
            String instrumentoFinancieroEliminado = this.instrumentoFinancieroService.eliminar(nombre).toString();
            return ResponseEntity.ok("Instrumento financiero eliminado con éxito: " + instrumentoFinancieroEliminado);
        } catch (InstrumentoNoEncontradoException mensaje) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje.getMessage());
        }
    }

    @PutMapping("/editar/{nombre}")
    private ResponseEntity<?> editar(@PathVariable("nombre") String instrumentoAEditar, @RequestBody InstrumentoFinancieroDTO instrumentoDTO) {
        try {
            String instrumentoFinancieroEditado = this.instrumentoFinancieroService.editar(instrumentoAEditar, instrumentoDTO).toString();
            return ResponseEntity.ok("Instrumento financiero editado con éxito: " + instrumentoFinancieroEditado);
        } catch (InstrumentoNoEncontradoException mensaje) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje.getMessage());
        }
    }
}
