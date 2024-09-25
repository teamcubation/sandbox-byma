package springbootApp.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootApp.java.exceptions.InversorNoEncontradoException;
import springbootApp.java.models.InstrumentoFinanciero;
import springbootApp.java.models.Inversor;
import springbootApp.java.models.InversorDTO;
import springbootApp.java.services.InversorService;

import java.util.List;

@RestController
@RequestMapping("/inversor")
public class InversorController {

    @Autowired
    private InversorService inversorService;

    @PostMapping("/")
    public ResponseEntity<InversorDTO> registrarInversor(@RequestBody InversorDTO inversor) {
        try {
            System.out.println("intentando registrar inversor...");
            inversorService.registrarInversor(inversor.getNombre(), inversor.getDni());
            return ResponseEntity.ok(inversor);
        } catch (Exception e) {
            System.out.println("Error al registrar inversor: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Inversor>> obtenerTodosLosInversores() {
        try {
            System.out.println("intentando obtener inversores...");
            return ResponseEntity.ok(inversorService.consultarTodosLosInversores());
        } catch (Exception e) {
            System.out.println("Error al obtener inversores: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/instrumentos/{dni}")
    public ResponseEntity<List<InstrumentoFinanciero>> obtenerInstrumentosDeInversor(@PathVariable String dni) throws InversorNoEncontradoException {
        try {
            return ResponseEntity.ok(inversorService.consultarInstrumentosDeInversor(dni));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{dni}")
    public ResponseEntity<InversorDTO> actualizarInversor(@PathVariable String dni, @RequestBody InversorDTO inversor) throws InversorNoEncontradoException {
        try {
            inversorService.actualizarInversor(dni, inversor);
            return ResponseEntity.ok(inversor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<String> eliminarInversor(@PathVariable String dni) throws InversorNoEncontradoException {
        try {
            inversorService.eliminarInversor(dni);
            return ResponseEntity.ok("Inversor eliminado");
        } catch (InversorNoEncontradoException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
