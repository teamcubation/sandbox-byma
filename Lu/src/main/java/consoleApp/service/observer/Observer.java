package consoleApp.service.observer;

import consoleApp.modelo.InstrumentoFinanciero;

public interface Observer {
    void actualizar(double estado, InstrumentoFinanciero instrumentoFinanciero);
}
