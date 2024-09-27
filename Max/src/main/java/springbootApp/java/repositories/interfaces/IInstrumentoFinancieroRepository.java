package springbootApp.java.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootApp.java.models.InstrumentoFinanciero;

import java.util.List;

@Repository
public interface IInstrumentoFinancieroRepository extends JpaRepository<InstrumentoFinanciero, Long> {

//    public void registrarInstrumento(InstrumentoFinanciero instrumento);

    public InstrumentoFinanciero findByNombre(String nombre);

//    public void eliminarInstrumento(InstrumentoFinanciero instrumento);
//
//    public List<InstrumentoFinanciero> consultarTodosLosInstrumentos();
}