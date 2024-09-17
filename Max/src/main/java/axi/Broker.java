package axi;

import axi.excepciones.InstrumentoDuplicadoException;
import axi.excepciones.InstrumentoNoEncontradoException;
import axi.modelos.InstrumentoFinanciero;
import axi.modelos.InstrumentoFinancieroFactory;
import axi.modelos.Inversor;
import axi.modelos.Tipo;

import java.util.ArrayList;

public class Broker {
    final static String NAME = "AXI";
    private static Broker broker;
    private String name;
    private ArrayList<InstrumentoFinanciero> instrumentos;
    private ArrayList<Inversor>inversores;

    private Broker() {
        this.name = NAME;
        this.instrumentos = new ArrayList<>();
        this.inversores = new ArrayList<>();
    }

    public static Broker getBroker() {
        if (broker == null)
            broker = new Broker();
        return broker;
    }
    public void registrarInversor(String nombre, String dni){
        Inversor inversor = this.buscarInversor(dni);
        if (inversor != null)
            throw new RuntimeException("Error. Inversor existente");
        inversor = new Inversor(nombre, dni);
        this.inversores.add(inversor);
    }

    private Inversor buscarInversor(String dni) {
        Inversor inversor = null;
        for (int j = 0; j < inversores.size(); j++) {
            if (inversores.get(j).getDni().equals(dni))
                inversor = inversores.get(j);
        }
        return inversor;
    }

    public void registrarInstrumento(String nombre, double precio, Tipo tipo) {
        InstrumentoFinanciero instrumento = buscarInstrumento(nombre);
        if (instrumento != null) {
            throw new InstrumentoDuplicadoException("Error. Accion existente");
        } else {
            this.instrumentos.add(
                    InstrumentoFinancieroFactory.nuevoInstrumento(nombre, precio, tipo));
        }
    }

    private InstrumentoFinanciero buscarInstrumento(String nombre) {
        InstrumentoFinanciero instrumento = null;
        for (int j = 0; j < instrumentos.size(); j++) {
            if (instrumentos.get(j).getNombre().equals(nombre))
                instrumento = instrumentos.get(j);
        }
        return instrumento;
    }


    public void consultar() {
        if (!this.instrumentos.isEmpty()) {
            for (InstrumentoFinanciero instrumento : instrumentos) {
                System.out.println(instrumento);
            }
        }
    }

    public void modificar(String variable, String modificacion, String nombre) {
        InstrumentoFinanciero instrumento = this.buscarInstrumento(nombre);
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
        }
    }

    public void eliminar(String nombre) {
        InstrumentoFinanciero instrumento = instrumentos.stream()
                .filter(e -> e.getNombre().equals(nombre))
                .findFirst().orElse(null);
        if (instrumento == null) {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }
        instrumentos.remove(instrumento);
    }
}
