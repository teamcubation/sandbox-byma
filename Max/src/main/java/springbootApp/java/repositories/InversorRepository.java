package springbootApp.java.repositories;

import org.springframework.stereotype.Repository;
import springbootApp.java.models.Inversor;
import springbootApp.java.models.InversorDTO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InversorRepository {
    private final List<Inversor> inversoresList = new ArrayList<>();


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

    public List<Inversor> consultarTodosLosInversores() {
        return inversoresList;
    }
}
