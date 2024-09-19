package model.gestorInstrumentosFinancieros;

import exceptions.InstrumentoDuplicadoException;
import exceptions.InstrumentoNoEncontradoException;
import exceptions.NoExisteEseTipoDeInstrumentoException;
import model.instrumentoFinanciero.InstrumentoFinanciero;
import model.instrumentoFinanciero.TipoInstrumentoFinanciero;
import model.instrumentoFinanciero.factoryInstrumentos.AccionFactory;
import model.instrumentoFinanciero.factoryInstrumentos.BonoFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

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

    public String consultarInstrumentosFinancierosToString() {
        String resultado = "";
        return this.instrumentosFinancieros.stream().map(x -> x.toString()).collect(Collectors.joining(" \n"));
    }

    public InstrumentoFinanciero consultarPorUnInstrumentoFinanciero(String nombre) throws InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumentoFinanciero = this.instrumentosFinancieros.stream().filter(x -> x.getNombre().equals(nombre)).findFirst().orElse(null);
        if (this.existeInstrumentoFinanciero(nombre)) {
            return instrumentoFinanciero;
        } else {
            throw new InstrumentoNoEncontradoException("El instrumento financiero no se encuentra en el sistema");
        }
    }

    public String consultarPorUnInstrumentoFinancieroToString(String nombre) throws InstrumentoNoEncontradoException {
        return this.consultarPorUnInstrumentoFinanciero(nombre).toString();
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

    public void editarPrecioInstrumentoFinanciero(String nombre, double precioActual) throws InstrumentoNoEncontradoException {
        if(this.existeInstrumentoFinanciero(nombre)) {
            InstrumentoFinanciero instrumentoFinanciero = this.consultarPorUnInstrumentoFinanciero(nombre);
            instrumentoFinanciero.setPrecio(precioActual);
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

//            if (instrumentoFinanciero != null) {
//                this.instrumentosFinancieros.add(instrumentoFinanciero);
//            }
        }
    }
}
