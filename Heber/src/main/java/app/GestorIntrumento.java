package app;

import controllers.InstrumentoController;
import exceptions.InstrumentoNoEncontradoException;
import models.Accion;
import models.Bono;
import models.InstrumentoFactory;
import models.InstrumentoFinanciero;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestorIntrumento {
    private static GestorIntrumento gestor;
    private Scanner scanner;
    private InstrumentoController instrumentoController = new InstrumentoController();

    private GestorIntrumento() {
        this.scanner = new Scanner(System.in);
    }

    // Método para obtener la única instancia
    public static GestorIntrumento getInstance() {
        if (gestor == null) {
            gestor = new GestorIntrumento();
        }
        return gestor;
    }

    // Método para iniciar la gestión
    // Método para iniciar la gestión
    public void iniciarGestion() {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();  // Utilizamos el método modularizado

            int opcion = -1; // Valor inicial fuera del rango válido
            boolean opcionValida = false;

            while (!opcionValida) {
                try {
                    opcion = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    if (opcion < 1 || opcion > 5) {
                        System.out.println("Opción no válida. Por favor, ingrese un número entre 1 y 5.");
                    } else {
                        opcionValida = true; // Salir del bucle si la opción es válida
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada no válida. Por favor, ingrese un número.");
                    scanner.nextLine(); // Limpiar el buffer después del error
                }
            }

            switch (opcion) {
                case 1:
                    registrarInstrumento();
                    break;
                case 2:
                    consultarInstrumentos();
                    break;
                case 3:
                    editarInstrumento();
                    break;
                case 4:
                    eliminarInstrumentoPorNombre();
                    break;
                case 5:
                    continuar = false;
                    System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
                    break;
                default:
                    // Este caso ya está cubierto por el bucle de validación
                    break;
            }
        }
    }

    // Modificar el menú usando StringBuilder para mejorar la legibilidad
    private void mostrarMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("Seleccione una opción:\n")
                .append("1. Registrar Instrumento\n")
                .append("2. Consultar Acciones y Bonos\n")
                .append("3. Editar Acción o Bono\n")
                .append("4. Eliminar Acción o Bono\n")
                .append("5. Salir\n");

        System.out.println(menu);
    }

    private void registrarInstrumento() {
        try {
            instrumentoController.registrarInstrumento();
        } catch (Exception e) {
            System.out.println("Ocurrio un error intente nuevamente ");
        }

    }

    private void consultarInstrumentos() {
        instrumentoController.consultarInstrumentos();
    }

    private void editarInstrumento() {
        //PODRIA hacerlo aca ya que tengo la instancia de intrumento pero no se si corresponde
        System.out.print("Introduce el nombre del instrumento que deseas editar: ");
        String nombreInstrumento = scanner.nextLine();

        try {
            InstrumentoFinanciero instrumento = instrumentoController.buscarInstrumentoPorNombre(nombreInstrumento);

            if (instrumento == null) {
                throw new InstrumentoNoEncontradoException("Instrumento con el nombre " + nombreInstrumento + " no encontrado");
            }

            // Mostrar menú para seleccionar qué atributo modificar
            System.out.println("Seleccione el atributo que desea modificar:");
            System.out.println("1. Nombre");
            System.out.println("2. Precio");

            // Mostrar atributos adicionales dependiendo del tipo de instrumento
            if (instrumento instanceof Accion) {
                System.out.println("3. Dividendo");
            } else if (instrumento instanceof Bono) {
                System.out.println("3. Tasa de interés");
            }else{
               // throw new Exception("tiene que ser un bono o una accion");
            }

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    instrumentoController.modificarNombre(instrumento);
                    break;
                case 2:
                    instrumentoController.modificarPrecio(instrumento);
                    break;
                case 3:
                    if (instrumento instanceof Accion) {
                        instrumentoController.modificarDividendo((Accion) instrumento);
                    } else if (instrumento instanceof Bono) {
                        instrumentoController.modificarTasaInteres((Bono) instrumento);
                    }
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

            System.out.println("Instrumento editado con éxito.");
        } catch (InstrumentoNoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no válida.");
            scanner.nextLine();
        }
    }


    private void eliminarInstrumentoPorNombre() {
        System.out.print("Ingrese el nombre del instrumento a eliminar: ");
        String nombreInstrumento = scanner.nextLine();

        instrumentoController.eliminarInstrumentoPorNombre(nombreInstrumento);
    }

}
