package springbootMigracion.java.com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootMigracion.java.com.example.demo.dto.InstrumentoDTO;
import springbootMigracion.java.com.example.demo.exception.InstrumentoDuplicadoException;
import springbootMigracion.java.com.example.demo.exception.InstrumentoNoEncontradoException;
import springbootMigracion.java.com.example.demo.exception.InversorNoEncontradoException;
import springbootMigracion.java.com.example.demo.model.InstrumentoFinanciero;
import springbootMigracion.java.com.example.demo.model.Inversor;
import springbootMigracion.java.com.example.demo.repository.IInstrumentoFinancieroRepository;
import springbootMigracion.java.com.example.demo.repository.IInversorRepository;
import springbootMigracion.java.com.example.demo.utils.validations.ErrorMessages;
import springbootMigracion.java.com.example.demo.utils.validations.ValidatorInstrumentoFinanciero;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InstrumentoFinancieroService implements IInstrumentoFinancieroService {

    @Autowired
    private IInstrumentoFinancieroRepository instrumentoFinancieroRepository;

    @Autowired
    private IInversorRepository inversorRepository;

    @Autowired
    private InstrumentoFinancieroFactoryService instrumentoFinancieroFactory;

    @Override
    public InstrumentoFinanciero registrarInstrumento(InstrumentoDTO instrumentoDTO) throws Exception {
        log.info("Registrando instrumento: {}", instrumentoDTO);
        ValidatorInstrumentoFinanciero.validarInstrumento(instrumentoDTO);
        InstrumentoFinanciero instrumento = instrumentoFinancieroFactory.crearInstrumento(instrumentoDTO);
        if (instrumentoFinancieroRepository.existsByNombreIgnoreCase(instrumento.getNombre())){
            log.warn("Instrumento duplicado: {}", instrumento.getNombre());
            throw new InstrumentoDuplicadoException(ErrorMessages.ERROR_INSTRUMENTO_DUPLICADO);
        }
        InstrumentoFinanciero instrumentoGuardado = instrumentoFinancieroRepository.save(instrumento);
        log.info("Instrumento registrado exitosamente: {}", instrumentoGuardado);
        return instrumentoGuardado;
    }

    @Override
    public InstrumentoFinanciero buscarInstrumentoPorId(Long id) throws Exception {
        log.info("Buscando instrumento por ID: {}", id);
        Optional<InstrumentoFinanciero> optionalInstrumentoFinanciero = instrumentoFinancieroRepository.findById(id);
        if (optionalInstrumentoFinanciero.isPresent()){
            log.info("Instrumento encontrado: {}", optionalInstrumentoFinanciero.get());
            return optionalInstrumentoFinanciero.get();
        } else {
            log.warn("Instrumento no encontrado por ID: {}", id);
            throw new InstrumentoNoEncontradoException(ErrorMessages.ERROR_INSTRUMENTO_NO_ENCONTRADO);
        }
    }

    @Override
    public void eliminarInstrumento(Long id) throws Exception {
        log.info("Eliminando instrumento con ID: {}", id);
        InstrumentoFinanciero instrumento = instrumentoFinancieroRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Instrumento no encontrado al intentar eliminar: {}", id);
                    return new InstrumentoNoEncontradoException(ErrorMessages.ERROR_INSTRUMENTO_NO_ENCONTRADO);
                });
        instrumentoFinancieroRepository.delete(instrumento);
        log.info("Instrumento con ID {} eliminado", id);
    }

    @Override
    public List<InstrumentoFinanciero> listarTodosLosInstrumentos() {
        return instrumentoFinancieroRepository.findAll();
    }

    @Override
    public List<InstrumentoFinanciero> listarInstrumentosPorNombre(String nombre) {
        log.info("Buscando instrumentos por nombre: {}", nombre);
        List<InstrumentoFinanciero> instrumentos = instrumentoFinancieroRepository.findAllByNombreContainingIgnoreCase(nombre);
        log.info("Instrumentos encontrados: {}", instrumentos.size());
        return instrumentos;
    }

    @Override
    public InstrumentoFinanciero editarInstrumento(Long id, InstrumentoDTO nuevoInstrumentoDTO) throws Exception {
        log.info("Editando instrumento con ID: {}", id);
        ValidatorInstrumentoFinanciero.validarInstrumento(nuevoInstrumentoDTO);
        Optional<InstrumentoFinanciero> optionalInstrumentoFinanciero = instrumentoFinancieroRepository.findById(id);
        InstrumentoFinanciero nuevoInstrumentoFinanciero = instrumentoFinancieroFactory.crearInstrumento(nuevoInstrumentoDTO);
        if (optionalInstrumentoFinanciero.isPresent()){
            InstrumentoFinanciero instrumentoFinancieroExistente = optionalInstrumentoFinanciero.get();
            nuevoInstrumentoFinanciero.setId(instrumentoFinancieroExistente.getId());
            log.info("Instrumento actualizado: {}", nuevoInstrumentoFinanciero);
        } else {
            log.warn("Instrumento no encontrado al intentar editar: {}", id);
            throw new InstrumentoNoEncontradoException(ErrorMessages.ERROR_INSTRUMENTO_NO_ENCONTRADO);
        }
        return instrumentoFinancieroRepository.save(nuevoInstrumentoFinanciero);
    }

    @Override
    public void suscribirInversor(String nombreInstrumento, String nombreInversor) throws Exception {
        log.info("Suscribiendo inversor {} al instrumento {}", nombreInversor, nombreInstrumento);
        InstrumentoFinanciero instrumento = instrumentoFinancieroRepository.findByNombreIgnoreCase(nombreInstrumento)
                .orElseThrow(() -> new InstrumentoNoEncontradoException(ErrorMessages.ERROR_INSTRUMENTO_NO_ENCONTRADO));
        Inversor inversor = inversorRepository.findByNombreIgnoreCase(nombreInversor)
                .orElseThrow(() -> new InversorNoEncontradoException(ErrorMessages.ERROR_INVERSOR_NO_ENCONTRADO));
        if (!inversor.getInstrumentosSuscritosList().contains(instrumento) && !instrumento.getInversoresSuscritosList().contains(inversor)){
            inversor.getInstrumentosSuscritosList().add(instrumento);
            instrumento.getInversoresSuscritosList().add(inversor);
            instrumentoFinancieroRepository.save(instrumento);
            inversorRepository.save(inversor);
        } else {
            log.warn("Ya se encuetra suscrito a ese instrumento : {}", instrumento.getNombre());
            throw new InstrumentoDuplicadoException(ErrorMessages.ERROR_INSTRUMENTO_DUPLICADO);
        }

    }

}
