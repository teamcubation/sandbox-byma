package org.example.ejercicioGestionAccionesYBonos.service;

import org.example.ejercicioGestionAccionesYBonos.modelo.InstrumentoFinanciero;
import org.example.ejercicioGestionAccionesYBonos.repo.InstrumentoFinancieroRepositoryImp;
import org.example.ejercicioGestionAccionesYBonos.exception.InstrumentoDuplicadoException;
import org.example.ejercicioGestionAccionesYBonos.exception.InstrumentoNoEncontradoException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class InstrumentoFinancieroServicio {
    private InstrumentoFinancieroRepositoryImp instrumentoFinancieroRepositoryImp;

    private static InstrumentoFinancieroServicio instancia;

    public InstrumentoFinancieroServicio(InstrumentoFinancieroRepositoryImp instrumentoFinancieroRepositoryImp) {
        this.instrumentoFinancieroRepositoryImp = instrumentoFinancieroRepositoryImp;
    }

    public static InstrumentoFinancieroServicio getInstancia(InstrumentoFinancieroRepositoryImp instrumentoFinancieroRepositoryImp) {
        if (instancia == null) {
            return new InstrumentoFinancieroServicio(instrumentoFinancieroRepositoryImp);
        }

        return instancia;
    }

    public void registrarNuevoInstrumento(InstrumentoFinanciero nuevoInstrumento) throws InstrumentoDuplicadoException {
        if (!nombreInstrumentoEsInexistente(nuevoInstrumento.getNombre())) {
            throw new InstrumentoDuplicadoException("Error: Ya existe un instrumento con ese nombre!!!. Por favor, pruebe ingresando un nombre distinto.");
        }
        this.instrumentoFinancieroRepositoryImp.registrarNuevoInstrumento(nuevoInstrumento);
    }

    public ArrayList<InstrumentoFinanciero> listarTodosLosInstrumentosFinancieros() {
        return this.instrumentoFinancieroRepositoryImp
                .listarBonosYAcciones()
                .stream()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<InstrumentoFinanciero> listarAcciones() {
        return this.instrumentoFinancieroRepositoryImp
                .consultarAcciones()
                .stream()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<InstrumentoFinanciero> listarBonos() {
        return this.instrumentoFinancieroRepositoryImp
                .consultarBonos()
                .stream()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Optional<InstrumentoFinanciero> listarInstrumentoPorNombre(String nombreInstrumento) throws InstrumentoNoEncontradoException {

        if (nombreInstrumentoEsInexistente(nombreInstrumento)) {
            throw new InstrumentoNoEncontradoException("Error: No se encontro un instrumento con el nombre ingresado");
        }
        return this.instrumentoFinancieroRepositoryImp
                .listarInstrumentoPorNombre(nombreInstrumento);

    }


    public void editarInstrumento(String nuevoNombre, double nuevoPrecio, String nombreInstrumento) throws InstrumentoNoEncontradoException {
        if (nombreInstrumentoEsInexistente(nombreInstrumento)) {
            throw new InstrumentoNoEncontradoException("Error: No se encontro un instrumento con el nombre ingresado");
        }

        this.instrumentoFinancieroRepositoryImp.editarInstrumento(nuevoNombre, nuevoPrecio, nombreInstrumento);
    }

    public void editarNombreInstrumento(String nuevoNombre, String nombreInstrumento) throws InstrumentoNoEncontradoException {
        if (nombreInstrumentoEsInexistente(nombreInstrumento)) {
            throw new InstrumentoNoEncontradoException("Error: No se encontro un instrumento con el nombre ingresado");
        }
        this.instrumentoFinancieroRepositoryImp.editarNombreInstrumento(nuevoNombre, nombreInstrumento);
    }

    public void editarPrecioInstrumento(double nuevoPrecio, String nombreInstrumento) throws InstrumentoNoEncontradoException {
        if (nombreInstrumentoEsInexistente(nombreInstrumento)) {
            throw new InstrumentoNoEncontradoException("Error: No se encontro un instrumento con el nombre ingresado");
        }
        this.instrumentoFinancieroRepositoryImp.editarPrecioInstrumento(nuevoPrecio, nombreInstrumento);
    }

    public void eliminarInstrumento(String nombreInstrumento) throws InstrumentoNoEncontradoException {
        if (nombreInstrumentoEsInexistente(nombreInstrumento)) {
            throw new InstrumentoNoEncontradoException("Error: No se encontro un instrumento con el nombre ingresado");
        }

        this.instrumentoFinancieroRepositoryImp.eliminarInstrumento(nombreInstrumento);
    }


    //Validaciones

    private boolean nombreInstrumentoEsInexistente(String nombreInstrumento) {
        return this.instrumentoFinancieroRepositoryImp
                .listarBonosYAcciones()
                .stream()
                .noneMatch(instrumentoFinanciero -> instrumentoFinanciero.getNombre().equalsIgnoreCase(nombreInstrumento));
    }


}
