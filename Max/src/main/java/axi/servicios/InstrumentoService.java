package axi.servicios;

import axi.excepciones.InstrumentoDuplicadoException;
import axi.excepciones.InstrumentoNoEncontradoException;
import axi.excepciones.InversorNoEncontradoException;
import axi.modelos.InstrumentoFinanciero;
import axi.modelos.InstrumentoFinancieroFactory;
import axi.modelos.Tipo;
import axi.repositories.InstrumenroFinancieroRepository;

public class InstrumentoService {
    private static InstrumentoService instrumentoService;
    private InstrumenroFinancieroRepository instrumentosRepository;

    private InstrumentoService() {
        this.instrumentosRepository = InstrumenroFinancieroRepository.getInstrumentoFinancieroRepository();
    }

    public static InstrumentoService getBroker() {
        if (instrumentoService == null)
            instrumentoService = new InstrumentoService();
        return instrumentoService;
    }

    public void registrarInstrumento(String nombre, double precio, Tipo tipo) {
        InstrumentoFinanciero instrumento = instrumentosRepository.buscarInstrumento(nombre);
        if (instrumento != null) {
            throw new InstrumentoDuplicadoException("Error. Accion existente");
        } else {
            this.instrumentosRepository.registrarInstrumento(
                    InstrumentoFinancieroFactory.nuevoInstrumento(nombre, precio, tipo));
        }
    }

    public void consultarTodosLosInstrumentos() {
        for (InstrumentoFinanciero instrumento : instrumentosRepository.consultarTodosLosInstrumentos()) {
            System.out.println(instrumento);
        }
    }

    public void modificarInstrumento(String variable, String modificacion, String nombre) {
        InstrumentoFinanciero instrumento = instrumentosRepository.buscarInstrumento(nombre);
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
        } else {
            throw new InversorNoEncontradoException("Error. Instrumento no encontrado");
        }
    }

    public void eliminarInstrumento(String nombre) {
        InstrumentoFinanciero instrumento = instrumentosRepository.buscarInstrumento(nombre);
        if (instrumento == null) {
            throw new InstrumentoNoEncontradoException("Error. Instrumento no encontrado");
        }
        instrumentosRepository.eliminarInstrumento(instrumento);
    }
}
