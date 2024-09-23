package springbootApp.java.services;


import org.springframework.beans.factory.annotation.Autowired;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import springbootApp.java.models.InstrumentoFinanciero;
import org.springframework.stereotype.Service;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.models.InstrumentoFactory;
import springbootApp.java.models.Tipo;
import springbootApp.java.repositories.InstrumentoFinancieroRepository;

import java.util.ArrayList;

@Service
public class InstrumentoFinancieroService {
    @Autowired
    private final InstrumentoFinancieroRepository instrumentoFinancieroRepository;

    public InstrumentoFinancieroService() {
        this.instrumentoFinancieroRepository = InstrumentoFinancieroRepository.getInstrumentoFinancieroRepository();
    }

    public void registrarInstrumentoFinanciero(String nombre, double precio, Tipo tipo) {
        InstrumentoFinanciero instrumento = instrumentoFinancieroRepository.buscarInstrumento(nombre);
        if (instrumento != null) {
            throw new InstrumentoDuplicadoException("Error. Accion existente");
        } else {
            this.instrumentoFinancieroRepository.registrarInstrumento(
                    InstrumentoFactory.nuevoInstrumento(nombre, precio, tipo));
        }
    }

    public ArrayList<InstrumentoFinanciero> consultarTodosLosInstrumentos() {
        for (InstrumentoFinanciero instrumento : instrumentoFinancieroRepository.consultarTodosLosInstrumentos()) {
            System.out.println(instrumento);
        }
        return instrumentoFinancieroRepository.consultarTodosLosInstrumentos();
    }

    public void modificarInstrumento(String variable, String modificacion, String nombre) {
        InstrumentoFinanciero instrumento = instrumentoFinancieroRepository.buscarInstrumento(nombre);
        if (instrumento != null) {
            switch (variable) {
                case "1":
                    instrumento.setNombre(modificacion);
                    break;
                case "2":
                    if (modificacion.equals("1")) {
                        instrumento.setTipo(Tipo.ACCION);
                    } else if (modificacion.equals("2")) {
                        instrumento.setTipo(Tipo.BONO);
                    } else {
                        throw new IllegalArgumentException("Error. Tipo incorrecto");
                    }
                    break;
                case "3":
                    try {
                        double numero = Double.parseDouble(modificacion);
                        instrumento.setPrecio(numero);
                    } catch (NumberFormatException e) {
                        System.out.println("Error de formato: ingrese un número.");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Error. los campos no coinciden");
            }
        } else {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }
    }

    public void eliminarInstrumento(String nombre) {
        InstrumentoFinanciero instrumento = instrumentoFinancieroRepository.buscarInstrumento(nombre);
        if (instrumento == null) {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }
        instrumentoFinancieroRepository.eliminarInstrumento(instrumento);
    }

    public InstrumentoFinanciero buscarInstrumento(String nombre) {
        return instrumentoFinancieroRepository.buscarInstrumento(nombre);
    }
}
