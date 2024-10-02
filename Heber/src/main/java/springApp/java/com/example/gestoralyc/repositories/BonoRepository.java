package springApp.java.com.example.gestoralyc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springApp.java.com.example.gestoralyc.models.BonoModel;

public interface BonoRepository extends JpaRepository<BonoModel, Long> {
    boolean existsByNombreIgnoreCase(String nombre);

    BonoModel findByNombre(String nombre);
}
