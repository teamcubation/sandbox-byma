package springbootproject.java.com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootproject.java.com.example.project.exceptions.InstrumentoDuplicadoException;
import springbootproject.java.com.example.project.exceptions.InstrumentoNoEncontradoException;
import springbootproject.java.com.example.project.exceptions.NoExisteEseTipoDeInstrumentoException;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.InstrumentoFinanciero;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.TipoInstrumentoFinanciero;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.factoryInstrumentos.AccionFactory;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.factoryInstrumentos.BonoFactory;
import springbootproject.java.com.example.project.repository.InstrumentosFinancierosRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

// SINGLETON
@Service
public class InstrumentoFinancieroService {
    private static InstrumentoFinancieroService instance;
    private final InstrumentosFinancierosRepository instrumentosFinancierosRepository;

    @Autowired
    private InstrumentoFinancieroService(InstrumentosFinancierosRepository instrumentosFinancierosRepository) {
        this.instrumentosFinancierosRepository = instrumentosFinancierosRepository;
    }

//    public static InstrumentoFinancieroService getInstance() {
//        if (instance == null) {
//            instance = new InstrumentoFinancieroService();
//        }
//        return instance;
//    }

    public ArrayList<InstrumentoFinanciero> consultarInstrumentosFinancieros() {
        return instrumentosFinancierosRepository.consultarInstrumentosFinancieros();
    }

    public String consultarInstrumentosFinancierosToString() {
        return this.consultarInstrumentosFinancieros().stream().map(x -> x.toString()).collect(Collectors.joining(" \n"));
    }

    public InstrumentoFinanciero consultarPorUnInstrumentoFinanciero(String nombre) throws InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumentoFinanciero = this.instrumentosFinancierosRepository.consultarPorUnInstrumentoFinanciero(nombre);
        if (instrumentoFinanciero == null) {
            throw new InstrumentoNoEncontradoException("El instrumento con nombre " + nombre + " no fue encontrado.");
        } else {
            return instrumentoFinanciero;
        }

    }

    public InstrumentoFinanciero editarNombreInstrumentoFinanciero(String nombreActual, String nombreNuevo) throws InstrumentoNoEncontradoException {
        return this.instrumentosFinancierosRepository.editarNombreInstrumento(nombreActual, nombreNuevo);
    }

    public InstrumentoFinanciero editarPrecioInstrumentoFinanciero(String nombreActual, double precioNuevo) throws InstrumentoNoEncontradoException {
        return this.instrumentosFinancierosRepository.editarPrecioInstrumento(nombreActual, precioNuevo);
    }

    public void eliminarInstrumentoFinanciero(String nombre) throws InstrumentoNoEncontradoException {
        this.instrumentosFinancierosRepository.eliminarInstrumentoFinanciero(nombre);
    }

    public InstrumentoFinanciero registrarInstrumentoFinanciero(String nombre, Double precio, LocalDate fechaDeEmision, TipoInstrumentoFinanciero tipo) throws InstrumentoDuplicadoException, NoExisteEseTipoDeInstrumentoException, InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumentoFinanciero = this.instrumentosFinancierosRepository.consultarPorUnInstrumentoFinanciero(nombre);
        if(instrumentoFinanciero != null) {
            throw new InstrumentoDuplicadoException("No se puede registrar el instrumento debido a que este ya fue registrado en el sistema con anterioridad.");
        } else {
            switch (tipo) {
                case BONO:
                    BonoFactory bonoFactory = new BonoFactory();
                    instrumentoFinanciero = bonoFactory.createInstrumentoFinanciero(nombre, precio, fechaDeEmision);
                    break;
                case ACCION:
                    AccionFactory accionFactory = new AccionFactory();
                    instrumentoFinanciero = accionFactory.createInstrumentoFinanciero(nombre, precio, fechaDeEmision);
                    break;
                default:
                    throw new NoExisteEseTipoDeInstrumentoException("El tipo ingresado no corresponde a un tipo de instrumento conocido.");
            }
            return this.instrumentosFinancierosRepository.crearInstrumentoFinanciero(instrumentoFinanciero);
        }
    }
}
