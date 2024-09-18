package Observer;

import Modelo.InstrumentoFinanciero;

import java.util.ArrayList;
import java.util.List;

public class Notificador {
    private double estado;
    List<Observer> observadores = new ArrayList<>();

    private void notificarObservadores(InstrumentoFinanciero instrumentoFinanciero) {
        for (Observer observador : observadores) {
            observador.actualizar(getEstado(), instrumentoFinanciero);
        }
    }

    public double getEstado() {
        return estado;
    }

    public void setEstado(double estado, InstrumentoFinanciero instrumentoFinanciero) {
        this.estado = estado;
        notificarObservadores(instrumentoFinanciero);
    }

    public void agregarSuscriptor(Observer observador) {
        observadores.add(observador);
    }

    public void eliminarSuscriptor(Observer observador) {
        observadores.remove(observador);
    }
}
