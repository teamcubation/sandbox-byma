package service.observer;

import modelo.InstrumentoFinanciero;

public interface Observer {
    void actualizar(double estado, InstrumentoFinanciero instrumentoFinanciero);
}
