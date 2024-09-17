package axi.modelos;

import axi.Tipo;

public class Accion extends InstrumentoFinanciero {


    public Accion(String nombre, double precio) {

        super(nombre, precio, Tipo.ACCION);
    }
}
