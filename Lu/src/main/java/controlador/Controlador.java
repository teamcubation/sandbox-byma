package controlador;
import controlador.enums.MenuConsultar;
import controlador.enums.MenuEditar;
import controlador.enums.MenuOpciones;
import excepciones.InstrumentoDuplicadoException;
import excepciones.InstrumentoNoEncontradoException;
import excepciones.OpcionInvalidaException;
import service.*;
import modelo.*;
import vista.Vista;

import java.util.List;

public class Controlador {
    private final Vista vista;
    private final InstrumentoFinancieroService instrumentoFinancieroService;

    public Controlador(Vista vista) {
        this.vista = vista;
        this.instrumentoFinancieroService = InstrumentoFinancieroService.obtenerInstancia();
    }

    private void registrar () throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {
        InstrumentoFinanciero instrumentoFinanciero;
        int instrumentoFinancieroTipo = getTipo();
        String nombre = getNombre();
        double precio = getPrecio();
        instrumentoFinanciero = instrumentoFinancieroService.registrar(nombre, precio, instrumentoFinancieroTipo);
        vista.mensajeRegistrarExito(instrumentoFinanciero);
    }

    private void consultar() throws InstrumentoNoEncontradoException, OpcionInvalidaException {
        vista.consultarOpciones();
        MenuConsultar opcionUsuario = MenuConsultar.opcionSeleccionada(vista.opcionUsuario());
        switch (opcionUsuario) {
            case CONSULTAR_POR_NOMBRE:
                String nombre = getNombre();
                InstrumentoFinanciero intrumentoFinanciero = instrumentoFinancieroService.consultar(nombre);
                vista.consultar(intrumentoFinanciero);
                break;
            case MenuConsultar.CONSULTAR_TODOS:
                List<InstrumentoFinanciero> intrumentosFinancieros = instrumentoFinancieroService.consultar();
                vista.consultar(intrumentosFinancieros);
                break;
        }
    }

    private void editar() throws InstrumentoNoEncontradoException, OpcionInvalidaException  {
        String nombre = getNombre();
        InstrumentoFinanciero instrumentoFinanciero = instrumentoFinancieroService.consultar(nombre);
        vista.mensajeEditar(instrumentoFinanciero);
        vista.editarOpciones();
        MenuEditar opcionUsuario = MenuEditar.opcionSeleccionada(vista.opcionUsuario());

        switch (opcionUsuario) {
            case EDITAR_NOMBRE:
                String nuevoNombre = getNombre();
                instrumentoFinanciero.setNombre(nuevoNombre);
                break;
            case EDITAR_PRECIO:
                double nuevoPrecio = getPrecio();
                instrumentoFinanciero.setPrecio(nuevoPrecio);
                break;
        }
        vista.mensajeEditarExito();
    }

    private void eliminar() throws InstrumentoNoEncontradoException {
        String nombre = getNombre();
        InstrumentoFinanciero instrumentoFinanciero = instrumentoFinancieroService.eliminar(nombre);
        vista.mensajeEliminarExito(instrumentoFinanciero);
    }

    private double getPrecio() {
        return vista.getPrecio();
    }

    private String getNombre() {
        return vista.getNombre();
    }

    private int getTipo() {
        return vista.getTipo();
    }

    public void gestionarInstrumentosFinancieros() {
        boolean mostrarMenu = true;

        while (mostrarMenu) {
            vista.mostrarMenu();

            try {
                MenuOpciones opcionUsuario = MenuOpciones.opcionSeleccionada(vista.opcionUsuario());

                switch (opcionUsuario) {
                    case REGISTRAR:
                        registrar();
                        break;
                    case CONSULTAR:
                        consultar();
                        break;
                    case EDITAR:
                        editar();
                        break;
                    case ELIMINAR:
                        eliminar();
                        break;
                    case SALIR:
                        vista.salir();
                        mostrarMenu = false;
                        break;
                }
            }  catch (InstrumentoNoEncontradoException | InstrumentoDuplicadoException | OpcionInvalidaException mensajeExcepcion) {
                System.out.print(mensajeExcepcion.getMessage());
            } catch (NumberFormatException mensajeExcepcion) {
                System.out.print("\n-------- Por favor selecciona una opción valida entre las que se muestran en el menú - No ingreses letras cuando se solicite un numero --------\n\n");
            }
        }
    }
}

