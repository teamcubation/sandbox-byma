package model;

import model.instrumentoFinanciero.InstrumentoFinanciero;
import service.observer.Notificador;
import service.observer.Observer;

public class Inversor implements Observer {
    private String nombre;

    public Inversor(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void update(InstrumentoFinanciero instrumentoFinanciero, String atributo) {
        System.out.println("Se actualizo el atributo " + atributo + " del instrumentoFinanciero " + instrumentoFinanciero.toString());
    }

    public void suscribirseANotificaciones() {
        Notificador.getInstance().suscribirInversor(this);
    }

    public void desuscribirseNotificaciones() {
        Notificador.getInstance().desuscribirInversor(this);
    }
}
