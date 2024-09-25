package springbootApp.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springbootApp.java.exceptions.InversorNoEncontradoException;
import springbootApp.java.models.InstrumentoFinanciero;
import springbootApp.java.models.Inversor;
import springbootApp.java.models.InversorDTO;
import springbootApp.java.services.InversorService;

import java.util.ArrayList;

@RestController
@RequestMapping("/inversor")
public class InversorController {

    @Autowired
    private InversorService inversorService;

    public InversorController(InversorService inversorService) {
        this.inversorService = inversorService;
    }

    @PostMapping("/registrarInversor")
    public String registrarInversor(@RequestBody InversorDTO inversor) {
        inversorService.registrarInversor(inversor.getNombre(), inversor.getDni());
        return "Inversor registrado";
    }

    @GetMapping("/obtenerTodosLosInversores")
    public ArrayList<Inversor> obtenerTodosLosInversores() {
        ArrayList<Inversor> inversores = inversorService.consultarTodosLosInversores();
        return inversores;
    }

    @GetMapping("/obtenerInstrumentosDeInversor/{dni}")
    public ArrayList<InstrumentoFinanciero> obtenerInstrumentosDeInversor(@PathVariable String dni) throws InversorNoEncontradoException {
        return inversorService.consultarInstrumentosDeInversor(dni);
    }


    @RequestMapping("/actualizarInversor/{dni}")
    public String actualizarInversor(@PathVariable String dni, @RequestBody InversorDTO inversor) throws InversorNoEncontradoException {
        inversorService.actualizarInversor(dni, inversor);
        return "Inversor modificado";
    }

    @RequestMapping("/eliminarInversor/{dni}")
    public String eliminarInversor(@PathVariable String dni) throws InversorNoEncontradoException {
        inversorService.eliminarInversor(dni);
        return "Inversor eliminado";
    }
}
