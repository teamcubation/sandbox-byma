package springbootproject.java.com.example.project.model;

import springbootproject.java.com.example.project.exceptions.InversorNoEncontradoException;
import springbootproject.java.com.example.project.model.instrumentoFinanciero.InstrumentoFinanciero;
import springbootproject.java.com.example.project.service.observer.Notificador;
import springbootproject.java.com.example.project.service.observer.Observer;

public class Inversor implements Observer {
    private String nombre;
    private boolean esSuscriptor;

    public Inversor(String nombre) {
        this.nombre = nombre;
        this.esSuscriptor = true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean esSuscriptor() {
        return esSuscriptor;
    }

    public void setEsSuscriptor(boolean esSuscriptor) {
        this.esSuscriptor = esSuscriptor;
    }

    public void suscribirseANotificaciones() throws InversorNoEncontradoException {
        Notificador.getInstance().suscribirInversor(this.getNombre());
    }

    public void desuscribirseANotificaciones() throws InversorNoEncontradoException {
        Notificador.getInstance().desuscribirInversor(this.getNombre());
    }

    @Override
    public void update(InstrumentoFinanciero instrumentoFinanciero, String atributo) {
        System.out.println("Se actualizo el atributo " + atributo + " del instrumentoFinanciero " + instrumentoFinanciero.toString() +
                "\n Soy inversor " + this.getNombre() + " y fui notificado.");
    }

    public String toString() {
        return "Inversor{nombre= " + this.getNombre() + ", esSuscriptor= " + esSuscriptor + "}";
    }
}
