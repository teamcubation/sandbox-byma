package springApp.java.com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springApp.java.com.example.demo.dto.InstrumentoDTO;
import springApp.java.com.example.demo.mappers.InstrumentoMapper;
import springApp.java.com.example.demo.models.InstrumentoFinancieroModel;
import springApp.java.com.example.demo.services.InstrumentoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instrumentos") // Este es el path base del controller
public class InstrumentoController {

    @Autowired
    InstrumentoService instrumentoService;

    ///////////METODOS CRUD/////////////////
    // AGREGAR INSTRUMENTO
    // http://localhost:5000/api/instrumentos/agregarInstrumento
    // EJEMPLO DE JSON PARA AGREGAR ACCION
    /*
    {
        "tipo":"ACCION",
        "nombre": "BYMA",
        "precio": 100,
        "cantidad": 10,
        "dividendo": 10
    }
    */
    // EJEMPLO DE JSON PARA AGREGAR BONO
    /*
    {
        "tipo":"BONO",
        "nombre": "AL30",
        "precio": 100,
        "cantidad": 10,
        "interes": 10
    }
    */

    //TODO: devolver lo que se creo SIEMPRE DEVOLVER EL DTO
    @PostMapping("/")
    public ResponseEntity<?> agregarInstrumento(@RequestBody InstrumentoDTO instrumentoDTO) {
        // Convierto el DTO a la entidad correspondiente usando el mapper
        InstrumentoFinancieroModel instrumento = InstrumentoMapper.mapToModel(instrumentoDTO);
        instrumentoService.agregarInstrumento(instrumento);
        return ResponseEntity.status(HttpStatus.CREATED).body("Instrumento agregado");
    }

    // OBTENER TODOS LOS INSTRUMENTOS
    // http://localhost:5000/api/instrumentos
    @GetMapping
    public ResponseEntity<List<InstrumentoFinancieroModel>> obtenerInstrumentos() {
        return new ResponseEntity<>(instrumentoService.obtenerInstrumentos(), HttpStatus.OK);
    }

    // BUSCAR POR ID
    // http://localhost:5000/api/instrumentos/obtenerInstrumento/1
    @GetMapping("/{id}")
    public ResponseEntity<InstrumentoFinancieroModel> obtenerInstrumento(@PathVariable("id") Long id) {
        Optional<InstrumentoFinancieroModel> instrumento = instrumentoService.obtenerInstrumento(id);
        return new ResponseEntity<>(instrumento.get(), HttpStatus.OK);
    }

    // ELIMINAR INSTRUMENTO POR ID
    // http://localhost:5000/api/instrumentos/1
    //TODO: devolver statud 204
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarInstrumentoPorId(@PathVariable("id") Long id) {
        instrumentoService.eliminarInstrumentoPorId(id);
        return ResponseEntity.ok("Instrumento eliminado.");
    }

    // EDITAR INSTRUMENTO
    // http://localhost:5000/api/instrumentos/1
    // EJEMPLO DE JSON PARA EDITAR ACCION
    /*
    {
        "nombre": "BYMA",
        "precio": 100,
        "cantidad": 10,
        "dividendo": 10
    }
    */
    // EJEMPLO DE JSON PARA EDITAR BONO
    /*
    {
        "nombre": "AL30",
        "precio": 100,
        "cantidad": 10,
        "interes": 10
    }
    */
    //TODO: devolver statud de instrumento editado 201
    @PutMapping("/{id}")
    public ResponseEntity<String> editarInstrumento(@PathVariable("id") Long id, @RequestBody InstrumentoFinancieroModel instrumento) {
        instrumentoService.editarInstrumento(id, instrumento);
        return ResponseEntity.ok("Instrumento editado correctamente.");
    }
}
