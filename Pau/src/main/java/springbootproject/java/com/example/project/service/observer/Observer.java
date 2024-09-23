package springbootproject.java.com.example.project.service.observer;

import springbootproject.java.com.example.project.model.instrumentoFinanciero.InstrumentoFinanciero;

public interface Observer {
    void update(InstrumentoFinanciero instrumentoFinanciero, String atributo);
}
