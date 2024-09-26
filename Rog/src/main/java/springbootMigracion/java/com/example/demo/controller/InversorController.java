package springbootMigracion.java.com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootMigracion.java.com.example.demo.dto.InversorDTO;
import springbootMigracion.java.com.example.demo.exception.InversorDuplicadoException;
import springbootMigracion.java.com.example.demo.exception.InversorNoEncontradoException;
import springbootMigracion.java.com.example.demo.model.Inversor;
import springbootMigracion.java.com.example.demo.service.IInversorService;

import java.util.List;

@RestController
@RequestMapping("/api/inversores")
public class InversorController {

    @Autowired
    private IInversorService inversorService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarInversor(@RequestBody InversorDTO inversoroDTO) {
        try {
            inversorService.registrarInversor(inversoroDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Inversor registrado correctamente.");
        } catch (InversorDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Inversor>> listarInversores() {
        return ResponseEntity.ok(inversorService.listarTodosLosInversores());
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<?> buscarInversor(@PathVariable String nombre) {
        try {
            Inversor inversor = inversorService.buscarInversorPorNombre(nombre)
                    .orElseThrow(() -> new InversorNoEncontradoException("Inversor no encontrado"));
            return ResponseEntity.ok(inversor);
        } catch (InversorNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<String> eliminarInversor(@PathVariable String nombre) {
        try {
            inversorService.eliminarInversor(nombre);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (InversorNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{nombre}")
    public ResponseEntity<String> editarInversor(@PathVariable String nombre, @RequestBody InversorDTO inversorDTO) {
        try {
            inversorService.editarInversor(nombre, inversorDTO);
            return ResponseEntity.ok("Inversor editado correctamente.");
        } catch (InversorNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
