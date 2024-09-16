package org.example;

import org.example.ejercicioGestionAccionesYBonos.app.AplicacionAccionesYBonos;
import org.example.ejercicioGestionAccionesYBonos.modelo.Inversor;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        System.out.println("Hola soy gus estoy en mi rama!!!");

        Inversor inversor1 = new Inversor("Pepito");
        Inversor inversor2 = new Inversor("Juan");

        AplicacionAccionesYBonos app = AplicacionAccionesYBonos.obtenerInstancia();
        app.registrarObservador(inversor1);
        app.registrarObservador(inversor2);

        app.iniciar();
    }
}