package axi.repositories;

import axi.modelos.Inversor;

import java.util.ArrayList;

public class InversorRepository {

    private static InversorRepository inversorRepository;
    private ArrayList<Inversor> inversoresList;
    private InversorRepository() {
        this.inversoresList = new ArrayList<>();
    }
    public static InversorRepository getInversorRepository() {
        if (inversorRepository == null) {
            inversorRepository = new InversorRepository();
        }
        return inversorRepository;
    }

    public void registrarInversor(Inversor inversor) {
        this.inversoresList.add(inversor);
    }
    public void borrarInversor(Inversor inversor) {
        this.inversoresList.remove(inversor);
    }

    public Inversor buscarInversor(String dni) {
        for (Inversor inversor : this.inversoresList) {
            if (inversor.getDni().equals(dni)) {
                return inversor;
            }
        }
        return null;
    }
}
