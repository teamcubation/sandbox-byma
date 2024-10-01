package springbootMigracion.java.com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootMigracion.java.com.example.demo.model.InstrumentoFinanciero;

import java.util.List;
import java.util.Optional;

@Repository
public interface IInstrumentoFinancieroRepository extends JpaRepository<InstrumentoFinanciero, Long> {

    boolean existsByNombreIgnoreCase(String nombre);
    List<InstrumentoFinanciero> findAllByNombreContainingIgnoreCase(String nombre);
    Optional<InstrumentoFinanciero> findByNombreIgnoreCase(String nombre);

}
