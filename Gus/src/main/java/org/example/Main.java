package org.example;

import org.example.ejercicioGestionAccionesYBonos.app.AppBonosYAcciones;
import org.example.ejercicioGestionAccionesYBonos.controller.InstrumentoFinancieroController;
import org.example.ejercicioGestionAccionesYBonos.modelo.Inversor;
import org.example.ejercicioGestionAccionesYBonos.repo.InstrumentoFinancieroObservableImp;
import org.example.ejercicioGestionAccionesYBonos.repo.InstrumentoFinancieroRepositoryImp;
import org.example.ejercicioGestionAccionesYBonos.service.InstrumentoFinancieroServicio;
import org.example.ejercicioGestionAccionesYBonos.service.NotificacionInstrumentoServicio;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        System.out.println("Hola soy gus estoy en mi rama!!!");

        Inversor inversor1 = new Inversor("Pepito");
        Inversor inversor2 = new Inversor("Juan");

//        AplicacionAccionesYBonos app = AplicacionAccionesYBonos.obtenerInstancia();
//        app.registrarObservador(inversor1);
//        app.registrarObservador(inversor2);
//
//        app.iniciar();

        InstrumentoFinancieroRepositoryImp repository = InstrumentoFinancieroRepositoryImp.getInstancia();
        InstrumentoFinancieroObservableImp observableRepository = InstrumentoFinancieroObservableImp.getInstancia();

        InstrumentoFinancieroServicio servicio = InstrumentoFinancieroServicio.getInstancia(repository);
        NotificacionInstrumentoServicio servicioObservable = NotificacionInstrumentoServicio.getInstancia(observableRepository);

        InstrumentoFinancieroController controller = InstrumentoFinancieroController.getInstancia(servicio, servicioObservable);

        controller.registrarObservador(inversor1);
        controller.registrarObservador(inversor2);

        AppBonosYAcciones app = AppBonosYAcciones.getInstancia(controller);

        app.iniciarAplicacion();
    }
}