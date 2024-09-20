package org.example.service;

import java.util.*;

public class NotificadorService implements INotificadorService {
    private final Map<String, List<IObserver>> observadoresPorInstrumento = new HashMap<>();
    private static NotificadorService notificadorService;

    private NotificadorService(){};

    public static NotificadorService getInstance(){
        if (notificadorService == null){
            notificadorService = new NotificadorService();
        }
        return notificadorService;
    }

    @Override
    public void agregarObservador(String nombreInstrumento, IObserver observador) {
        observadoresPorInstrumento.computeIfAbsent(nombreInstrumento, k -> new ArrayList<>()).add(observador);
    }

    @Override
    public void eliminarObservador(String nombreInstrumento, IObserver observador) {
        Optional.ofNullable(observadoresPorInstrumento.get(nombreInstrumento))
                .ifPresent(observadores -> observadores.remove(observador));
    }

    @Override
    public void notificarObservadores(String nombreInstrumento, double nuevoPrecio) {
//        List<IObserver> observadores = observadoresPorInstrumento.get(nombreInstrumento);
//        if (observadores != null) {
//            for (IObserver observador : observadores) {
//                observador.actualizar(nombreInstrumento, nuevoPrecio);
//            }
//        }
        Optional.ofNullable(observadoresPorInstrumento.get(nombreInstrumento))
                .ifPresent(observadores -> {
                    for (IObserver observador : observadores) {
                        observador.actualizar(nombreInstrumento, nuevoPrecio);
                    }
                });
    }
}
