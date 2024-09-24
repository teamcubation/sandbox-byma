package springApp.java.com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springApp.java.com.example.demo.exceptions.InstrumentoNoEncontradoException;
import springApp.java.com.example.demo.models.AccionModel;
import springApp.java.com.example.demo.models.BonoModel;
import springApp.java.com.example.demo.models.InstrumentoFinancieroModel;
import springApp.java.com.example.demo.services.InstrumentoService;
import springApp.java.com.example.demo.exceptions.InstrumentoDuplicadoException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instrumentos") // Este es el path base del controller
public class InstrumentoController {

    @Autowired
    InstrumentoService instrumentoService;

    @GetMapping("/hola")
    public String instrumento() {
        return "asdasd";
    }

    // GET URL: localhost:8081/service-service
    @RequestMapping("/hello-service/{id}")
    public String helloService(@PathVariable("id") Long id) {
        System.out.println("el id es: " + id);
        return instrumentoService.helloService();
    }


    // GET URL: localhost:8081/hello-repository
    @RequestMapping("/hello-repository")
    public String helloRepository() {
        return instrumentoService.helloRepository();
    }

    ///////////METODOS CRUD/////////////////
    // AGREGAR INSTRUMENTO
    //http://localhost:5000/api/instrumentos/agregarInstrumento
    //EJEMPLO DE JSON PARA AGREGAR ACCION
    /*
    {
        "tipo":"ACCION",
        "nombre": "BYMA",
        "precio": 100,
        "cantidad": 10,
        "dividendo": 10
    }
     */

    //EJEMPLO DE JSON PARA AGREGAR BONO
    /*
    {
        "tipo":"BONO",
        "nombre": "AL30",
        "precio": 100,
        "cantidad": 10,
        "interes": 10
    }
     */
    @PostMapping("/agregarInstrumento")
    public ResponseEntity<String> agregarInstrumento(@RequestBody InstrumentoFinancieroModel instrumento) {
        try {
            if (instrumento instanceof AccionModel) {
                instrumentoService.agregarAccion((AccionModel) instrumento);
                return ResponseEntity.status(HttpStatus.CREATED).body("Accion agregada");
            } else if (instrumento instanceof BonoModel) {
                instrumentoService.agregarBono((BonoModel) instrumento);
                return ResponseEntity.status(HttpStatus.CREATED).body("Bono agregado");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo de instrumento no valido");
            }
        } catch (InstrumentoDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al agregar instrumento");
        }
    }


    // OBTENER TODOS LOS INSTRUMENTOS
    //http://localhost:5000/api/instrumentos
    @GetMapping
    public ResponseEntity<List<InstrumentoFinancieroModel>> obtenerInstrumentos() {
        return new ResponseEntity<>(instrumentoService.obtenerInstrumentos(), HttpStatus.OK);
    }

    //buscar por id
    //http://localhost:5000/api/instrumentos/obtenerInstrumento/1
    @GetMapping("/obtenerInstrumento/{id}")
    public ResponseEntity<?> obtenerInstrumento(@PathVariable("id") Long id) {
        try {
            Optional<InstrumentoFinancieroModel> instrumento = instrumentoService.obtenerInstrumento(id);
            return new ResponseEntity<>(instrumento, HttpStatus.OK);
        } catch (InstrumentoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener instrumento", HttpStatus.BAD_REQUEST);
        }
    }

    // ELIMINAR INSTRUMENTO POR ID
    //http://localhost:5000/api/instrumentos/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarInstrumentoPorId(@PathVariable("id") Long id) {
        try {
            instrumentoService.eliminarInstrumentoPorId(id);
            return ResponseEntity.ok("Instrumento eliminado.");
        } catch (InstrumentoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar instrumento", HttpStatus.BAD_REQUEST);
        }
    }

    //EDITAR INSTRUMENTO
    //http://localhost:5000/api/instrumentos/1
    //EJEMPLO DE JSON PARA EDITAR ACCION
    /*
    {
        "nombre": "BYMA",
        "precio": 100,
        "cantidad": 10,
        "dividendo": 10
    }
     */

    //EJEMPLO DE JSON PARA EDITAR BONO
    /*
    {
        "nombre": "AL30",
        "precio": 100,
        "cantidad": 10,
        "interes": 10
    }
     */

    @PutMapping("/{id}")
    public ResponseEntity<String> editarInstrumento(@PathVariable("id") Long id, @RequestBody InstrumentoFinancieroModel instrumento) {
        try {
            instrumentoService.editarInstrumento(id, instrumento);
            return ResponseEntity.ok("Instrumento editado correctamente.");
        } catch (InstrumentoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return new ResponseEntity<>("Error al editar instrumento", HttpStatus.BAD_REQUEST);
        }
    }

}
