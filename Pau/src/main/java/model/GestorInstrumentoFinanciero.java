package main.java.model;

import main.java.model.exceptions.NoExisteEseTipoDeInstrumentoException;
import main.java.model.instrumentoFinanciero.InstrumentoFinanciero;
import main.java.model.instrumentoFinanciero.factoryInstrumentos.AccionFactory;
import main.java.model.instrumentoFinanciero.factoryInstrumentos.BonoFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class GestorInstrumentoFinanciero {
    private static GestorInstrumentoFinanciero instance;
    ArrayList<InstrumentoFinanciero> instrumentosFinancieros;


    private GestorInstrumentoFinanciero() {
        instrumentosFinancieros = new ArrayList<InstrumentoFinanciero>();
    }

    public static GestorInstrumentoFinanciero getInstance() {
        if (instance == null) {
            instance = new GestorInstrumentoFinanciero();
        }
        return instance;
    }

    public void registrar(String nombre, Double precio, LocalDate fechaDeEmision, String tipo) {
        InstrumentoFinanciero instrumentoFinanciero = null;
        try {

            if(this.existeInstrumentoFinanciero(nombre)) {
                throw new exceptions.InstrumentoDuplicadoException("No se puede registrar el instrumento debido a que este ya fue registrado en el sistema con anterioridad.");
            }
            if (tipo.matches("[Bb]ono")) {
                BonoFactory bonoFactory = new BonoFactory();
                instrumentoFinanciero = bonoFactory.createInstrumentoFinanciero(nombre, precio, fechaDeEmision);
            } else if (tipo.matches("[Aa]cci[oó]n")) {
                AccionFactory accionFactory = new AccionFactory();
                instrumentoFinanciero = accionFactory.createInstrumentoFinanciero(nombre, precio, fechaDeEmision);
            } else {
                /* TODO :  throw new NoExisteEseTipoDeInstrumentoException("El tipo ingresado no corresponde a un tipo de instrumento conocido.");*/
            }
        } catch (exceptions.InstrumentoDuplicadoException e) {
            System.out.println(e.getMessage());
        }
        if (instrumentoFinanciero != null) {
            instrumentosFinancieros.add(instrumentoFinanciero);
        }
    }

    public ArrayList<InstrumentoFinanciero> consultarInstrumentosFinancieros() {
        return this.instrumentosFinancieros;
    }

    public InstrumentoFinanciero consultarInstrumentoFinanciero(String nombre) {
        InstrumentoFinanciero instrumentoFinanciero = null;

        try {
            instrumentoFinanciero = this.buscarInstrumento(nombre);
        } catch (exceptions.InstrumentoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
        return instrumentoFinanciero;
    }

    public void editarNombreInstrumento(InstrumentoFinanciero instrumentoFinanciero, String nuevoValor) {
        if (this.instrumentosFinancieros.contains(instrumentoFinanciero)) {
            instrumentoFinanciero.setNombre(nuevoValor);
        }
    }

    // Ver, si se puede no retornar el null??
    public InstrumentoFinanciero buscarInstrumento(String nombre) throws exceptions.InstrumentoNoEncontradoException {
        InstrumentoFinanciero instrumentoFinanciero = this.instrumentosFinancieros.stream().filter(x -> x.getNombre().equals(nombre)).findFirst().orElse(null);
        if (this.existeInstrumentoFinanciero(nombre)) {
            return instrumentoFinanciero;
        } else {
            throw new exceptions.InstrumentoNoEncontradoException("El instrumento financiero no se encuentra en el sistema");
        }
    }

    public Boolean existeInstrumentoFinanciero(String nombre)  {
        Boolean existeInstrumentoFinanciero = !this.instrumentosFinancieros.stream().filter(x -> x.getNombre().equals(nombre)).findFirst().orElse(null).equals(null);
        // Si no lo encuentra la funcion retorna, bool == True. Si lo encuentra bool = false
        return existeInstrumentoFinanciero;
    }

    public void editarPrecioInstrumento(InstrumentoFinanciero instrumentoFinanciero, Double precio) {
        if(this.instrumentosFinancieros.contains(instrumentoFinanciero)) {
            instrumentoFinanciero.setPrecio(precio);
        }
    }

    public void eliminarInstrumento(String nombre) {
        try {
            if(this.existeInstrumentoFinanciero(nombre)) {
                this.instrumentosFinancieros.remove(this.buscarInstrumento(nombre));
            } else {
                throw new exceptions.InstrumentoNoEncontradoException("No se puede eliminar el instrumento financiero porque no existe en el sistema");
            }
        } catch (exceptions.InstrumentoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
}
