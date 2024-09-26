package springBootProject.java.com.example.proyectoSpringBoot.service.observer;

import springBootProject.java.com.example.proyectoSpringBoot.model.InstrumentoFinanciero;

public class Inversor implements Observer {
    @Override
    public void actualizar(double estado, InstrumentoFinanciero instrumentoFinanciero) {
        System.out.println("Se actualizo el valor del instrumento financiero: " + instrumentoFinanciero + "su nuevo valor es: " + estado);
    }
}
