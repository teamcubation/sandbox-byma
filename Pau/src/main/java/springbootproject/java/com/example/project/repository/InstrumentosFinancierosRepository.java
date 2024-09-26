package springbootproject.java.com.example.project.repository;

import org.springframework.stereotype.Repository;
import springbootproject.java.com.example.project.exceptions.InstrumentoNoEncontradoException;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.InstrumentoFinanciero;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.factoryInstrumentos.AccionFactory;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.factoryInstrumentos.BonoFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InstrumentosFinancierosRepository {
    private List<InstrumentoFinanciero> instrumentosFinancieros;

    private InstrumentosFinancierosRepository() {
        instrumentosFinancieros = new ArrayList<InstrumentoFinanciero>();
        AccionFactory accionFactory = new AccionFactory();
        BonoFactory bonoFactory = new BonoFactory();

        this.instrumentosFinancieros.add(accionFactory.createInstrumentoFinanciero("BYMA",741.0, LocalDate.of(2010,10,10)));
        this.instrumentosFinancieros.add(bonoFactory.createInstrumentoFinanciero("AGRO",852.0, LocalDate.of(2011,11,11)));
    }

    public List<InstrumentoFinanciero> consultarInstrumentosFinancieros() {
        return this.instrumentosFinancieros;
    }

    public InstrumentoFinanciero consultarPorUnInstrumentoFinanciero(String nombre)  {
        return  this.instrumentosFinancieros.stream().filter(x -> x.getNombre().equals(nombre)).findFirst().orElse(null);
    }

    public InstrumentoFinanciero crearInstrumentoFinanciero(InstrumentoFinanciero instrumentoFinanciero) {
        this.instrumentosFinancieros.add(instrumentoFinanciero);
        return instrumentoFinanciero;
    }

    public void eliminarInstrumentoFinanciero(String nombre) throws InstrumentoNoEncontradoException {
        if(!this.instrumentosFinancieros.removeIf(x -> x.getNombre().equals(nombre))) {
            throw new InstrumentoNoEncontradoException("No se puede eliminar el instrumento financiero porque no existe en el sistema");
        }
    }

    public InstrumentoFinanciero editarInstrumentoFinanciero(String nombreActual, String nuevoNombre, Double nuevoPrecio, LocalDate nuevaFechaDeEmision) {
        InstrumentoFinanciero instrumento = consultarPorUnInstrumentoFinanciero(nombreActual);
        if (nuevoNombre != null) {
            instrumento.setNombre(nuevoNombre);
        }
        if (nuevoPrecio != null) {
            instrumento.setPrecio(nuevoPrecio);
        }
        if (nuevaFechaDeEmision != null) {
            instrumento.setFechaDeEmision(nuevaFechaDeEmision);
        }
        return instrumento;
    }

    // TODO: obtener bonos y acciones
}
