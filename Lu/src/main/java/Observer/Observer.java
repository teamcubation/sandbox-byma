package Observer;

import Modelo.InstrumentoFinanciero;

public interface Observer {
    void actualizar(double estado, InstrumentoFinanciero instrumentoFinanciero);
}
