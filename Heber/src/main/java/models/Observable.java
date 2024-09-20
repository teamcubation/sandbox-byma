package models;

public interface Observable {
    void agregarObservador(Observer observador);
    void eliminarObservador(Observer observador);
    void notificarObservadores();
}