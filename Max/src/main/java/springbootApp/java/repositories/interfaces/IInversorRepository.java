package springbootApp.java.repositories.interfaces;

import springbootApp.java.models.Inversor;

import java.util.List;

public interface IInversorRepository {

    public void registrarInversor(Inversor inversor);

    public void borrarInversor(Inversor inversor);

    public Inversor buscarInversor(String dni);

    public List<Inversor> consultarTodosLosInversores();
}
