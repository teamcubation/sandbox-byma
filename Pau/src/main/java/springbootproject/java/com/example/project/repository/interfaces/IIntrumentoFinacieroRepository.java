package com.example.project.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.project.model.instrumentoFinanciero.InstrumentoFinanciero;
import org.springframework.stereotype.Repository;

@Repository
public interface IIntrumentoFinacieroRepository extends JpaRepository<InstrumentoFinanciero, Long> {
    InstrumentoFinanciero save(InstrumentoFinanciero instrumentoFinanciero);

    void delete(InstrumentoFinanciero instrumentoFinanciero);

}
