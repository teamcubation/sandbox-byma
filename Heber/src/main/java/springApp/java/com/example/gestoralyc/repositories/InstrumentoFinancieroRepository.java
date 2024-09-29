package springApp.java.com.example.gestoralyc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springApp.java.com.example.gestoralyc.models.InstrumentoFinancieroModel;
import java.util.List;

import java.util.Optional;

public interface InstrumentoFinancieroRepository extends JpaRepository<InstrumentoFinancieroModel, Long> {



}
