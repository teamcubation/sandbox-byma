package springbootMigracion.java.com.example.demo.service;

import springbootMigracion.java.com.example.demo.dto.InversorDTO;
import springbootMigracion.java.com.example.demo.model.Inversor;

import java.util.List;

public interface IInversorService {
    Inversor registrarInversor(InversorDTO inversorDTO) throws Exception;
    Inversor buscarInversorPorId(Long id) throws Exception;
    List<Inversor> listarTodosLosInversores();
    List<Inversor> listarInversoresPorNombre(String nombre);
    void eliminarInversor(Long id) throws Exception;
    Inversor editarInversor(Long id, InversorDTO inversorDTO) throws Exception;

}
