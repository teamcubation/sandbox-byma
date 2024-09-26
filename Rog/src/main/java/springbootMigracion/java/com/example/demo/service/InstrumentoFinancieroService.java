package springbootMigracion.java.com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootMigracion.java.com.example.demo.exception.InstrumentoDuplicadoException;
import springbootMigracion.java.com.example.demo.exception.InstrumentoNoEncontradoException;
import springbootMigracion.java.com.example.demo.exception.InversorNoEncontradoException;
import springbootMigracion.java.com.example.demo.model.InstrumentoFinanciero;
import springbootMigracion.java.com.example.demo.model.Inversor;
import springbootMigracion.java.com.example.demo.repository.IInstrumentoFinancieroRepository;
import springbootMigracion.java.com.example.demo.repository.IInversorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InstrumentoFinancieroService implements IInstrumentoFinancieroService {

    @Autowired
    private IInstrumentoFinancieroRepository instrumentoFinancieroRepository;

    @Autowired
    private IInversorRepository inversorRepository;

    @Override
    public void registrarInstrumento(InstrumentoFinanciero instrumento) {
        instrumentoFinancieroRepository.agregarInstrumento(instrumento);
    }

    @Override
    public void eliminarInstrumento(String nombre) {
        instrumentoFinancieroRepository.eliminarInstrumento(nombre);
    }

    @Override
    public List<InstrumentoFinanciero> listarTodosLosInstrumentos() {
        return instrumentoFinancieroRepository.listarTodosLosInstrumentos();
    }

    @Override
    public Optional<InstrumentoFinanciero> buscarInstrumentoPorNombre(String nombre) {
        return instrumentoFinancieroRepository.buscarInstrumentoPorNombre(nombre);
    }

    @Override
    public void editarInstrumento(String nombre, InstrumentoFinanciero nuevoInstrumento) {
        instrumentoFinancieroRepository.editarInstrumento(nombre, nuevoInstrumento);
    }

    @Override
    public void suscribirInversor(String nombreInstrumento, String nombreInversor) {
        InstrumentoFinanciero instrumento = instrumentoFinancieroRepository.buscarInstrumentoPorNombre(nombreInstrumento)
                .orElseThrow(() -> new InstrumentoNoEncontradoException("Instrumento no encontrado."));
        Inversor inversor = inversorRepository.buscarInversorPorNombre(nombreInversor)
                .orElseThrow(() -> new InversorNoEncontradoException("Inversor no encontrado."));
        if (!inversor.getInstrumentosSuscritos().contains(instrumento)){
            inversor.getInstrumentosSuscritos().add(instrumento);
        } else {
            throw new InstrumentoDuplicadoException("Ya se encuentra suscrito al instrumento ingresado");
        }

    }



}
