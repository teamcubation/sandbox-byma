package com.example.teamcubation.service.impl;

import com.example.teamcubation.exceptions.InstrumentoDuplicadoException;
import com.example.teamcubation.exceptions.InstrumentoNoEncontradoException;
import com.example.teamcubation.model.Accion;
import com.example.teamcubation.repository.interfaces.AccionRepository;
import com.example.teamcubation.service.AccionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccionServiceImpl implements AccionService {

    private final AccionRepository accionRepository;


    private final BonoServiceImpl bonoService;

    public AccionServiceImpl(AccionRepository accionRepository, @Lazy BonoServiceImpl bonoService) {
        this.accionRepository = accionRepository;
        this.bonoService = bonoService;
    }


    /**
     * Crea un nuevo instrumento accion y lo agrega a la lista de instrumentos financieros.
     *
     * @param nuevaAccion El instrumento accion a crear
     * @return El instrumento accion creado
     * @throws InstrumentoDuplicadoException Si el instrumento ya existe
     */
    @Override
    public Accion createAccion(Accion nuevaAccion) throws InstrumentoDuplicadoException {

        if (this.instrumentoEsDuplicado(nuevaAccion.getNombre())) {
            throw new InstrumentoDuplicadoException("Error: Ya existe un instrumento con el mismo nombre");
        }

        return this.accionRepository.save(nuevaAccion);
    }

    /**
     * Devuelve una lista de todos los instrumentos financieros de tipo accion.
     *
     * @return La lista de instrumentos financieros de tipo accion
     */
    @Override
    public List<Accion> getAllAcciones() {
        return this.accionRepository.findAll();
    }

    /**
     * Actualiza un instrumento accion existente.
     * <p>
     * El instrumento accion se busca por su id, si no se encuentra se lanza una excepcion
     * {@link InstrumentoNoEncontradoException}.
     * </p>
     * <p>
     * Si el instrumento accion se actualiza correctamente, se devuelve un objeto
     * {@link Accion} con los nuevos valores.
     * </p>
     * <p>
     * Si el nombre del instrumento accion ya existe en la base de datos, se lanza una excepcion
     * {@link InstrumentoDuplicadoException}.
     * </p>
     *
     * @param accion El instrumento accion a actualizar
     * @return El instrumento accion actualizado
     * @throws InstrumentoNoEncontradoException Si no se encuentra el instrumento accion
     * @throws InstrumentoDuplicadoException    Si el nombre del instrumento accion ya existe
     */
    @Override
    public Accion updateAccion(Accion accion) throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {

        if (this.accionEsInexistente(accion.getId())) {
            throw new InstrumentoNoEncontradoException("Error: El instrumento no existe");
        }

        if (this.instrumentoEsDuplicado(accion.getNombre())) {
            throw new InstrumentoDuplicadoException("Error: Ya existe un instrumento con el mismo nombre");
        }

        return this.accionRepository.save(accion);
    }

    /**
     * Elimina un instrumento accion existente.
     * <p>
     * El instrumento accion se busca por su id, si no se encuentra se lanza una excepcion
     * {@link InstrumentoNoEncontradoException}.
     * </p>
     * <p>
     * Si el instrumento accion se elimina correctamente, no se devuelve nada.
     * </p>
     *
     * @param id El id del instrumento accion a eliminar
     * @throws InstrumentoNoEncontradoException Si no se encuentra el instrumento accion
     */
    @Override
    public void deleteAccion(long id) throws InstrumentoNoEncontradoException {

        if (this.accionEsInexistente(id)) {
            throw new InstrumentoNoEncontradoException("Error: El instrumento no existe");
        }

        this.accionRepository.deleteById(id);
    }


    //Validaciones

    /**
     * Valida si el instrumento accion existe.
     * <p>
     * Utiliza el repositorio para buscar el instrumento por su id y devuelve true
     * si existe, false en caso contrario.
     * </p>
     *
     * @param id El id del instrumento accion a buscar
     * @return True si el instrumento existe, false en caso contrario
     */
    private boolean accionEsInexistente(long id) {
        return !this.accionRepository
                .existsById(id);
    }


    /**
     * Valida si el instrumento ya existe.
     * <p>
     * Busca en la lista de instrumentos si ya existe alguno con el mismo nombre,
     * si es asi devuelve true, false en caso contrario.
     * </p>
     *
     * @param nombre El nombre del instrumento a buscar
     * @return True si el instrumento existe, false en caso contrario
     */
    private boolean instrumentoEsDuplicado(String nombre) {
        return bonoEsDuplicado(nombre) || accionEsDuplicado(nombre);
    }

    /**
     * Valida si el instrumento accion ya existe.
     * <p>
     * Busca en la tabla de acciones si ya existe alguno con el mismo nombre,
     * si es asi devuelve true, false en caso contrario.
     * </p>
     *
     * @param nombre El nombre del instrumento a buscar
     * @return True si el instrumento existe, false en caso contrario
     */
    public boolean accionEsDuplicado(String nombre) {
        return this.accionRepository
                .findAll()
                .stream()
                .anyMatch(accion -> accion.getNombre().equalsIgnoreCase(nombre));
    }

    /**
     * Valida si el instrumento bono ya existe.
     * <p>
     * Busca en la tabla de bonos si ya existe alguno con el mismo nombre,
     * si es asi devuelve true, false en caso contrario.
     * </p>
     *
     * @param nombre El nombre del instrumento a buscar
     * @return True si el instrumento existe, false en caso contrario
     */
    private boolean bonoEsDuplicado(String nombre) {
        return this.bonoService
                .getAllBonos()
                .stream()
                .anyMatch(bono -> bono.getNombre().equalsIgnoreCase(nombre));
    }
}
