package springbootMigracion.java.com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootMigracion.java.com.example.demo.dto.InversorDTO;
import springbootMigracion.java.com.example.demo.model.Inversor;
import springbootMigracion.java.com.example.demo.service.IInversorService;

import java.util.List;

@RestController
@RequestMapping("/api/inversores")
@Slf4j
public class InversorController implements IInversorApi {

    @Autowired
    private IInversorService inversorService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarInversor(@RequestBody InversorDTO inversoroDTO) throws Exception {
        log.info("Solicitud para agregar inversor: {}", inversoroDTO);
        Inversor inversor = inversorService.registrarInversor(inversoroDTO);
        log.info("Inversor agregado exitosamente: {}", inversor);
        return ResponseEntity.status(HttpStatus.CREATED).body(inversor);
    }

    @GetMapping
    public ResponseEntity<List<Inversor>> listarInversores() {
        List<Inversor> inversorList = inversorService.listarTodosLosInversores();
        log.info("Inversores listados: {}", inversorList.size());
        return ResponseEntity.ok(inversorList);
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<Inversor>> listarInversoresPorNombre(@PathVariable String nombre) {
        List<Inversor> inversoresPorNombre = inversorService.listarInversoresPorNombre(nombre);
        log.info("Instrumentos encontrados por nombre: {}", inversoresPorNombre.size());
        return ResponseEntity.ok(inversoresPorNombre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarInversorPorId(@PathVariable Long id) throws Exception{
        log.info("Solicitud para buscar inversor por ID: {}", id);
        Inversor inversor = inversorService.buscarInversorPorId(id);
        log.info("Inversor encontrado: {}", inversor);
        return ResponseEntity.ok(inversor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarInversor(@PathVariable Long id) throws Exception {
        log.info("Solicitud para eliminar inversor con ID: {}", id);
        inversorService.eliminarInversor(id);
        log.info("Inversor con ID {} eliminado", id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarInversor(@PathVariable Long id, @RequestBody InversorDTO nuevoInversorDTO) throws Exception {
        log.info("Solicitud para editar inversor con ID: {}", id);
        Inversor inversor = inversorService.editarInversor(id, nuevoInversorDTO);
        log.info("Inversor con ID {} actualizado exitosamente", id);
        return ResponseEntity.ok(inversor);
    }
}
