package org.example.ejercicioGestionAccionesYBonos.app;

import org.example.ejercicioGestionAccionesYBonos.controller.InstrumentoFinancieroController;

public class AppBonosYAcciones {

    private InstrumentoFinancieroController controller;

    private static AppBonosYAcciones instancia;


    private AppBonosYAcciones(InstrumentoFinancieroController controller) {
        this.controller = controller;
    }


    public static AppBonosYAcciones getInstancia(InstrumentoFinancieroController controller) {
        if (instancia == null) {
            return new AppBonosYAcciones(controller);
        }

        return instancia;
    }

    public void iniciarAplicacion() {
        this.controller.iniciarApp();
    }


}
