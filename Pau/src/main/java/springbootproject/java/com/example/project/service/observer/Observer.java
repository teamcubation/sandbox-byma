package com.example.project.service.observer;

import com.example.project.model.instrumentofinanciero.InstrumentoFinanciero;

public interface Observer {
    void update(InstrumentoFinanciero instrumentoFinanciero, String atributo);
}
