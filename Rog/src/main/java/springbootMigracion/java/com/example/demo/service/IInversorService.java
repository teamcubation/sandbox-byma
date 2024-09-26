package springbootMigracion.java.com.example.demo.service;

import springbootMigracion.java.com.example.demo.dto.InversorDTO;
import springbootMigracion.java.com.example.demo.model.Inversor;

import java.util.List;
import java.util.Optional;

public interface IInversorService {
    void registrarInversor(InversorDTO inversorDTO);
    void eliminarInversor(String nombre);
    List<Inversor> listarTodosLosInversores();
    Optional<Inversor> buscarInversorPorNombre(String nombre);
    void editarInversor(String nombre, InversorDTO inversorDTO);

}
