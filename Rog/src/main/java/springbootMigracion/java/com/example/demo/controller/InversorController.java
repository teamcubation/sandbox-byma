package springbootMigracion.java.com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootMigracion.java.com.example.demo.dto.InversorDTO;
import springbootMigracion.java.com.example.demo.model.Inversor;
import springbootMigracion.java.com.example.demo.service.IInversorService;
import springbootMigracion.java.com.example.demo.utils.logs.LogMessages;

import java.util.List;

@RestController
@RequestMapping("/api/inversores")
@Slf4j
public class InversorController implements IInversorApi {

    @Autowired
    private IInversorService inversorService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarInversor(@RequestBody InversorDTO inversoroDTO) throws Exception {
        log.info(LogMessages.SOLICITUD_REGISTRAR_INVERSOR.getMessage(), inversoroDTO);
        Inversor inversor = inversorService.registrarInversor(inversoroDTO);
        log.info(LogMessages.INVERSOR_REGISTRADO.getMessage(), inversor);
        return ResponseEntity.status(HttpStatus.CREATED).body(inversor);
    }

    @GetMapping()
    public ResponseEntity<List<Inversor>> obtenerTodosLosInversoresOFiltrarPorNombre(@RequestParam(value = "nombre", required = false) String nombre) {
        List<Inversor> inversores;
        if (nombre != null && !nombre.isEmpty()) {
            inversores = inversorService.listarInversoresPorNombre(nombre);
            log.info(LogMessages.INVERSORES_ENCONTRADOS_POR_NOMBRE.getMessage(), nombre, inversores.size());
        } else {
            inversores = inversorService.listarTodosLosInversores();
            log.info(LogMessages.TODOS_LOS_INVERSORES_ENCONTRADOS.getMessage(), inversores.size());
        }
        return ResponseEntity.ok(inversores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarInversorPorId(@PathVariable Long id) throws Exception{
        log.info(LogMessages.SOLICITUD_BUSCAR_INVERSOR_POR_ID.getMessage(), id);
        Inversor inversor = inversorService.buscarInversorPorId(id);
        log.info(LogMessages.INVERSOR_ENCONTRADO.getMessage(), inversor);
        return ResponseEntity.ok(inversor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarInversor(@PathVariable Long id) throws Exception {
        log.info(LogMessages.SOLICITUD_ELIMINAR_INVERSOR.getMessage(), id);
        inversorService.eliminarInversor(id);
        log.info(LogMessages.INVERSOR_ELIMINADO.getMessage(), id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarInversor(@PathVariable Long id, @RequestBody InversorDTO nuevoInversorDTO) throws Exception {
        log.info(LogMessages.SOLICITUD_EDITAR_INVERSOR.getMessage(), id);
        Inversor inversor = inversorService.editarInversor(id, nuevoInversorDTO);
        log.info(LogMessages.INVERSOR_ACTUALIZADO.getMessage(), id);
        return ResponseEntity.ok(inversor);
    }
}
