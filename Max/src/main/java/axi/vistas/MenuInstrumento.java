package axi.vistas;

import axi.servicios.InstrumentoService;
import axi.excepciones.InstrumentoDuplicadoException;
import axi.excepciones.InstrumentoNoEncontradoException;
import axi.modelos.Tipo;

import java.util.Scanner;

public class MenuInstrumento {
    private static MenuInstrumento menuInstrumento;
    private InstrumentoService instrumentoService;
    Scanner scanner;


    private MenuInstrumento() {
        this.instrumentoService = InstrumentoService.getBroker();
        this.scanner = new Scanner(System.in);
    }

    public static MenuInstrumento getMenu() {
        if (menuInstrumento == null)
            menuInstrumento = new MenuInstrumento();
        return menuInstrumento;
    }


    public void abrirMenu() {
        System.out.println("Ingrese la operacion que desea hacer: " +
                "1. Registrar instrumento " +
                "2. Eliminar instrumento " +
                "3. Consultar instrumentos " +
                "4. Modificar instrumento ");
        String operacion = scanner.nextLine();
        while (seguirOperando(operacion)) {
            try {
                switch (operacion) {
                    case "1":
                        this.registrarInstrumento();
                        break;
                    case "2":
                        this.eliminarInstrumento();
                        break;
                    case "3":
                        this.consultarInstrumentos();
                        break;
                    case "4":
                        this.modificarInstrumentos();
                }
            } catch (InstrumentoDuplicadoException e) {
                System.out.println(e.getMessage());
            } catch (InstrumentoNoEncontradoException i) {
                System.out.println(i.getMessage());
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Ingrese la operacion que desea hacer: " +
                    "1. Registrar instrumento " +
                    "2. Eliminar instrumento " +
                    "3. Consultar instrumentos " +
                    "4. Modificar instrumento ");
            operacion = scanner.next();
        }
    }

    private static boolean seguirOperando(String operacion) {
        return operacion.equals("1") || operacion.equals("2") || operacion.equals("3") ||
                operacion.equals("4");
    }

    private void modificarInstrumentos() {
        String modificacion;
        String nombre;
        System.out.println("ingrese que desea modificar: " +
                " 1. Nombre" +
                " 2. Tipo" +
                " 3. Precio");
        String variable = scanner.next();
        switch (variable) {
            case "1":
                modificacion = this.ingresarNombre();
                break;
            case "2":
                System.out.println("ingrese el nuevo tipo de instrumento 1.Accion 2.Bono");
                modificacion = scanner.next();
                break;
            case "3":
                modificacion = String.valueOf(this.ingresarPrecio());
                break;
            default:
                throw new IllegalArgumentException("Error. Numero ingresado no valido");
        }
        System.out.println("ingrese el nombre del instrumento que desea modificar");
        nombre = scanner.next();
        instrumentoService.modificarInstrumento(variable, modificacion, nombre);
    }

    private void consultarInstrumentos() {
        instrumentoService.consultarTodosLosInstrumentos();
    }

    private void eliminarInstrumento() {
        System.out.println("ingrese el instrumento que desea eliminar");
        instrumentoService.eliminarInstrumento(scanner.next());
    }

    private void registrarInstrumento() {
        Tipo tipo = this.ingresarTipo();
        String nombre = this.ingresarNombre();
        double precio = this.ingresarPrecio();
        instrumentoService.registrarInstrumento(nombre, precio, tipo);
    }

    private double ingresarPrecio() {
        System.out.println("ingrese el precio de la accion");
        return scanner.nextDouble();
    }

    private String ingresarNombre() {
        System.out.println("ingrese el nombre del instrumento");
        return scanner.next();
    }

    private Tipo ingresarTipo() {
        System.out.println("ingrese que tipo de instrumento quiere ingresar: 1.Accion, 2.Bono");
        String nombreTipo = scanner.next();
        Tipo tipo;
        switch (nombreTipo) {
            case "1":
                tipo = Tipo.ACCION;
                break;
            case "2":
                tipo = Tipo.BONO;
                break;
            default:
                tipo = null;
        }
        return tipo;
    }
}
