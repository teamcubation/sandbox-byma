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


@Service
public class InstrumentoFinancieroServiceImpl implements InstrumentoFinancieroServiceInterface {

    @Autowired
    InstrumentoFinancieroRepository instrumentoFinancieroRepository;

    public List<InstrumentoFinancieroDTO> consultarTodos() {
        return this.instrumentoFinancieroRepository.findAll().stream().map(InstrumentoFinancieroMapper::toDTO).collect(Collectors.toList());
    }

    public InstrumentoFinancieroDTO consultar(Long id) throws Exception {
        Optional<InstrumentoFinanciero> instrumentoFinanciero = this.instrumentoFinancieroRepository.findById(id);

        if (instrumentoFinanciero.isEmpty()) {
            throw new InstrumentoNoEncontradoException("El instrumento financiero a consultar no existe.");
        }

        return InstrumentoFinancieroMapper.toDTO(instrumentoFinanciero.get());
    }

    public InstrumentoFinancieroDTO registrar(InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws Exception {
        if(existeNombreInstrumentoFinanciero(instrumentoFinancieroDTO.getNombre())) {
            throw new InstrumentoDuplicadoException("El instrumento financiero a registrar con ese nombre ya existe.");
        }

        validarDatos(instrumentoFinancieroDTO);

        InstrumentoFinanciero instrumentoFinanciero = InstrumentoFinancieroMapper.toModel(instrumentoFinancieroDTO);
        return InstrumentoFinancieroMapper.toDTO(this.instrumentoFinancieroRepository.save(instrumentoFinanciero));
    }

    public void eliminar(Long id) {
        this.instrumentoFinancieroRepository.deleteById(id);
    }

    public InstrumentoFinancieroDTO editar(Long id, InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws Exception {
        validarDatos(instrumentoFinancieroDTO);

        if (existeInstrumentoFinanciero(id)) {
            InstrumentoFinanciero instrumentoFinancieroModel = InstrumentoFinancieroMapper.toModel(instrumentoFinancieroDTO);
            instrumentoFinancieroModel.setId(id);
            return InstrumentoFinancieroMapper.toDTO(this.instrumentoFinancieroRepository.save(instrumentoFinancieroModel));
        }
        throw new InstrumentoNoEncontradoException("El instrumento financiero a editar no existe");
    }

    private void validarNombre(String nombreAValidar) throws Exception {
        if (!Validaciones.esNombreValido(nombreAValidar)) {
            throw new OpcionInvalidaException("Por favor ingrese un nombre valido");
        }
    }

    private void validarPrecio(Double precioAValidar) throws Exception {
        if (!Validaciones.esPrecioValido(precioAValidar)) {
            throw new OpcionInvalidaException("Por favor ingrese un precio valido.");
        }
    }

    private void validarTipo(Integer tipoAValidar) throws Exception {
        if (!Validaciones.esTipoValido(tipoAValidar)) {
            throw new OpcionInvalidaException("Por favor ingrese un instrumento financieron valido.");
        }
    }

    private void validarDatos(InstrumentoFinancieroDTO instrumentoFinancieroDTO) throws Exception {
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
