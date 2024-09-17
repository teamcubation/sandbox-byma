package axi.vistas;

import axi.Broker;
import axi.excepciones.InstrumentoDuplicadoException;
import axi.excepciones.InstrumentoNoEncontradoException;
import axi.modelos.Tipo;

import java.util.Scanner;

public class Menu {
    private static Menu menu;
    private Broker broker;
    Scanner scanner;


    private Menu() {
        this.broker = Broker.getBroker();
        this.scanner = new Scanner(System.in);
    }

    public static Menu getMenu() {
        if (menu == null)
            menu = new Menu();
        return menu;
    }


    public void abrirMenu() {
        System.out.println("Ingrese la operacion que desea hacer: " +
                "1. Registrar instrumento " +
                "2. Eliminar instrumento " +
                "3. Consultar instrumentos " +
                "4. Modificar instrumento ");
        String operacion = scanner.nextLine();
        while (operacion.equals("1") || operacion.equals("2") || operacion.equals("3") ||
                operacion.equals("4")) {
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
        broker.modificar(variable, modificacion, nombre);
    }

    private void consultarInstrumentos() {
        broker.consultar();
    }

    private void eliminarInstrumento() {
        System.out.println("ingrese el instrumento que desea eliminar");
        broker.eliminar(scanner.next());
    }

    private void registrarInstrumento() {
        Tipo tipo = this.ingresarTipo();
        String nombre = this.ingresarNombre();
        double precio = this.ingresarPrecio();
        broker.registrarInstrumento(nombre, precio, tipo);
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
