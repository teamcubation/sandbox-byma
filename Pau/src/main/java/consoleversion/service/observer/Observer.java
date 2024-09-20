package consoleversion.service.observer;

import consoleversion.model.instrumentoFinanciero.InstrumentoFinanciero;

public interface Observer {
    void update(InstrumentoFinanciero instrumentoFinanciero, String atributo);
}
