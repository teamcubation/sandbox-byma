package springbootMigracion.java.com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootMigracion.java.com.example.demo.model.InstrumentoFinanciero;

import java.util.Optional;

@Repository
public interface IInstrumentoFinancieroRepository extends JpaRepository<InstrumentoFinanciero, Long> {
//    void agregarInstrumento(InstrumentoFinanciero instrumento);
//    List<InstrumentoFinanciero> listarTodosLosInstrumentos();
    Optional<InstrumentoFinanciero> findByNombreIgnoreCase(String nombre);
//    void eliminarInstrumento(String nombre);
//    void editarInstrumento(String nombre, InstrumentoFinanciero nuevoInstrumento);
}
