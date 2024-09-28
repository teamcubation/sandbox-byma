package com.example.project.service.observer;

import com.example.project.model.instrumentoFinanciero.InstrumentoFinanciero;

public interface Observer {
    void update(InstrumentoFinanciero instrumentoFinanciero, String atributo);
}
