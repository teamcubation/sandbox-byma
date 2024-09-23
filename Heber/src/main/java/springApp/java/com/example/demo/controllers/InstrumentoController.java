package springApp.java.com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springApp.java.com.example.demo.models.AccionModel;
import springApp.java.com.example.demo.models.BonoModel;
import springApp.java.com.example.demo.models.InstrumentoFinancieroModel;
import springApp.java.com.example.demo.models.TipoInstrumento;
import springApp.java.com.example.demo.services.InstrumentoService;

@RestController
//@RequestMapping("/instrumento") // Este seria el path base del controller
public class InstrumentoController {

    @Autowired
    private InstrumentoService instrumentoService;

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
    @PostMapping("/agregarInstrumento")
    public ResponseEntity<InstrumentoFinancieroModel> agregarInstrumento(@RequestBody InstrumentoFinancieroModel instrumento) {
        if (instrumento instanceof AccionModel) {
            return new ResponseEntity<>(instrumentoService.agregarAccion((AccionModel) instrumento), HttpStatus.CREATED);
        } else if (instrumento instanceof BonoModel) {
            return new ResponseEntity<>(instrumentoService.agregarBono((BonoModel) instrumento), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // OBTENER TODOS LOS INSTRUMENTOS
    @GetMapping("/obtenerInstrumentos")
    public ResponseEntity<?> obtenerInstrumentos() {
        return new ResponseEntity<>(instrumentoService.obtenerInstrumentos(), HttpStatus.OK);
    }

    //buscar por id
    @GetMapping("/obtenerInstrumento/{id}")
    public ResponseEntity<?> obtenerInstrumento(@PathVariable("id") Long id) {
        return new ResponseEntity<>(instrumentoService.obtenerInstrumento(id), HttpStatus.OK);
    }

    @DeleteMapping ("/eliminarInstrumento/{id}")
    public ResponseEntity<?> eliminarInstrumento(@PathVariable("id") Long id) {
        return new ResponseEntity<>(instrumentoService.eliminarInstrumento(id), HttpStatus.OK);
    }

    @PutMapping("/actualizarInstrumento")
    public ResponseEntity<?> actualizarInstrumento(@RequestBody InstrumentoFinancieroModel instrumento) {
        return new ResponseEntity<>(instrumentoService.actualizarInstrumento(instrumento), HttpStatus.OK);
    }

}
