package main.java.model.gestorInstrumentosFinancieros;

import exceptions.InstrumentoDuplicadoException;
import exceptions.InstrumentoNoEncontradoException;
import main.java.model.exceptions.NoExisteEseTipoDeInstrumentoException;
import main.java.model.instrumentoFinanciero.InstrumentoFinanciero;
import main.java.model.instrumentoFinanciero.TipoInstrumentoFinanciero;
import main.java.model.instrumentoFinanciero.factoryInstrumentos.AccionFactory;
import main.java.model.instrumentoFinanciero.factoryInstrumentos.BonoFactory;

import java.time.LocalDate;
import java.util.ArrayList;

// SINGLETON
public class GestorInstrumentosFinancieros {
    private static GestorInstrumentosFinancieros instance;
    ArrayList<InstrumentoFinanciero> instrumentosFinancieros;

    private GestorInstrumentosFinancieros() {
        instrumentosFinancieros = new ArrayList<InstrumentoFinanciero>();
    }

    public static GestorInstrumentosFinancieros getInstance() {
        if (instance == null) {
            instance = new GestorInstrumentosFinancieros();
        }
        return instance;
    }

    public ArrayList<InstrumentoFinanciero> consultarInstrumentosFinancieros() {
        return this.instrumentosFinancieros;
    }

    public InstrumentoFinanciero consultarPorUnInstrumentoFinanciero(String nombre) throws InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumentoFinanciero = this.instrumentosFinancieros.stream().filter(x -> x.getNombre().equals(nombre)).findFirst().orElse(null);
        if (this.existeInstrumentoFinanciero(nombre)) {
            return instrumentoFinanciero;
        } else {
            throw new InstrumentoNoEncontradoException("El instrumento financiero no se encuentra en el sistema");
        }
    }

    public Boolean existeInstrumentoFinanciero(String nombre)  {
        Boolean existeInstrumentoFinanciero = this.instrumentosFinancieros.stream().filter(x -> x.getNombre().equals(nombre)).findFirst().orElse(null) != null;
        return existeInstrumentoFinanciero;
    }

    public void editarNombreInstrumentoFinanciero(String nombreActual, String nombreNuevo) throws InstrumentoNoEncontradoException {
        if(this.existeInstrumentoFinanciero(nombreActual)) {
            InstrumentoFinanciero instrumentoFinanciero = this.consultarPorUnInstrumentoFinanciero(nombreActual);
            instrumentoFinanciero.setNombre(nombreNuevo);
        }
    }

    public void editarPrecioInstrumentoFinanciero(String nombre, double precioActual) {
        if(this.existeInstrumentoFinanciero(nombre)) {
            //InstrumentoFinanciero instrumentoFinanciero = this.consultarPorUnInstrumentoFinanciero(nombre);
            //instrumentoFinanciero.setPrecio(precioActual);
            // TODO: resolver catcheo de error
        }
    }

    public void eliminarInstrumentoFinanciero(String nombre) throws InstrumentoNoEncontradoException {
        if(this.existeInstrumentoFinanciero(nombre)) {
            this.instrumentosFinancieros.removeIf(x -> x.getNombre().equals(nombre));
        } else {
            throw new InstrumentoNoEncontradoException("No se puede eliminar el instrumento financiero porque no existe en el sistema");
        }
    }

    public void registrarInstrumentoFinanciero(String nombre, Double precio, LocalDate fechaDeEmision, TipoInstrumentoFinanciero tipo) throws InstrumentoDuplicadoException, NoExisteEseTipoDeInstrumentoException {
        InstrumentoFinanciero instrumentoFinanciero = null;
        if(this.existeInstrumentoFinanciero(nombre)) {
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

            if (instrumentoFinanciero != null) {
                this.instrumentosFinancieros.add(instrumentoFinanciero);
            }
        }
    }
}
