package org.example.ejercicioGestionAccionesYBonos.repo;

import org.example.ejercicioGestionAccionesYBonos.modelo.InstrumentoFinanciero;
import org.example.ejercicioGestionAccionesYBonos.exception.InstrumentoDuplicadoException;
import org.example.ejercicioGestionAccionesYBonos.exception.InstrumentoNoEncontradoException;
import org.example.ejercicioGestionAccionesYBonos.util.TipoInstrumentoFinanciero;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InstrumentoFinancieroRepositoryImp implements InstrumentoFinancieroRepository {

    private ArrayList<InstrumentoFinanciero> listaDeInstrumentos;

    private static InstrumentoFinancieroRepositoryImp instancia;

    private InstrumentoFinancieroRepositoryImp() {
        this.listaDeInstrumentos = new ArrayList<>();
    }

    public static InstrumentoFinancieroRepositoryImp getInstancia() {
        if (instancia == null){
            return new InstrumentoFinancieroRepositoryImp();
        }

        return instancia;
    }

    @Override
    public List<InstrumentoFinanciero> listarBonosYAcciones() {
        return this.listaDeInstrumentos;
    }

    @Override
    public List<InstrumentoFinanciero> consultarBonos() {
        return this.listaDeInstrumentos.stream().filter(i -> i.getTipo().equals(TipoInstrumentoFinanciero.BONO)).toList();
    }

    @Override
    public List<InstrumentoFinanciero> consultarAcciones() {
        return this.listaDeInstrumentos.stream().filter(i -> i.getTipo().equals(TipoInstrumentoFinanciero.ACCION)).toList();
    }

    @Override
    public Optional<InstrumentoFinanciero> listarInstrumentoPorNombre(String nombreInstrumento) {
        return this.listaDeInstrumentos
                .stream()
                .filter(instrumentoFinanciero -> instrumentoFinanciero.getNombre().equalsIgnoreCase(nombreInstrumento))
                .findFirst();
    }

    @Override
    public void registrarNuevoInstrumento(InstrumentoFinanciero nuevoInstrumento) throws InstrumentoDuplicadoException {
        this.listaDeInstrumentos.add(nuevoInstrumento);
    }

    @Override
    public void editarInstrumento(String nuevoNombre, double nuevoPrecio, String nombreInstrumento) throws InstrumentoNoEncontradoException {
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
        this.listaDeInstrumentos = this.listaDeInstrumentos
                .stream()
                .map(instrumentoFinanciero -> {
                    return getInstrumentoFinanciero(nuevoPrecio, nombreInstrumento, instrumentoFinanciero);
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static InstrumentoFinanciero getInstrumentoFinanciero(double nuevoPrecio, String nombreInstrumento, InstrumentoFinanciero instrumentoFinanciero) {
        if (instrumentoFinanciero.getNombre().equalsIgnoreCase(nombreInstrumento)) {
            instrumentoFinanciero.setPrecio(nuevoPrecio);
        }
        return instrumentoFinanciero;
    }

    @Override
    public void eliminarInstrumento(String nombreInstrumento) throws InstrumentoNoEncontradoException {
        this.listaDeInstrumentos = this.listaDeInstrumentos
                .stream()
                .filter(instrumentoFinanciero -> !instrumentoFinanciero.getNombre().equalsIgnoreCase(nombreInstrumento))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
