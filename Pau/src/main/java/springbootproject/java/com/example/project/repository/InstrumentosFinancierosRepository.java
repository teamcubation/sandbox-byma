package springbootproject.java.com.example.project.repository;

import org.springframework.stereotype.Repository;
import springbootproject.java.com.example.project.exceptions.InstrumentoNoEncontradoException;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.InstrumentoFinanciero;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.factoryInstrumentos.AccionFactory;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.factoryInstrumentos.BonoFactory;

import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public class InstrumentosFinancierosRepository {
    private static InstrumentosFinancierosRepository instance;
    ArrayList<InstrumentoFinanciero> instrumentosFinancieros;

    private InstrumentosFinancierosRepository() {
        instrumentosFinancieros = new ArrayList<InstrumentoFinanciero>();
        AccionFactory accionFactory = new AccionFactory();
        BonoFactory bonoFactory = new BonoFactory();

        this.instrumentosFinancieros.add(accionFactory.createInstrumentoFinanciero("BYMA",741.0, LocalDate.of(2010,10,10)));
        this.instrumentosFinancieros.add(bonoFactory.createInstrumentoFinanciero("AGRO",852.0, LocalDate.of(2011,11,11)));
    }
    public static InstrumentosFinancierosRepository getInstance() {
        if (instance == null) {
            instance = new InstrumentosFinancierosRepository();
        }
        return instance;
    }

    public ArrayList<InstrumentoFinanciero> consultarInstrumentosFinancieros() {
        return this.instrumentosFinancieros;
    }

    public InstrumentoFinanciero consultarPorUnInstrumentoFinanciero(String nombre)  {
        return  this.instrumentosFinancieros.stream().filter(x -> x.getNombre().equals(nombre)).findFirst().orElse(null);
    }

    public InstrumentoFinanciero crearInstrumentoFinanciero(InstrumentoFinanciero instrumentoFinanciero) {
        this.instrumentosFinancieros.add(instrumentoFinanciero);
        return instrumentoFinanciero;
    }

    public InstrumentoFinanciero editarNombreInstrumento(String nombreActual, String nombreNuevo) throws InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumento = consultarPorUnInstrumentoFinanciero(nombreActual);
        if (instrumento == null) {
            throw new InstrumentoNoEncontradoException("El instrumento con nombre " + nombreActual + " no fue encontrado.");
        } else {
            instrumento.setNombre(nombreNuevo);
            return instrumento;
        }

    }

    public InstrumentoFinanciero editarPrecioInstrumento(String nombreActual, double precioNuevo) throws InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumento = consultarPorUnInstrumentoFinanciero(nombreActual);
        if (instrumento == null) {
            throw new InstrumentoNoEncontradoException("El instrumento con nombre " + nombreActual + " no fue encontrado.");
        } else {
            instrumento.setPrecio(precioNuevo);
            return instrumento;
        }

    }

    public void eliminarInstrumentoFinanciero(String nombre) throws InstrumentoNoEncontradoException {
        if(!this.instrumentosFinancieros.removeIf(x -> x.getNombre().equals(nombre))) {
            throw new InstrumentoNoEncontradoException("No se puede eliminar el instrumento financiero porque no existe en el sistema");
        }
    }



    // TODO: obtener bonos y acciones


}
