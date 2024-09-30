package springApp.java.com.example.gestoralyc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springApp.java.com.example.gestoralyc.models.AccionModel;

public interface AccionRepository extends JpaRepository<AccionModel, Long> {


    boolean existsByNombre(String nombre);
    boolean existsByNombreIgnoreCase(String nombre);
}
