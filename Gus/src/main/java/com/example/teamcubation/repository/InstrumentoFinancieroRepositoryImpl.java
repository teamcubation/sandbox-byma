package com.example.teamcubation.repository;


import com.example.teamcubation.model.InstrumentoFinanciero;
import com.example.teamcubation.model.instrumentoEnums.TipoInstrumentoFinanciero;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InstrumentoFinancieroRepositoryImpl implements InstrumentoFinancieroRepository {
    private ArrayList<InstrumentoFinanciero> listaDeInstrumentos = new ArrayList<>();

    private static InstrumentoFinanciero getInstrumentoFinanciero(double nuevoPrecio, String nombreInstrumento, InstrumentoFinanciero instrumentoFinanciero) {
        if (instrumentoFinanciero.getNombre().equalsIgnoreCase(nombreInstrumento)) {
            instrumentoFinanciero.setPrecio(nuevoPrecio);
        }
        return instrumentoFinanciero;
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
    public void registrarNuevoInstrumento(InstrumentoFinanciero nuevoInstrumento) {
        this.listaDeInstrumentos.add(nuevoInstrumento);
    }

    @Override
    public void editarInstrumento(String nuevoNombre, double nuevoPrecio, String nombreInstrumento) {
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
    public void editarNombreInstrumento(String nuevoNombre, String nombreInstrumento) {
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
    public void editarPrecioInstrumento(double nuevoPrecio, String nombreInstrumento) {
        this.listaDeInstrumentos = this.listaDeInstrumentos
                .stream()
                .map(instrumentoFinanciero -> {
                    return getInstrumentoFinanciero(nuevoPrecio, nombreInstrumento, instrumentoFinanciero);
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void eliminarInstrumento(String nombreInstrumento) {
        this.listaDeInstrumentos = this.listaDeInstrumentos
                .stream()
                .filter(instrumentoFinanciero -> !instrumentoFinanciero.getNombre().equalsIgnoreCase(nombreInstrumento))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
