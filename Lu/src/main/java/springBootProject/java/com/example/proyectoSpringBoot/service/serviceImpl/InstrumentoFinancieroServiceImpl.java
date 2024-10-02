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
            throw new InstrumentoNoEncontradoException(MSJ_ELEMENTO_NO_ENCONTRADO);
        }

        return InstrumentoFinancieroMapper.toDTO(instrumentoFinanciero.get());
    }

    public InstrumentoFinancieroDTO registrar(InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws InstrumentoDuplicadoException, OpcionInvalidaException {
        if(existeNombreInstrumentoFinanciero(instrumentoFinancieroDTO.getNombre())) {
            throw new InstrumentoDuplicadoException(MSJ_ELEMENTO_DUPLICADO);
        }

        validarDatos(instrumentoFinancieroDTO);

        InstrumentoFinanciero instrumentoFinanciero = InstrumentoFinancieroMapper.toModel(instrumentoFinancieroDTO);
        return InstrumentoFinancieroMapper.toDTO(this.instrumentoFinancieroRepository.save(instrumentoFinanciero));
    }

    public void eliminar(Long id) {
        this.instrumentoFinancieroRepository.deleteById(id);
    }

    public InstrumentoFinancieroDTO editar(Long id, InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws OpcionInvalidaException, InstrumentoNoEncontradoException {
        //validarDatos(instrumentoFinancieroDTO);

        //hacer el if en el caso de que no exista el instrumento financiero
        Optional<InstrumentoFinanciero> instrumentoFinancieroAEditar = this.instrumentoFinancieroRepository.findById(id);

        if (instrumentoFinancieroAEditar.isEmpty()) {
            throw new InstrumentoNoEncontradoException(MSJ_ELEMENTO_NO_ENCONTRADO);
        }

        if (existeInstrumentoFinanciero(id)) {
            InstrumentoFinanciero instrumentoFinancieroModel = InstrumentoFinancieroMapper.toModel(instrumentoFinancieroDTO);
            instrumentoFinancieroModel.setId(id);
            return InstrumentoFinancieroMapper.toDTO(this.instrumentoFinancieroRepository.save(instrumentoFinancieroModel));
        }
        throw new InstrumentoNoEncontradoException(MSJ_ELEMENTO_NO_ENCONTRADO);
    }

    private void validarNombre(String nombreAValidar) throws OpcionInvalidaException {
        if (!Validaciones.esNombreValido(nombreAValidar)) {
            throw new OpcionInvalidaException(MSJ_OPCION_INVALIDA);
        }
    }

    private void validarPrecio(Double precioAValidar) throws OpcionInvalidaException {
        if (!Validaciones.esPrecioValido(precioAValidar)) {
            throw new OpcionInvalidaException(MSJ_OPCION_INVALIDA);
        }
    }

    private void validarTipo(Integer tipoAValidar) throws OpcionInvalidaException {
        if (!Validaciones.esTipoValido(tipoAValidar)) {
            throw new OpcionInvalidaException(MSJ_OPCION_INVALIDA);
        }
    }

    private void validarDatos(InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws OpcionInvalidaException  {
        validarNombre(instrumentoFinancieroDTO.getNombre());
        validarPrecio(instrumentoFinancieroDTO.getPrecio());
        validarTipo(instrumentoFinancieroDTO.getTipo());
    }

    private boolean existeNombreInstrumentoFinanciero(String nombre) {
        return this.instrumentoFinancieroRepository.existsByNombre(nombre);
    }

    private boolean existeInstrumentoFinanciero(Long id) {
        return this.instrumentoFinancieroRepository.existsById(id);
    }
}
