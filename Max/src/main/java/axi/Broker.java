package axi;

import axi.excepciones.InstrumentoDuplicadoException;
import axi.excepciones.InstrumentoNoEncontradoException;
import axi.excepciones.InversorNoEncontradoException;
import axi.modelos.InstrumentoFinanciero;
import axi.modelos.InstrumentoFinancieroFactory;
import axi.modelos.Inversor;
import axi.modelos.Tipo;
import axi.repositories.InstrumenroFinancieroRepository;
import axi.repositories.InversorRepository;

import java.util.ArrayList;

public class Broker {
    private static Broker broker;
    private String name;
    private InversorRepository inversores;
    private InstrumenroFinancieroRepository instrumentos;

    private Broker() {
        this.name = "AXI";
        this.inversores = InversorRepository.getInversorRepository();
        this.instrumentos = InstrumenroFinancieroRepository.getInstrumentoFinancieroRepository();
    }

    public static Broker getBroker() {
        if (broker == null)
            broker = new Broker();
        return broker;
    }


    public void registrarInstrumento(String nombre, double precio, Tipo tipo) {
        InstrumentoFinanciero instrumento = instrumentos.buscarInstrumento(nombre);
        if (instrumento != null) {
            throw new InstrumentoDuplicadoException("Error. Accion existente");
        } else {
            this.instrumentos.registrarInstrumento(
                    InstrumentoFinancieroFactory.nuevoInstrumento(nombre, precio, tipo));
        }
    }


    public void consultarTodosLosInstrumentos() {
        instrumentos.consultarTodosLosInstrumentos();
    }

    public void modificarInstrumento(String variable, String modificacion, String nombre) {
        InstrumentoFinanciero instrumento = instrumentos.buscarInstrumento(nombre);
        if (instrumento != null) {
            switch (variable) {
                case "1":
                    instrumento.setNombre(modificacion);
                    break;
                case "2":
                    if (modificacion.equals("1")) {
                        instrumento.setTipo(Tipo.ACCION);
                    } else if (modificacion.equals("2")) {
                        instrumento.setTipo(Tipo.BONO);
                    } else {
                        throw new IllegalArgumentException("Error. Tipo incorrecto");
                    }
                    break;
                case "3":
                    try {
                        double numero = Double.parseDouble(modificacion);
                        instrumento.setPrecio(numero);
                    } catch (NumberFormatException e) {
                        System.out.println("Error de formato: ingrese un número.");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Error. los campos no coinciden");
            }
            instrumentos.modificarInstrumento(instrumento);
        } else {
            throw new InversorNoEncontradoException("Error. Instrumento no encontrado");
        }
    }

    public void eliminarInstrumento(String nombre) {
        InstrumentoFinanciero instrumento = instrumentos.buscarInstrumento(nombre);
        if (instrumento == null) {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }

        instrumentos.eliminarInstrumento(instrumento);
    }


//    -----------------------------------------------------------------------------
//    -----------------------------------------------------------------------------
//    -----------------------------------------------------------------------------
//    -----------------------------------------------------------------------------

    public void registrarInversor(String nombre, String dni) {
        Inversor inversor = inversores.buscarInversor(dni);
        if (inversor != null)
            throw new RuntimeException("Error. Inversor existente");
        inversores.registrarInversor(new Inversor(nombre, dni));
    }

    public void metodoParaSuscribirse(String dni, String nombreInstrumento) {
        InstrumentoFinanciero instrumento = instrumentos.buscarInstrumento(nombreInstrumento);
        if (instrumento == null) {
            throw new InversorNoEncontradoException("Error. instrumento no existente");
        }
        Inversor inversor = inversores.buscarInversor(dni);
        if (inversor == null) {
            throw new InversorNoEncontradoException("Error. inversor no existente");
        }
        instrumento.suscribirse(inversor);
    }

    public void metodoParaDesuscribirse(String dni, String nombreInstrumento) {
        InstrumentoFinanciero instrumento = instrumentos.buscarInstrumento(nombreInstrumento);
        if (instrumento == null) {
            throw new InversorNoEncontradoException("Error. instrumento no existente");
        }
        Inversor inversor = inversores.buscarInversor(dni);
        if (inversor == null) {
            throw new InversorNoEncontradoException("Error. inversor no existente");
        }
        instrumento.desuscribirse(inversor);
    }

    public static void notificarCambioDePrecio(ArrayList<Inversor> inversoresANotificar,
                                               InstrumentoFinanciero instrumento) {
        for (Inversor inversor : inversoresANotificar) {
            inversor.actualizar(instrumento);
        }
    }

    public void consultarInstrumentosDeInversor(String dni) {
        Inversor inversor = inversores.buscarInversor(dni);
        if (inversor == null) {
            throw new InversorNoEncontradoException("Error. Inversor no encontrado");
        }
        inversor.consultarInstrumentos();
    }
}
