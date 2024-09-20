package axi.vistas;

import axi.excepciones.InstrumentoDuplicadoException;
import axi.excepciones.InstrumentoNoEncontradoException;
import axi.excepciones.InversorExistenteException;
import axi.excepciones.InversorNoEncontradoException;
import axi.servicios.InversorService;

import java.util.Scanner;

public class MenuInversor {
    final static String MSG_MENU_INVERSOR = "Ingrese la operacion que desea hacer: " +
            "1. Registrar inversor " +
            "2. Eliminar inversor " +
            "3. Agregar instumento a mi cartera " +
            "4. Eliminar instrumento de mi cartera " +
            "5. Modificar inversor" +
            "6. Consultar instrumentos";
    private static MenuInversor menu;
    Scanner scanner;
    private InversorService inversorService;

    private MenuInversor() {
        this.scanner = new Scanner(System.in);
        this.inversorService = InversorService.getInversorService();
    }

    public static MenuInversor getMenu() {
        if (menu == null)
            menu = new MenuInversor();
        return menu;
    }

    public void abrirMenu() {
        System.out.println(MSG_MENU_INVERSOR);
        String operacion = scanner.nextLine();
        while (operacion.equals("1") || operacion.equals("2") || operacion.equals("3") ||
                operacion.equals("4") || operacion.equals("5") || operacion.equals("6")) {
            try {
                switch (operacion) {
                    case "1":
                        this.registrarInversor();
                        break;
                    case "2":
                        this.eliminarInversor();
                        break;
                    case "3":
                        this.agregarInstrumentoACartera();
                        break;
                    case "4":
                        this.eliminarInstrumentoDeCartera();
                        break;
                    case "5":
                        this.modificarInversor();
                        break;
                    case "6":
                        this.ConsultarInstrumentos();
                        break;
                }
            } catch (InversorExistenteException e) {
                System.out.println(e.getMessage());
            } catch (InversorNoEncontradoException e) {
                System.out.println(e.getMessage());
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
            System.out.println(MSG_MENU_INVERSOR);
            operacion = scanner.next();
        }
    }

    private void ConsultarInstrumentos() {
        System.out.println("ingrese su dni");
        inversorService.consultarInstrumentosDeInversor(scanner.next());
    }

    public void registrarInversor() {
        System.out.println("ingrese su nombre");
        String nombre = scanner.next();
        System.out.println("ingrese su dni");
        String dni = scanner.next();
        inversorService.registrarInversor(nombre, dni);
    }

    public void eliminarInversor() {

    }

    public void modificarInversor() {

    }

    public void agregarInstrumentoACartera() {
        System.out.println("ingrese su dni");
        String dni = scanner.next();
        System.out.println("ingrese el nombre del instrumento que desea agregar");
        String nombre = scanner.next();
        inversorService.metodoParaSuscribirse(dni, nombre);
    }

    public void eliminarInstrumentoDeCartera() {
        System.out.println("ingrese su dni");
        String dni = scanner.next();
        System.out.println("ingrese el nombre del instrumento que desea eliminar");
        String nombre = scanner.next();
        inversorService.metodoParaDesuscribirse(dni, nombre);
    }


}
