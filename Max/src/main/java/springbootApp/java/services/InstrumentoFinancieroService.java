package springbootApp.java.services;


import org.springframework.beans.factory.annotation.Autowired;
import springbootApp.java.exceptions.InstrumentoNoEncontradoException;
import springbootApp.java.DTOs.InstrumentoDTO;
import springbootApp.java.models.InstrumentoFinanciero;
import org.springframework.stereotype.Service;
import springbootApp.java.exceptions.InstrumentoDuplicadoException;
import springbootApp.java.models.InstrumentoFactory;
import springbootApp.java.models.Tipo;
import springbootApp.java.repositories.InstrumentoFinancieroRepository;
import springbootApp.java.utils.Validaciones;

import java.util.List;

@Service
public class InstrumentoFinancieroService {
    @Autowired
    private InstrumentoFinancieroRepository instrumentoFinancieroRepository;


    public void registrarInstrumentoFinanciero(String nombre, double precio, Tipo tipo) throws InstrumentoDuplicadoException {
        if (validarDatos(nombre, precio, tipo)) {
            InstrumentoFinanciero instrumento = instrumentoFinancieroRepository.buscarInstrumento(nombre);
            if (instrumento != null) {
                throw new InstrumentoDuplicadoException("Error. Accion existente");
            } else {
                this.instrumentoFinancieroRepository.registrarInstrumento(
                        InstrumentoFactory.nuevoInstrumento(nombre, precio, tipo));
            }
        }
    }

    public List<InstrumentoFinanciero> consultarTodosLosInstrumentos() {
        for (InstrumentoFinanciero instrumento : instrumentoFinancieroRepository.consultarTodosLosInstrumentos()) {
            System.out.println(instrumento);
        }
        return instrumentoFinancieroRepository.consultarTodosLosInstrumentos();
    }

    public void eliminarInstrumento(String nombre) throws InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumento = instrumentoFinancieroRepository.buscarInstrumento(nombre);
        if (instrumento == null) {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }
        instrumentoFinancieroRepository.eliminarInstrumento(instrumento);
    }

    public InstrumentoFinanciero buscarInstrumento(String nombre) {
        return instrumentoFinancieroRepository.buscarInstrumento(nombre);
    }

    public void actualizarInstrumento(String nombre, InstrumentoDTO instrumento) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {
        validarDatos(instrumento.getNombre(), instrumento.getPrecio(), instrumento.getTipo());
        instrumentoFinancieroRepository.modificarInstrumento(nombre, instrumento);
    }

    private boolean validarDatos(String nombre, double precio, Tipo tipo) {
        return !Validaciones.validarNombre(nombre) || !Validaciones.validarPrecio(precio) || !Validaciones.validarTipo(tipo);
    }

}