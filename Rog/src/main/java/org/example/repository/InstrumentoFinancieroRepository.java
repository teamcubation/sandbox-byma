package org.example.repository;

import org.example.exception.InstrumentoNoEncontradoException;
import org.example.exception.InstrumentoDuplicadoException;
import org.example.model.InstrumentoFinanciero;

import java.util.ArrayList;
import java.util.List;

public class InstrumentoFinancieroRepository implements IInstrumentoFinancieroRepository {

    private final List<InstrumentoFinanciero> instrumentoFinancieroList = new ArrayList<>();
    private static InstrumentoFinancieroRepository InstrumentoFinancieroRepository;

    private InstrumentoFinancieroRepository() {}

    public static InstrumentoFinancieroRepository getInstance() {
        if (InstrumentoFinancieroRepository == null) {
            InstrumentoFinancieroRepository = new InstrumentoFinancieroRepository();
        }
        return InstrumentoFinancieroRepository;
    }

    @Override
    public void agregarInstrumento(InstrumentoFinanciero instrumento) {
        if (buscarInstrumentoPorNombre(instrumento.getNombre()) != null){
            throw new InstrumentoDuplicadoException("EL instrumento ya existe.");
        }
        instrumentoFinancieroList.add(instrumento);
    }

    @Override
    public List<InstrumentoFinanciero> listarTodosLosInstrumentos() {
        return instrumentoFinancieroList;
    }

    @Override
    public InstrumentoFinanciero buscarInstrumentoPorNombre(String nombre) {
        return instrumentoFinancieroList.stream()
                .filter(instrumento -> instrumento.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void eliminarInstrumento(String nombre) {
        InstrumentoFinanciero instrumentoExistente = buscarInstrumentoPorNombre(nombre);
        if (instrumentoExistente == null){
            throw new InstrumentoNoEncontradoException("Instrumento no econtrado.");
        }
        instrumentoFinancieroList.remove(instrumentoExistente);
    }

//    public void agregarInstrumento(InstrumentoFinanciero instrumento) {
//        if (buscarInstrumentoPorNombre(instrumento.getNombre()) != null){
//            throw new InstrumentoDuplicadoException("EL instrumento ya existe.");
//        }
//        instrumentoFinancieroList.add(instrumento);
//    }

//    public List<InstrumentoFinanciero> listarTodosLosInstrumentos() {
//        return instrumentoFinancieroList;
//    }

//    public InstrumentoFinanciero buscarInstrumentoPorNombre(String nombre) {
////        for (InstrumentoFinanciero instrumento : instrumentoFinancieroList) {
////            if (instrumento.getNombre().equalsIgnoreCase(nombre)) {
////                return instrumento;
////            }
////        }
////        return null;
//
//        return instrumentoFinancieroList.stream()
//                .filter(instrumento -> instrumento.getNombre().equalsIgnoreCase(nombre))
//                .findFirst()
//                .orElse(null);
//    }
//
////    public void editarInstrumento(String nombre, InstrumentoFinanciero instrumentoActualizado) {
////        InstrumentoFinanciero instrumentoExistente = buscarInstrumentoPorNombre(nombre);
////        if (instrumentoExistente == null) {
////            throw new InstrumentoNoEncontradoException("Instrumento no encontrado.");
////        }
////        int index = instrumentoFinancieroList.indexOf(instrumentoExistente);
////        instrumentoFinancieroList.set(index, instrumentoActualizado);
////    }
//
//    public void eliminarInstrumento(String nombre) {
//        InstrumentoFinanciero instrumentoExistente = buscarInstrumentoPorNombre(nombre);
//        if (instrumentoExistente == null){
//            throw new InstrumentoNoEncontradoException("Instrumento no econtrado.");
//        }
//        instrumentoFinancieroList.remove(instrumentoExistente);
//    }
}
