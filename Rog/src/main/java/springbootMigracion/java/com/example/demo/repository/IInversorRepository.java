package springbootMigracion.java.com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootMigracion.java.com.example.demo.model.Inversor;

import java.util.Optional;

@Repository
public interface IInversorRepository extends JpaRepository<Inversor, Long> {
//    void agregarInversor(Inversor inversor);
//    List<Inversor> listarTodosLosInversores();
    Optional<Inversor> findByNombreIgnoreCase(String nombre);
//    void eliminarInversor(String nombre);
//    void editarInversor(String nombre, Inversor nuevoInversor);

}
