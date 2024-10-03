package springbootMigracion.java.com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootMigracion.java.com.example.demo.dto.InversorDTO;
import springbootMigracion.java.com.example.demo.exception.InversorDuplicadoException;
import springbootMigracion.java.com.example.demo.exception.InversorNoEncontradoException;
import springbootMigracion.java.com.example.demo.model.Inversor;
import springbootMigracion.java.com.example.demo.repository.IInversorRepository;
import springbootMigracion.java.com.example.demo.utils.logs.LogMessages;
import springbootMigracion.java.com.example.demo.utils.validations.ErrorMessagesInversor;
import springbootMigracion.java.com.example.demo.utils.validations.ValidatorInversor;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InversorService implements IInversorService {

    @Autowired
    private IInversorRepository inversorRepository;

    @Override
    public Inversor registrarInversor(InversorDTO inversorDTO) throws Exception {
        log.info(LogMessages.REGISTRANDO_INVERSOR.getMessage(), inversorDTO);
        ValidatorInversor.validarInversor(inversorDTO);
        Inversor inversor = Inversor.builder()
                .nombre(inversorDTO.getNombre())
                .email(inversorDTO.getEmail())
                .build();
        if (inversorRepository.existsByNombreIgnoreCase(inversor.getNombre())){
            log.warn(LogMessages.INVERSOR_DUPLICADO.getMessage(), inversor.getNombre());
            throw new InversorDuplicadoException(ErrorMessagesInversor.ERROR_INVERSOR_DUPLICADO);
        }
        Inversor inversorGuardado = inversorRepository.save(inversor);
        log.info(LogMessages.INVERSOR_REGISTRADO.getMessage(), inversorGuardado);
        return inversorGuardado;
    }

    @Override
    public Inversor buscarInversorPorId(Long id) throws Exception {
        log.info(LogMessages.BUSCANDO_INVERSOR_POR_ID.getMessage(), id);
        Optional<Inversor> optionalInversor = inversorRepository.findById(id);
        if (optionalInversor.isPresent()) {
            log.info(LogMessages.INVERSOR_ENCONTRADO.getMessage(), optionalInversor.get());
            return optionalInversor.get();
        } else {
            log.warn(LogMessages.INVERSOR_NO_ENCONTRADO_POR_ID.getMessage(), id);
            throw new InversorNoEncontradoException(ErrorMessagesInversor.ERROR_INVERSOR_NO_ENCONTRADO);
        }
    }

    @Override
    public void eliminarInversor(Long id) throws Exception {
        log.info(LogMessages.ELIMINANDO_INVERSOR.getMessage(), id);
        Inversor inversor = inversorRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn(LogMessages.INVERSOR_NO_ENCONTRADO_AL_ELIMINAR.getMessage(), id);
                    return new InversorNoEncontradoException(ErrorMessagesInversor.ERROR_INVERSOR_NO_ENCONTRADO);
                });
        inversorRepository.delete(inversor);
        log.info(LogMessages.INVERSOR_ELIMINADO.getMessage(), id);
    }

    @Override
    public List<Inversor> listarTodosLosInversores() {
        return inversorRepository.findAll();
    }

    @Override
    public List<Inversor> listarInversoresPorNombre(String nombre) {
        log.info(LogMessages.SOLICITUD_FILTRAR_INVERSORES_POR_NOMBRE.getMessage(), nombre);
        List<Inversor> inversores = inversorRepository.findAllByNombreContainingIgnoreCase(nombre);
        log.info(LogMessages.INVERSORES_ENCONTRADOS_POR_NOMBRE.getMessage(), nombre, inversores.size());
        return inversores;
    }

    @Override
    public Inversor editarInversor(Long id, InversorDTO nuevoInversorDTO) throws Exception {
        log.info(LogMessages.EDITANDO_INVERSOR.getMessage(), id);
        ValidatorInversor.validarInversor(nuevoInversorDTO);
        Inversor inversor = inversorRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn(LogMessages.INVERSOR_NO_ENCONTRADO_AL_EDITAR.getMessage(), id);
                    return new InversorNoEncontradoException(ErrorMessagesInversor.ERROR_INVERSOR_NO_ENCONTRADO);
                });
        inversor.setNombre(nuevoInversorDTO.getNombre());
        inversor.setEmail(nuevoInversorDTO.getEmail());
        log.info(LogMessages.INVERSOR_ACTUALIZADO.getMessage(), id);
        return inversorRepository.save(inversor);
    }
}
