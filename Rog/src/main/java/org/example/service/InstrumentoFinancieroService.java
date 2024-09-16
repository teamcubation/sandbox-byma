package org.example.service;

import org.example.exception.InstrumentoNoEncontradoException;
import org.example.exception.InstrumentoDuplicadoException;
import org.example.model.Bono;
import org.example.model.InstrumentoFinanciero;
import org.example.repository.InstrumentoFinancieroRepository;

import java.util.List;

public class InstrumentoFinancieroService implements IInstrumentoFinancieroService {
    private final InstrumentoFinancieroRepository repository = InstrumentoFinancieroRepository.getInstance();

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
                instrumento.setNombre((String) nuevoValor);
                break;
            case "precio":
                instrumento.setPrecio(Double.parseDouble(nuevoValor.toString()));
                break;
            case "tasa de interes":
                if (instrumento instanceof Bono) {
                    ((Bono) instrumento).setTasaDeInteres(Double.parseDouble(nuevoValor.toString()));

                } else {
                    throw new IllegalArgumentException("El atributo 'tasa de interes' solo aplica a bonos.");
                }
                break;
            default:
                throw new IllegalArgumentException("Atributo no valido.");
        }
    }

//    public void registrarInstrumento(InstrumentoFinanciero instrumento) {
////        try {
//            repository.agregarInstrumento(instrumento);
////        } catch (InstrumentoDuplicadoException e) {
////            throw new RuntimeException("Error al registrar el instrumento: " + e.getMessage());
////        }
//    }
//
//    public List<InstrumentoFinanciero> listarTodosLosInstrumentos() {
//        return repository.listarTodosLosInstrumentos();
//    }
//
//    public InstrumentoFinanciero buscarInstrumentoPorNombre(String nombre) {
//        return repository.buscarInstrumentoPorNombre(nombre);
//    }
//
//    public void eliminarInstrumento(String nombre) {
//        try{
//            repository.eliminarInstrumento(nombre);
//        } catch (InstrumentoNoEncontradoException e){
//            throw new RuntimeException("Error al eliminar el instrumento: " + e.getMessage());
//        }
//    }
//
//    public void editarInstrumento(String nombre, String atributo, Object nuevoValor) {
////        try{
//            InstrumentoFinanciero instrumento = repository.buscarInstrumentoPorNombre(nombre);
//            if (instrumento == null) {
//                throw new InstrumentoNoEncontradoException("Instrumento no encontrado.");
//            }
//            switch (atributo.toLowerCase()) {
//                case "nombre":
//                    instrumento.setNombre((String) nuevoValor);
//                    break;
//                case "precio":
//                    instrumento.setPrecio(Double.parseDouble(nuevoValor.toString()));
//                    break;
//                case "tasa de interes":
//                    if (instrumento instanceof Bono) {
//                        ((Bono) instrumento).setTasaDeInteres(Double.parseDouble(nuevoValor.toString()));
//
//                    } else {
//                        throw new IllegalArgumentException("El atributo 'tasa de interes' solo aplica a bonos.");
//                    }
//                    break;
//                default:
//                    throw new IllegalArgumentException("Atributo no valido.");
//            }
////            repository.editarInstrumento(nombre, instrumento);
////        } catch (InstrumentoNoEncontradoException | IllegalArgumentException e) {
////            throw new RuntimeException("Error al editar el instrumento: " + e.getMessage());
////        }
//    }
}
