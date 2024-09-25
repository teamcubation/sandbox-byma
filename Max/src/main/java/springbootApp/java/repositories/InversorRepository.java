package springbootApp.java.repositories;

import org.springframework.stereotype.Repository;
import springbootApp.java.models.Inversor;
import springbootApp.java.models.InversorDTO;

import java.util.ArrayList;

@Repository
public class InversorRepository {
    private static InversorRepository inversorRepository;
    private final ArrayList<Inversor> inversoresList;
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

    public ArrayList<Inversor> consultarTodosLosInversores() {
        return inversoresList;
    }
}
