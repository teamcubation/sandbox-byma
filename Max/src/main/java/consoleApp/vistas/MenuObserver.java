package consoleApp.vistas;

import consoleApp.excepciones.InstrumentoDuplicadoException;
import consoleApp.excepciones.InstrumentoNoEncontradoException;
import consoleApp.excepciones.InversorExistenteException;
import consoleApp.excepciones.InversorNoEncontradoException;
import consoleApp.servicios.ObserverService;

import java.util.Scanner;

public class MenuObserver {
    final static String MSG_MENU_OBSERVER = "Ingrese la operacion que desea hacer: " +
            "\n1. Agregar instumento a mi cartera " +
            "\n2. Eliminar instrumento de mi cartera ";
    private static MenuObserver menuObserver;
    private ObserverService observerService;
    Scanner scanner;
    private MenuObserver() {
        this.scanner = new Scanner(System.in);
        this.observerService = ObserverService.getObserverService();
    }

    public static MenuObserver getMenu() {
        if (menuObserver == null) {
            menuObserver = new MenuObserver();
        }
        return menuObserver;
    }

    public void abrirMenu() {
        System.out.println(MSG_MENU_OBSERVER);
        String operacion = scanner.nextLine();
        while (operacion.equals("1") || operacion.equals("2")) {
            try {
                switch (operacion) {

                    case "1":
                        this.agregarInstrumentoACartera();
                        break;
                    case "2":
                        this.eliminarInstrumentoDeCartera();
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
            System.out.println(MSG_MENU_OBSERVER);
            operacion = scanner.next();
        }
    }

    private void agregarInstrumentoACartera() {
        System.out.println("ingrese su dni");
        String dni = scanner.next();
        System.out.println("ingrese el nombre del instrumento que desea agregar");
        String nombre = scanner.next();
        observerService.metodoParaSuscribirse(dni, nombre);
    }

    public void eliminarInstrumentoDeCartera() {
        System.out.println("ingrese su dni");
        String dni = scanner.next();
        System.out.println("ingrese el nombre del instrumento que desea eliminar");
        String nombre = scanner.next();
        observerService.metodoParaDesuscribirse(dni, nombre);
    }
}