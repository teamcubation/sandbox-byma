package com.example.proyectoSpringBoot.observer;

import com.example.proyectoSpringBoot.model.InstrumentoFinanciero;

public interface Observer {
    void actualizar(double estado, InstrumentoFinanciero instrumentoFinanciero);
}
