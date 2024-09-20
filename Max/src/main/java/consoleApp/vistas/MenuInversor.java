package consoleApp.vistas;

import consoleApp.excepciones.InstrumentoDuplicadoException;
import consoleApp.excepciones.InstrumentoNoEncontradoException;
import consoleApp.excepciones.InversorExistenteException;
import consoleApp.excepciones.InversorNoEncontradoException;
import consoleApp.servicios.InversorService;

import java.util.Scanner;

public class MenuInversor {
    final static String MSG_MENU_INVERSOR = "Ingrese la operacion que desea hacer: " +
            "\n1. Registrar inversor " +
            "\n2. Eliminar inversor " +
            "\n3. Modificar inversor" +
            "\n4. Consultar instrumentos";
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
                operacion.equals("4")) {
            try {
                switch (operacion) {
                    case "1":
                        this.registrarInversor();
                        break;
                    case "2":
                        this.eliminarInversor();
                        break;
                    case "3":
                        this.modificarInversor();
                        break;
                    case "4":
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
        System.out.println("ingrese su dni");
        inversorService.eliminarInversor(scanner.next());
    }

    public void modificarInversor() {
        System.out.println("ingrese su dni");
        String dni = scanner.next();
        System.out.println("ingrese que desea modificar: " +
                "\n1. Nombre" +
                "\n2. Dni");
        String variable = scanner.next();
        System.out.println("ingrese el nuevo valor");
        String modificacion = scanner.next();
        inversorService.modificarInversor(variable, modificacion, dni);
    }
}
