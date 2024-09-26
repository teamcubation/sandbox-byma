package springbootproject.java.com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootproject.java.com.example.project.exceptions.InversorNoEncontradoException;
import springbootproject.java.com.example.project.exceptions.InversorYaRegistradoException;
import springbootproject.java.com.example.project.model.Inversor;
import springbootproject.java.com.example.project.service.InversorService;

import java.util.List;

@RestController
@RequestMapping("/inversores")
public class InversorController {
    private final InversorService inversorService;

    @Autowired
    public InversorController(InversorService inversorService) {
        this.inversorService = inversorService;
    }

    @RequestMapping("/")
    public List<Inversor> obtenerInversores() {
        return inversorService.obtenerInversores();
    }

    @PostMapping("/")
    public ResponseEntity<?> registrarInversor(@RequestBody InversorDTO inversorDTO) {
        try {
            return ResponseEntity.ok(this.inversorService.registrarInversor(inversorDTO));
        } catch (InversorYaRegistradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @RequestMapping("/inversores/{nombre}")
    public ResponseEntity<?> obtenerInversorPorNombre(@PathVariable String nombre) {
        try {
            return ResponseEntity.ok(this.inversorService.consultarPorUnInversor(nombre));
        } catch (InversorNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<?> eliminarInversorPorNombre(@PathVariable String nombre) {
        try {
            this.inversorService.eliminarInversor(nombre);
            return ResponseEntity.ok("El inversor de nombre " + nombre + " fue eliminado exitosamente.");
        } catch (InversorNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
