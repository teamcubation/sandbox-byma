package springBootProject.java.com.example.proyectoSpringBoot.service.observer;

import springBootProject.java.com.example.proyectoSpringBoot.model.InstrumentoFinanciero;

public interface Observer {
    void actualizar(double estado, InstrumentoFinanciero instrumentoFinanciero);
}
