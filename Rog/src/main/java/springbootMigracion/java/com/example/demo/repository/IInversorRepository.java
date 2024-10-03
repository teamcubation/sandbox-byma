package springbootMigracion.java.com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootMigracion.java.com.example.demo.model.Inversor;

import java.util.List;
import java.util.Optional;

@Repository
public interface IInversorRepository extends JpaRepository<Inversor, Long> {

    boolean existsByNombreIgnoreCase(String nombre);
    List<Inversor> findAllByNombreContainingIgnoreCase(String nombre);
    Optional<Inversor> findByNombreIgnoreCase(String nombre);

}
