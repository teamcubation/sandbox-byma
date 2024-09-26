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
        if (buscarInstrumentoPorNombre(instrumento.getNombre()).isPresent()){
            throw new InstrumentoDuplicadoException("EL instrumento ya existe.");
        }
        instrumentoFinancieroRepository.save(instrumento);
    }

    @Override
    public void eliminarInstrumento(String nombre) {
        InstrumentoFinanciero instrumento = instrumentoFinancieroRepository.findByNombreIgnoreCase(nombre)
                .orElseThrow(() -> new InstrumentoNoEncontradoException("Instrumento no encontrado"));
        for (Inversor inversor : instrumento.getInversoresSuscritosList()) {
            inversor.getInstrumentosSuscritosList().remove(instrumento);
        }
        instrumentoFinancieroRepository.delete(instrumento);
    }

    @Override
    public List<InstrumentoFinanciero> listarTodosLosInstrumentos() {
        return instrumentoFinancieroRepository.findAll();
    }

    @Override
    public Optional<InstrumentoFinanciero> buscarInstrumentoPorNombre(String nombre) {
        return instrumentoFinancieroRepository.findByNombreIgnoreCase(nombre);
    }

    @Override
    public void editarInstrumento(String nombre, InstrumentoFinanciero nuevoInstrumento) {
        InstrumentoFinanciero instrumentoExistente = instrumentoFinancieroRepository.findByNombreIgnoreCase(nombre)
                .orElseThrow(() -> new InstrumentoNoEncontradoException("Instrumento no encontrado"));
        nuevoInstrumento.setId(instrumentoExistente.getId());
        instrumentoFinancieroRepository.save(nuevoInstrumento);
    }

    @Override
    public void suscribirInversor(String nombreInstrumento, String nombreInversor) {
        InstrumentoFinanciero instrumento = instrumentoFinancieroRepository.findByNombreIgnoreCase(nombreInstrumento)
                .orElseThrow(() -> new InstrumentoNoEncontradoException("Instrumento no encontrado."));
        Inversor inversor = inversorRepository.findByNombreIgnoreCase(nombreInversor)
                .orElseThrow(() -> new InversorNoEncontradoException("Inversor no encontrado."));
        if (!inversor.getInstrumentosSuscritosList().contains(instrumento) && !instrumento.getInversoresSuscritosList().contains(inversor)){
            inversor.getInstrumentosSuscritosList().add(instrumento);
            instrumento.getInversoresSuscritosList().add(inversor);
            instrumentoFinancieroRepository.save(instrumento);
            inversorRepository.save(inversor);
        } else {
            throw new InstrumentoDuplicadoException("Ya se encuentra suscrito al instrumento ingresado");
        }

    }



}
