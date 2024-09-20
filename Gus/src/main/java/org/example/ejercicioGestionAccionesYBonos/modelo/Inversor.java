package org.example.ejercicioGestionAccionesYBonos.modelo;

import org.example.ejercicioGestionAccionesYBonos.repo.InstrumentoFinancieroObserver;

public class Inversor implements InstrumentoFinancieroObserver {

    String nombre;

    public Inversor(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(InstrumentoFinanciero instrumentoFinanciero) {
        System.out.println(this.nombre + "!!!, se actualizo el precio del instrumento: " + instrumentoFinanciero.mostrarInstrumento());

    }
}
