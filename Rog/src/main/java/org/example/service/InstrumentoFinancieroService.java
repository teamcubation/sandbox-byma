package org.example.service;

import org.example.exception.InstrumentoNoEncontradoException;
import org.example.model.Bono;
import org.example.model.InstrumentoFinanciero;
import org.example.repository.InstrumentoFinancieroRepository;

import java.util.List;

public class InstrumentoFinancieroService implements IInstrumentoFinancieroService {
    private final InstrumentoFinancieroRepository repository = InstrumentoFinancieroRepository.getInstance();
    private final NotificadorService notificadorService = NotificadorService.getInstance();

    @Override
    public void registrarInstrumento(InstrumentoFinanciero instrumento) {
        repository.agregarInstrumento(instrumento);
    }

    @Override
    public void eliminarInstrumento(String nombre) {
        repository.eliminarInstrumento(nombre);
    }

    @Override
    public List<InstrumentoFinanciero> listarTodosLosInstrumentos() {
        return repository.listarTodosLosInstrumentos();
    }

    @Override
    public InstrumentoFinanciero buscarInstrumentoPorNombre(String nombre) {
        return repository.buscarInstrumentoPorNombre(nombre);
    }

    @Override
    public void editarInstrumento(String nombre, String atributo, String nuevoValor) {
        InstrumentoFinanciero instrumento = repository.buscarInstrumentoPorNombre(nombre);
        if (instrumento == null) {
            throw new InstrumentoNoEncontradoException("Instrumento no encontrado.");
        }
        switch (atributo.toLowerCase()) {
            case "nombre":
                instrumento.setNombre(nuevoValor);
                break;
            case "precio":
                instrumento.setPrecio(Double.parseDouble(nuevoValor));
                this.notificadorService.notificarObservadores(nombre, Double.parseDouble(nuevoValor));
                break;
            case "tasa de interes":
                if (instrumento instanceof Bono) {
                    ((Bono) instrumento).setTasaDeInteres(Double.parseDouble(nuevoValor));

                } else {
                    throw new IllegalArgumentException("El atributo 'tasa de interes' solo aplica a bonos.");
                }
                break;
            default:
                throw new IllegalArgumentException("Atributo no valido.");
        }
    }

}
