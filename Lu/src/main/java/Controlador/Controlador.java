package Controlador;
import Enums.*;
import Excepciones.InstrumentoDuplicadoException;
import Excepciones.InstrumentoNoEncontradoException;
import Excepciones.OpcionInvalidaException;
import Vista.*;
import Modelo.*;

import java.util.List;

public class Controlador {
    private final Modelo modelo;
    private final Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    private void registrar () throws InstrumentoNoEncontradoException, InstrumentoDuplicadoException {
        InstrumentoFinanciero instrumentoFinanciero;
        int instrumentoFinancieroTipo = vista.getTipo();
        String nombre = vista.getNombre();
        double precio = vista.getPrecio();
        instrumentoFinanciero = modelo.registrar(nombre, precio, instrumentoFinancieroTipo);
        vista.mensaje("\n-------- Se registro exitosamente el intrumento financiero: --------\n" + instrumentoFinanciero.toString());
    }

    private void consultar() throws InstrumentoNoEncontradoException, OpcionInvalidaException {
        vista.consultarOpciones();
        MenuConsultar opcionUsuario = MenuConsultar.opcionSeleccionada(vista.opcionUsuario());
        switch (opcionUsuario) {
            case CONSULTAR_POR_NOMBRE:
                String nombre = vista.getNombre();
                InstrumentoFinanciero intrumentoFinanciero = modelo.consultar(nombre);
                vista.consultar(intrumentoFinanciero);
                break;
            case CONSULTAR_TODOS:
                List<InstrumentoFinanciero> intrumentosFinancieros = modelo.consultar();
                vista.consultar(intrumentosFinancieros);
                break;
        }
    }

    private void editar() throws InstrumentoNoEncontradoException, OpcionInvalidaException  {
        String nombre = vista.getNombre();
        InstrumentoFinanciero instrumentoFinanciero = modelo.consultar(nombre);
        vista.mensaje("\n-------- El instrumento financiero a modificar es: --------\n");
        vista.consultar(instrumentoFinanciero);
        vista.editarOpciones();
        MenuEditar opcionUsuario = MenuEditar.opcionSeleccionada(vista.opcionUsuario());

        switch (opcionUsuario) {
            case EDITAR_NOMBRE:
                vista.mensaje("-------- Modificar Nombre --------");
                String nuevoNombre = vista.getNombre();
                instrumentoFinanciero.setNombre(nuevoNombre);
                break;
            case EDITAR_PRECIO:
                vista.mensaje("-------- Modificar Precio --------");
                double nuevoPrecio = vista.getPrecio();
                instrumentoFinanciero.setPrecio(nuevoPrecio);
                break;
        }
        vista.mensaje("\n-------- Instrumento modificado exitosamente --------\n");
    }

    private void eliminar() throws InstrumentoNoEncontradoException {
        String nombre = vista.getNombre();
        InstrumentoFinanciero instrumentoFinanciero = modelo.eliminar(nombre);
        vista.mensaje("\n-------- Instrumento eliminado exitosamente: --------\n" + instrumentoFinanciero.toString());
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

