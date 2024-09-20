package consoleversion.repository;

import consoleversion.exceptions.InstrumentoNoEncontradoException;
import consoleversion.model.instrumentoFinanciero.InstrumentoFinanciero;

import java.util.ArrayList;

public class InstrumentosFinancierosRepository {
    private static InstrumentosFinancierosRepository instance;
    ArrayList<InstrumentoFinanciero> instrumentosFinancieros;

    private InstrumentosFinancierosRepository() {
        instrumentosFinancieros = new ArrayList<InstrumentoFinanciero>();
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

    public InstrumentoFinanciero consultarPorUnInstrumentoFinanciero(String nombre) throws InstrumentoNoEncontradoException {
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
        } // Editar el instrumento encontrado
        instrumento.setNombre(nombreNuevo);
        return instrumento;
    }

    public InstrumentoFinanciero editarPrecioInstrumento(String nombreActual, double precioNuevo) throws InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumento = consultarPorUnInstrumentoFinanciero(nombreActual);
        if (instrumento == null) {
            throw new InstrumentoNoEncontradoException("El instrumento con nombre " + nombreActual + " no fue encontrado.");
        } // Editar el instrumento encontrado
        instrumento.setPrecio(precioNuevo);
        return instrumento;
    }

    public void eliminarInstrumentoFinanciero(String nombre) throws InstrumentoNoEncontradoException {
        if(!this.instrumentosFinancieros.removeIf(x -> x.getNombre().equals(nombre))) {
            throw new InstrumentoNoEncontradoException("No se puede eliminar el instrumento financiero porque no existe en el sistema");
        }
    }



    // TODO: obtener bonos y acciones


}
