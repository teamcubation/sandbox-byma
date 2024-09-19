package axi.repositories;

import axi.modelos.InstrumentoFinanciero;
import axi.modelos.Inversor;

import java.util.ArrayList;

public class InversorRepository {

    private static InversorRepository inversorRepository;
    private ArrayList<Inversor> inversores;
    private InversorRepository() {
        this.inversores = new ArrayList<>();
    }
    public static InversorRepository getInversorRepository() {
        if (inversorRepository == null) {
            inversorRepository = new InversorRepository();
        }
        return inversorRepository;
    }

    public void registrarInversor(Inversor inversor) {
        this.inversores.add(inversor);
    }
    public void borrarInversor(Inversor inversor) {
        this.inversores.remove(inversor);
    }

    public Inversor buscarInversor(String dni) {
        for (Inversor inversor : this.inversores) {
            if (inversor.getDni().equals(dni)) {
                return inversor;
            }
        }
        return null;
    }
}
