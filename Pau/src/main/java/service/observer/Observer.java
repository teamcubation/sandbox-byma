package service.observer;

import model.instrumentoFinanciero.InstrumentoFinanciero;

import java.util.Observable;

public interface Observer {
    void update(InstrumentoFinanciero instrumentoFinanciero, String atributo);
}
