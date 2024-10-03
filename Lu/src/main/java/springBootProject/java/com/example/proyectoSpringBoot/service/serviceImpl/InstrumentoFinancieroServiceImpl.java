package com.example.proyectoSpringBoot.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.proyectoSpringBoot.dto.InstrumentoFinancieroDTO;
import com.example.proyectoSpringBoot.excepciones.customExcepcions.InstrumentoDuplicadoException;
import com.example.proyectoSpringBoot.excepciones.customExcepcions.InstrumentoNoEncontradoException;
import com.example.proyectoSpringBoot.excepciones.customExcepcions.OpcionInvalidaException;
import com.example.proyectoSpringBoot.model.InstrumentoFinanciero;
import com.example.proyectoSpringBoot.repository.InstrumentoFinancieroRepository;
import com.example.proyectoSpringBoot.service.InstrumentoFinancieroServiceInterface;
import com.example.proyectoSpringBoot.utils.Validaciones;
import com.example.proyectoSpringBoot.dto.InstrumentoFinancieroMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.proyectoSpringBoot.utils.ExcepcionesMensajes.*;


@Service
public class InstrumentoFinancieroServiceImpl implements InstrumentoFinancieroServiceInterface {

    @Autowired
    private InstrumentoFinancieroRepository instrumentoFinancieroRepository;

    public List<InstrumentoFinancieroDTO> consultarTodos() {
        return this.instrumentoFinancieroRepository.findAll().stream().map(InstrumentoFinancieroMapper::toDTO).collect(Collectors.toList());
    }

    public InstrumentoFinancieroDTO consultar(Long id) throws InstrumentoNoEncontradoException {
        Optional<InstrumentoFinanciero> instrumentoFinanciero = this.instrumentoFinancieroRepository.findById(id);

        if (instrumentoFinanciero.isEmpty()) {
            throw new InstrumentoNoEncontradoException(ELEMENTO_NO_ENCONTRADO);
        }

        return InstrumentoFinancieroMapper.toDTO(instrumentoFinanciero.get());
    }

    public InstrumentoFinancieroDTO registrar(InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws InstrumentoDuplicadoException, OpcionInvalidaException {
        InstrumentoFinanciero instrumentoFinanciero = InstrumentoFinancieroMapper.toModel(instrumentoFinancieroDTO);
        validarInstrumentoFinanciero(instrumentoFinanciero);
        return InstrumentoFinancieroMapper.toDTO(this.instrumentoFinancieroRepository.save(instrumentoFinanciero));
    }

    public void eliminar(Long id) {
        this.instrumentoFinancieroRepository.deleteById(id);
    }

    public InstrumentoFinancieroDTO editar(Long id, InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws OpcionInvalidaException, InstrumentoNoEncontradoException {
        Optional<InstrumentoFinanciero> instrumentoFinancieroAEditar = this.instrumentoFinancieroRepository.findById(id);

        if (instrumentoFinancieroAEditar.isEmpty()) {
            throw new InstrumentoNoEncontradoException(ELEMENTO_NO_ENCONTRADO);
        }

        InstrumentoFinanciero instrumentoFinancieroEditado = editarInstrumentoFinanciero(instrumentoFinancieroAEditar.get(), instrumentoFinancieroDTO);

        return InstrumentoFinancieroMapper.toDTO(this.instrumentoFinancieroRepository.save(instrumentoFinancieroEditado));
    }

    private InstrumentoFinanciero editarInstrumentoFinanciero(InstrumentoFinanciero instrumentoFinancieroAEditar, InstrumentoFinancieroDTO instrumentoFinancieroDTO) {
        if (instrumentoFinancieroDTO.getTipo() != null) {
            instrumentoFinancieroAEditar.setTipo(instrumentoFinancieroDTO.getTipo());
        }

        if (instrumentoFinancieroDTO.getNombre() != null) {
            instrumentoFinancieroAEditar.setNombre(instrumentoFinancieroDTO.getNombre());
        }

        if (instrumentoFinancieroDTO.getPrecio() != null) {
            instrumentoFinancieroAEditar.setPrecio(instrumentoFinancieroDTO.getPrecio());
        }

        validarInstrumentoFinanciero(instrumentoFinancieroAEditar);

        return instrumentoFinancieroAEditar;
    }

    private boolean existeNombreInstrumentoFinanciero(String nombre) {
        return this.instrumentoFinancieroRepository.existsByNombre(nombre);
    }

    private void validarInstrumentoFinanciero(InstrumentoFinanciero instrumentoFinanciero) {
        if(existeNombreInstrumentoFinanciero(instrumentoFinanciero.getNombre())) {
            throw new InstrumentoDuplicadoException(ELEMENTO_DUPLICADO);
        }

        Validaciones.validarInstrumentoFinanciero(instrumentoFinanciero);
    }
}
