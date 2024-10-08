package consoleversion.repository;

import consoleversion.exceptions.InversorNoEncontradoException;
import consoleversion.model.Inversor;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class InversorRepository {
    private static InversorRepository instance;
    ArrayList<Inversor> inversores;

    private InversorRepository() {
        inversores = new ArrayList<Inversor>();
    }

    public static InversorRepository getInstance() {
        if (instance == null) {
            instance = new InversorRepository();
        }
        return instance;
    }

    public ArrayList<Inversor> obtenerInversores() {
        return this.inversores;
    }

    public ArrayList<Inversor> obtenerInversoresSuscriptos() {
        return (ArrayList<Inversor>) this.inversores.stream().filter(inversor -> inversor.esSuscriptor()).collect(Collectors.toList());
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
