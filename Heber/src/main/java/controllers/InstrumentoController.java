package controllers;

import exceptions.InstrumentoNoEncontradoException;
import models.Accion;
import models.Bono;
import models.InstrumentoFinanciero;
import services.InstrumentoService;

public class InstrumentoController {

    InstrumentoService instrumentoService = new InstrumentoService();

    public void registrarInstrumento() {
        instrumentoService.registrarInstrumento();
    }

    public void consultarInstrumentos() {
        try {
            instrumentoService.consultarInstrumentos();
        } catch (InstrumentoNoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }

    public InstrumentoFinanciero buscarInstrumentoPorNombre(String nombreInstrumento) {
        return instrumentoService.buscarInstrumentoPorNombre(nombreInstrumento);
    }

    public void modificarNombre(InstrumentoFinanciero instrumento) {
        instrumentoService.modificarNombre(instrumento);
    }

    public void modificarPrecio(InstrumentoFinanciero instrumento) {
        instrumentoService.modificarPrecio(instrumento);
    }

    public void modificarDividendo(InstrumentoFinanciero instrumento) {
        instrumentoService.modificarDividendo((Accion) instrumento);
    }

    public void modificarTasaInteres(InstrumentoFinanciero instrumento) {
        instrumentoService.modificarTasaInteres((Bono) instrumento);
    }


    public void eliminarInstrumentoPorNombre(String nombreInstrumento) {
        try {
            instrumentoService.eliminarInstrumentoPorNombre(nombreInstrumento);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
