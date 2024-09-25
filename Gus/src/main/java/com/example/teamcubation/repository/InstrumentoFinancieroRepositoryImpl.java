package com.example.teamcubation.repository;


import com.example.teamcubation.model.InstrumentoFinanciero;
import com.example.teamcubation.model.instrumentoEnums.TipoInstrumentoFinanciero;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InstrumentoFinancieroRepositoryImpl implements InstrumentoFinancieroRepository {
    private List<InstrumentoFinanciero> listaDeInstrumentos = new ArrayList<>();

//    private InstrumentoFinanciero getInstrumentoFinanciero(EditarInstrumentoDTO instrumentoDTO, InstrumentoFinanciero instrumentoFinanciero) {
//        if (instrumentoFinanciero.getNombre().equalsIgnoreCase(instrumentoDTO.getNombreInstrumento())) {
//            instrumentoFinanciero.setPrecio(instrumentoDTO.getNuevoPrecio());
//        }
//        return instrumentoFinanciero;
//    }

    @Override
    public List<InstrumentoFinanciero> getAll() {
        return this.listaDeInstrumentos;
    }

    @Override
    public List<InstrumentoFinanciero> getBonos() {
        return this.listaDeInstrumentos.stream().filter(i -> i.getTipo().equals(TipoInstrumentoFinanciero.BONO)).toList();
    }

    @Override
    public List<InstrumentoFinanciero> getAcciones() {
        return this.listaDeInstrumentos.stream().filter(i -> i.getTipo().equals(TipoInstrumentoFinanciero.ACCION)).toList();
    }

    @Override
    public Optional<InstrumentoFinanciero> getByName(String nombreInstrumento) {
        return this.listaDeInstrumentos
                .stream()
                .filter(instrumentoFinanciero -> instrumentoFinanciero.getNombre().equalsIgnoreCase(nombreInstrumento))
                .findFirst();
    }

    @Override
    public void create(InstrumentoFinanciero nuevoInstrumento) {
        this.listaDeInstrumentos.add(nuevoInstrumento);
    }

    @Override
    public void update(InstrumentoFinanciero instrumentoActualizado, String nombreInstrumeto) {


        this.listaDeInstrumentos = listaDeInstrumentos
                .stream()
                .map(instrumentoFinanciero -> {
                    if (instrumentoFinanciero.getNombre().equalsIgnoreCase(nombreInstrumeto)) {
                        return instrumentoActualizado;
                    }
                    return instrumentoFinanciero;
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void delete(String nombreInstrumento) {
        this.listaDeInstrumentos = this.listaDeInstrumentos
                .stream()
                .filter(instrumentoFinanciero -> !instrumentoFinanciero.getNombre().equalsIgnoreCase(nombreInstrumento))
                .collect(Collectors.toCollection(ArrayList::new));
    }

//    @Override
//    public void updateName(EditarInstrumentoDTO instrumentoDTO) {
//        this.listaDeInstrumentos = this.listaDeInstrumentos
//                .stream()
//                .map(instrumentoFinanciero -> {
//                    if (instrumentoFinanciero.getNombre().equalsIgnoreCase(instrumentoDTO.getNombreInstrumento())) {
//                        instrumentoFinanciero.setNombre(instrumentoDTO.getNuevoNombre());
//                    }
//                    return instrumentoFinanciero;
//                })
//                .collect(Collectors.toCollection(ArrayList::new));
//    }
//
//    @Override
//    public void updatePrice(EditarInstrumentoDTO instrumentoDTO) {
//        this.listaDeInstrumentos = this.listaDeInstrumentos
//                .stream()
//                .map(instrumentoFinanciero -> getInstrumentoFinanciero(instrumentoDTO, instrumentoFinanciero))
//                .collect(Collectors.toCollection(ArrayList::new));
//    }


}
