package org.example.service;

public interface INotificadorService {
    void agregarObservador(String nombreInstrumento, IObserver observador);
    void eliminarObservador(String nombreInstrumento, IObserver observador);
    void notificarObservadores(String nombreInstrumento, double nuevoPrecio);
}
