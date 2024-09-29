package com.example.proyectoSpringBoot.service.observer;

import com.example.proyectoSpringBoot.model.InstrumentoFinanciero;

public interface Observer {
    void actualizar(double estado, InstrumentoFinanciero instrumentoFinanciero);
}
