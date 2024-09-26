package springbootproject.java.com.example.project.repository;

import org.springframework.stereotype.Repository;
import springbootproject.java.com.example.project.exceptions.InversorNoEncontradoException;
import springbootproject.java.com.example.project.model.Inversor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InversorRepository {
    private List<Inversor> inversores;

    private InversorRepository() {
        inversores = new ArrayList<Inversor>();
    }

    public List<Inversor> obtenerInversores() {
        return this.inversores;
    }

    public List<Inversor> obtenerInversoresSuscriptos() {
        return this.inversores.stream().filter(inversor -> inversor.esSuscriptor()).collect(Collectors.toList());
    }

    public Inversor agregarInversor(Inversor inversor) {
        this.inversores.add(inversor);
        return inversor;
    }

    public void eliminarInversor(Inversor inversor) {
        this.inversores.remove(inversor);
    }

    public void suscribirInversor(String nombreInversor) throws InversorNoEncontradoException {
        Inversor inversor = this.buscarInversor(nombreInversor);
        if (inversor == null) {
            throw new InversorNoEncontradoException("El inversor con nombre " + nombreInversor + " no fue encontrado.");
        } // Editar el inversor
        inversor.setEsSuscriptor(true);
    }

    public void desuscribirInversor(String nombreInversor) throws InversorNoEncontradoException {
        Inversor inversor = this.buscarInversor(nombreInversor);
        if (inversor == null) {
            throw new InversorNoEncontradoException("El inversor con nombre " + nombreInversor + " no fue encontrado.");
        }
        inversor.setEsSuscriptor(false);
    }

    public Inversor buscarInversor(String nombre) {
        return  this.obtenerInversores().stream().filter(x -> x.getNombre().equals(nombre)).findFirst().orElse(null);
    }
}
