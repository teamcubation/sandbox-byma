package org.example.ejercicioGestionAccionesYBonos.service;

import org.example.ejercicioGestionAccionesYBonos.modelo.InstrumentoFinanciero;
import org.example.ejercicioGestionAccionesYBonos.repo.InstrumentoFinancieroObservable;
import org.example.ejercicioGestionAccionesYBonos.repo.InstrumentoFinancieroObserver;
import org.example.ejercicioGestionAccionesYBonos.repo.InstrumentoFinancieroRepository;
import org.example.ejercicioGestionAccionesYBonos.util.InstrumentoDuplicadoException;
import org.example.ejercicioGestionAccionesYBonos.util.InstrumentoNoEncontradoException;
import org.example.ejercicioGestionAccionesYBonos.util.TipoInstrumentoFinanciero;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InstrumentoFinancieroService implements InstrumentoFinancieroRepository, InstrumentoFinancieroObservable {

    private ArrayList<InstrumentoFinanciero> listaDeInstrumentos;

    private ArrayList<InstrumentoFinancieroObserver> listaDeInversores;

    private static InstrumentoFinancieroService instancia = null;

    private InstrumentoFinancieroService() {
        this.listaDeInstrumentos = new ArrayList<>();
        this.listaDeInversores = new ArrayList<>();
    }

    public static InstrumentoFinancieroService getInstancia() {
        if (instancia == null) {
            return new InstrumentoFinancieroService();
        }

        return instancia;
    }


    //Observer
    @Override
    public void registrarObservador(InstrumentoFinancieroObserver instrumentoFinancieroObserver) {
        this.listaDeInversores.add(instrumentoFinancieroObserver);

    }

    @Override
    public void eliminarObservador(InstrumentoFinancieroObserver instrumentoFinancieroObserver) {
        this.listaDeInversores.remove(instrumentoFinancieroObserver);

    }

    @Override
    public void notificarObservadores(InstrumentoFinanciero instrumentoFinanciero) {
        this.listaDeInversores.forEach(inversor -> inversor.actualizar(instrumentoFinanciero));

    }


    //CRUD repository
    @Override
    public List<InstrumentoFinanciero> listarBonosYAcciones() {
        return listaDeInstrumentos;
    }

    @Override
    public List<InstrumentoFinanciero> consultarBonos() {
        return listaDeInstrumentos
                .stream()
                .filter(instrumento -> instrumento.getTipo().equals(TipoInstrumentoFinanciero.BONO))
                .toList();
    }

    @Override
    public List<InstrumentoFinanciero> consultarAcciones() {
        return listaDeInstrumentos
                .stream()
                .filter(instrumentoFinanciero -> instrumentoFinanciero.getTipo().equals(TipoInstrumentoFinanciero.ACCION))
                .toList();
    }

    @Override
    public Optional<InstrumentoFinanciero> listarInstrumentoPorNombre(String nombreInstrumento) {
        return this.listaDeInstrumentos.stream().filter(i -> i.getNombre().equalsIgnoreCase(nombreInstrumento)).findFirst();
    }

    @Override
    public void registrarNuevoInstrumento(InstrumentoFinanciero nuevoInstrumento) throws InstrumentoDuplicadoException {


        if (this.listaDeInstrumentos
                .stream()
                .anyMatch(instrumentoFinanciero -> instrumentoFinanciero.getNombre().equalsIgnoreCase(nuevoInstrumento.getNombre()))) {
            throw new InstrumentoDuplicadoException("El instrumento ingresado ya existe!!! Probar con un nombre distinto");
        }

        listaDeInstrumentos.add(nuevoInstrumento);

    }

    @Override
    public void editarInstrumento(String nuevoNombre, double nuevoPrecio, String nombreInstrumento) throws InstrumentoNoEncontradoException {
        if (nombreInstrumentoEsInexistente(nombreInstrumento)) {
            throw new InstrumentoNoEncontradoException("Error: No se encontro instrumento con el nombre ingresado");
        }

        this.listaDeInstrumentos = listaDeInstrumentos
                .stream()
                .map(instrumentoFinanciero -> {
                    if (instrumentoFinanciero.getNombre().equalsIgnoreCase(nombreInstrumento)) {
                        instrumentoFinanciero.setNombre(nuevoNombre);
                        instrumentoFinanciero.setPrecio(nuevoPrecio);
                    }
                    return instrumentoFinanciero;
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void editarNombreInstrumento(String nuevoNombre, String nombreInstrumento) throws InstrumentoNoEncontradoException {

        if (nombreInstrumentoEsInexistente(nombreInstrumento)) {
            throw new InstrumentoNoEncontradoException("Error: No se encontro instrumento con el nombre ingresado");
        }

        this.listaDeInstrumentos = this.listaDeInstrumentos
                .stream()
                .map(instrumentoFinanciero -> {
                    if (instrumentoFinanciero.getNombre().equalsIgnoreCase(nombreInstrumento)) {
                        instrumentoFinanciero.setNombre(nuevoNombre);
                    }
                    return instrumentoFinanciero;
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void editarPrecioInstrumento(double nuevoPrecio, String nombreInstrumento) throws InstrumentoNoEncontradoException {
        if (nombreInstrumentoEsInexistente(nombreInstrumento)) {
            throw new InstrumentoNoEncontradoException("Error: No se encontro instrumento con el nombre ingresado");
        }

        this.listaDeInstrumentos = this.listaDeInstrumentos
                .stream()
                .map(instrumentoFinanciero -> {
                    if (instrumentoFinanciero.getNombre().equalsIgnoreCase(nombreInstrumento)) {
                        instrumentoFinanciero.setPrecio(nuevoPrecio);
                    }
                    return instrumentoFinanciero;
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void eliminarInstrumento(String nombreInstrumento) throws InstrumentoNoEncontradoException {
        if (nombreInstrumentoEsInexistente(nombreInstrumento)) {
            throw new InstrumentoNoEncontradoException("Error: No se encontro instrumento con el nombre ingresado");
        }

        this.listaDeInstrumentos = this.listaDeInstrumentos
                .stream()
                .filter(instrumentoFinanciero -> !instrumentoFinanciero.getNombre().equalsIgnoreCase(nombreInstrumento))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private boolean nombreInstrumentoEsInexistente(String nombreInstrumento) {
        return this.listaDeInstrumentos
                .stream()
                .noneMatch(instrumentoFinanciero -> instrumentoFinanciero.getNombre().equalsIgnoreCase(nombreInstrumento));
    }
}
