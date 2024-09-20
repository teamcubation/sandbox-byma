package service.observer;

import modelo.InstrumentoFinanciero;

public class Inversor implements Observer {

    @Override
    public void actualizar(double estado, InstrumentoFinanciero instrumentoFinanciero) {
        System.out.println("Se actualizo el valor del instrumento financiero: " + instrumentoFinanciero + "su nuevo valor es: " + estado);
    }
}
