package org.example.ejercicioGestionAccionesYBonos.modelo;

import org.example.ejercicioGestionAccionesYBonos.repo.InstrumentoFinancieroObserver;

public class Inversor implements InstrumentoFinancieroObserver {

    String nombre;

    public Inversor(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(InstrumentoFinanciero instrumentoFinanciero) {
        System.out.println("se actualizo el instrumento: " + instrumentoFinanciero.mostrarInstrumento());

    }
}
