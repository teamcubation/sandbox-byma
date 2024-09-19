package org.example.service;

import java.util.ArrayList;
import java.util.List;

public class NotificadorService {
    private final List<IObserver> observadores = new ArrayList<>();

    public void agregarObservador(IObserver observador) {
        observadores.add(observador);
    }

    protected void notificarObservadores(String nombreInstrumento, double nuevoPrecio) {
        for (IObserver observador : observadores) {
            observador.actualizar(nombreInstrumento, nuevoPrecio);
        }
    }
}
