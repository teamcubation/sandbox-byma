package springbootMigracion.java.com.example.demo.repository;

import org.springframework.stereotype.Repository;
import springbootMigracion.java.com.example.demo.exception.InstrumentoNoEncontradoException;
import springbootMigracion.java.com.example.demo.exception.InversorDuplicadoException;
import springbootMigracion.java.com.example.demo.exception.InversorNoEncontradoException;
import springbootMigracion.java.com.example.demo.model.Inversor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InversorRepository implements IInversorRepository {

    private final List<Inversor> inversorList = new ArrayList<>();

    @Override
    public void agregarInversor(Inversor inversor) {
        if (buscarInversorPorNombre(inversor.getNombre()).isPresent()){
            throw new InversorDuplicadoException("EL inversor ya existe.");
        }
        inversorList.add(inversor);
    }

    @Override
    public List<Inversor> listarTodosLosInversores() {
        return inversorList;
    }

    @Override
    public Optional<Inversor> buscarInversorPorNombre(String nombre) {
        return inversorList.stream()
                .filter(inversor -> inversor.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    @Override
    public void eliminarInversor(String nombre) {
        Optional<Inversor> insversorExistente = buscarInversorPorNombre(nombre);
        if (insversorExistente.isEmpty()){
            throw new InversorNoEncontradoException("Inversor no econtrado.");
        }
        Inversor inversor = insversorExistente.get();
        inversorList.remove(inversor);
    }

    @Override
    public void editarInversor(String nombre, Inversor nuevoInversor) {
        Inversor insversorExistente = buscarInversorPorNombre(nombre)
                .orElseThrow(() -> new InversorNoEncontradoException("Inversor no encontrado."));
        inversorList.remove(insversorExistente);
        inversorList.add(nuevoInversor);
    }

}
