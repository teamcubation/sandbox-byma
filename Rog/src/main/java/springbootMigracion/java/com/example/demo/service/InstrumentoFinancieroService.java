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
import springbootMigracion.java.com.example.demo.utils.logs.LogMessages;
import springbootMigracion.java.com.example.demo.utils.validations.ErrorMessagesInstrumento;
import springbootMigracion.java.com.example.demo.utils.validations.ErrorMessagesInversor;
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
        log.info(LogMessages.REGISTRANDO_INSTRUMENTO.getMessage(), instrumentoDTO);
        ValidatorInstrumentoFinanciero.validarInstrumento(instrumentoDTO);
        InstrumentoFinanciero instrumento = instrumentoFinancieroFactory.crearInstrumento(instrumentoDTO);
        if (instrumentoFinancieroRepository.existsByNombreIgnoreCase(instrumento.getNombre())){
            log.warn(LogMessages.INSTRUMENTO_DUPLICADO.getMessage(), instrumento.getNombre());
            throw new InstrumentoDuplicadoException(ErrorMessagesInstrumento.ERROR_INSTRUMENTO_DUPLICADO);
        }
        InstrumentoFinanciero instrumentoGuardado = instrumentoFinancieroRepository.save(instrumento);
        log.info(LogMessages.INSTRUMENTO_REGISTRADO.getMessage(), instrumentoGuardado);
        return instrumentoGuardado;
    }

    @Override
    public InstrumentoFinanciero buscarInstrumentoPorId(Long id) throws Exception {
        log.info(LogMessages.BUSCANDO_INSTRUMENTO_POR_ID.getMessage(), id);
        Optional<InstrumentoFinanciero> optionalInstrumentoFinanciero = instrumentoFinancieroRepository.findById(id);
        if (optionalInstrumentoFinanciero.isPresent()){
            log.info(LogMessages.INSTRUMENTO_ENCONTRADO.getMessage(), optionalInstrumentoFinanciero.get());
            return optionalInstrumentoFinanciero.get();
        } else {
            log.warn(LogMessages.INSTRUMENTO_NO_ENCONTRADO_POR_ID.getMessage(), id);
            throw new InstrumentoNoEncontradoException(ErrorMessagesInstrumento.ERROR_INSTRUMENTO_NO_ENCONTRADO);
        }
    }

    @Override
    public void eliminarInstrumento(Long id) throws Exception {
        log.info(LogMessages.ELIMINANDO_INSTRUMENTO.getMessage(), id);
        InstrumentoFinanciero instrumento = instrumentoFinancieroRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn(LogMessages.INSTRUMENTO_NO_ENCONTRADO_POR_ID.getMessage(), id);
                    return new InstrumentoNoEncontradoException(ErrorMessagesInstrumento.ERROR_INSTRUMENTO_NO_ENCONTRADO);
                });
        instrumentoFinancieroRepository.delete(instrumento);
        log.info(LogMessages.INSTRUMENTO_ELIMINADO.getMessage(), id);
    }

    @Override
    public List<InstrumentoFinanciero> listarTodosLosInstrumentos() {
        return instrumentoFinancieroRepository.findAll();
    }

    @Override
    public List<InstrumentoFinanciero> listarInstrumentosPorNombre(String nombre) {
        log.info(LogMessages.BUSCANDO_INSTRUMENTOS_POR_NOMBRE.getMessage(), nombre);
        List<InstrumentoFinanciero> instrumentos = instrumentoFinancieroRepository.findAllByNombreContainingIgnoreCase(nombre);
        log.info(LogMessages.INSTRUMENTO_ENCONTRADO.getMessage(), instrumentos.size());
        return instrumentos;
    }

    @Override
    public InstrumentoFinanciero editarInstrumento(Long id, InstrumentoDTO nuevoInstrumentoDTO) throws Exception {
        log.info(LogMessages.EDITANDO_INSTRUMENTO.getMessage(), id);
        ValidatorInstrumentoFinanciero.validarInstrumento(nuevoInstrumentoDTO);
        Optional<InstrumentoFinanciero> optionalInstrumentoFinanciero = instrumentoFinancieroRepository.findById(id);
        InstrumentoFinanciero nuevoInstrumentoFinanciero = instrumentoFinancieroFactory.crearInstrumento(nuevoInstrumentoDTO);
        if (optionalInstrumentoFinanciero.isPresent()){
            InstrumentoFinanciero instrumentoFinancieroExistente = optionalInstrumentoFinanciero.get();
            nuevoInstrumentoFinanciero.setId(instrumentoFinancieroExistente.getId());
            log.info(LogMessages.INSTRUMENTO_EDITADO.getMessage(), nuevoInstrumentoFinanciero);
        } else {
            log.warn(LogMessages.INSTRUMENTO_NO_ENCONTRADO_POR_ID.getMessage(), id);
            throw new InstrumentoNoEncontradoException(ErrorMessagesInstrumento.ERROR_INSTRUMENTO_NO_ENCONTRADO);
        }
        return instrumentoFinancieroRepository.save(nuevoInstrumentoFinanciero);
    }

    @Override
    public void suscribirInversor(String nombreInstrumento, String nombreInversor) throws Exception {
        log.info(LogMessages.SUSCRIBIENDO_INVERSOR.getMessage(), nombreInversor, nombreInstrumento);
        InstrumentoFinanciero instrumento = instrumentoFinancieroRepository.findByNombreIgnoreCase(nombreInstrumento)
                .orElseThrow(() -> new InstrumentoNoEncontradoException(ErrorMessagesInstrumento.ERROR_INSTRUMENTO_NO_ENCONTRADO));
        Inversor inversor = inversorRepository.findByNombreIgnoreCase(nombreInversor)
                .orElseThrow(() -> new InversorNoEncontradoException(ErrorMessagesInversor.ERROR_INVERSOR_NO_ENCONTRADO));
        if (!inversor.getInstrumentosSuscritos().contains(instrumento) && !instrumento.getInversoresSuscritos().contains(inversor)){
            inversor.getInstrumentosSuscritos().add(instrumento);
            instrumento.getInversoresSuscritos().add(inversor);
            instrumentoFinancieroRepository.save(instrumento);
            inversorRepository.save(inversor);
        } else {
            log.warn(LogMessages.INVERSOR_YA_SUSCRITO.getMessage(), instrumento.getNombre());
            throw new InstrumentoDuplicadoException(ErrorMessagesInstrumento.ERROR_INSTRUMENTO_DUPLICADO);
        }
    }
}
