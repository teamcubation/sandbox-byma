package service.observer;


import model.Inversor;
import model.instrumentoFinanciero.InstrumentoFinanciero;

import java.util.ArrayList;

public class Notificador {

    private static Notificador instance;
    ArrayList<Inversor> instrumentosFinancieros;

    private Notificador() {
        instrumentosFinancieros = new ArrayList<Inversor>();
    }

    public static Notificador getInstance() {
        if (instance == null) {
            instance = new Notificador();
        }
        return instance;
    }

    public ArrayList<Inversor> getInstrumentosFinancieros() {
        return instrumentosFinancieros;
    }

    public void suscribirInversor(Inversor inversor) {
        instrumentosFinancieros.add(inversor);
    }

    public void desuscribirInversor(Inversor inversor) {
        instrumentosFinancieros.remove(inversor);
    }

    public void notificarInteresados(InstrumentoFinanciero instrumentoFinanciero, String atributo) {
       this.getInstrumentosFinancieros().stream().forEach(inversor -> inversor.update(instrumentoFinanciero, atributo));
    }
}
