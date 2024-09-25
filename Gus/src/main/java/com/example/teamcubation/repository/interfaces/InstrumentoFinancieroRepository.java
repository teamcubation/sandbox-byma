package com.example.teamcubation.repository.interfaces;


import com.example.teamcubation.model.InstrumentoFinanciero;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstrumentoFinancieroRepository {
    List<InstrumentoFinanciero> getAll();

    List<InstrumentoFinanciero> getBonos();

    List<InstrumentoFinanciero> getAcciones();

    Optional<InstrumentoFinanciero> getByName(String nombreInstrumento);

    void create(com.example.teamcubation.model.InstrumentoFinanciero nuevoInstrumento);

    void update(InstrumentoFinanciero instrumentoActualizado, String nombreInstrumento);

//    void updateName(EditarInstrumentoDTO instrumentoDTO);
//
//    void updatePrice(EditarInstrumentoDTO instrumentoDTO);

    void delete(String nombreInstrumento);

}
