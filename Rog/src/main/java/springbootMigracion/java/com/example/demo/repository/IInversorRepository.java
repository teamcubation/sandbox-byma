package springbootMigracion.java.com.example.demo.repository;

import springbootMigracion.java.com.example.demo.model.Inversor;

import java.util.List;
import java.util.Optional;

public interface IInversorRepository {
    void agregarInversor(Inversor inversor);
    List<Inversor> listarTodosLosInversores();
    Optional<Inversor> buscarInversorPorNombre(String nombre);
    void eliminarInversor(String nombre);
    void editarInversor(String nombre, Inversor nuevoInversor);

}
