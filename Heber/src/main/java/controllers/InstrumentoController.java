package controllers;

import exceptions.InstrumentoDuplicadoException;
import exceptions.InstrumentoNoEncontradoException;
import models.AccionFactory;
import models.BonoFactory;
import models.InstrumentoFactory;
import models.InstrumentoFinanciero;
import services.InstrumentoService;

import java.util.InputMismatchException;
import java.util.Scanner;

import contenedorBoot.ContenedorBoot;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class InstrumentoController {

    private final Scanner scanner;
    private final InstrumentoService instrumentoService;
    private static InstrumentoController instrumentoController;

    private InstrumentoController() {
        this.scanner = new Scanner(System.in);
        this.instrumentoService = InstrumentoService.getInstance();
    }

    // Método para obtener la única instancia
    public static InstrumentoController getInstance() {
        if (instrumentoController == null) {
            instrumentoController = new InstrumentoController();
        }
        return instrumentoController;
    }

    // Método para iniciar la gestión
    public void iniciarGestion() {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();

            int opcion = obtenerOpcionValida();

            switch (opcion) {
                case 1:
                    registrarInstrumento();
                    break;
                case 2:
                    consultarInstrumentos();
                    break;
                case 3:
                    /*editarInstrumento();
                    break;
                case 4:
                    eliminarInstrumentoPorNombre();
                    break;*/
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

    private int obtenerOpcionValida() {
        int opcion = -1;
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

        return opcion;
    }

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

    public void registrarInstrumento(){

        // Selección del tipo de instrumento
        InstrumentoFactory factory = seleccionarFabricaInstrumento();

        if (factory != null) {
            try {
                System.out.print("Ingrese el nombre del Instrumento: ");
                String nombreInstrumento = scanner.nextLine();
                verificarDuplicado(nombreInstrumento);
                InstrumentoFinanciero instrumento = factory.crearInstrumento(nombreInstrumento);
                instrumentoService.registrarInstrumento(instrumento);
                System.out.println("Instrumento registrado con éxito.");
            } catch (InstrumentoDuplicadoException | InstrumentoNoEncontradoException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        } else {
            System.out.println("No se pudo registrar el instrumento.");
        }
    }

    private InstrumentoFactory seleccionarFabricaInstrumento() {
        System.out.println("Seleccione el tipo de instrumento a registrar:");
        System.out.println("1. Acción");
        System.out.println("2. Bono");

        int opcion = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        switch (opcion) {
            case 1:
                return new AccionFactory();  // Devuelve la fábrica de Acciones
            case 2:
                return new BonoFactory();  // Devuelve la fábrica de Bonos
            default:
                System.out.println("Opción no válida. Inténtelo de nuevo.");
                return null;
        }
    }

    public void consultarInstrumentos() {
        instrumentoService.consultarInstrumentos();
        System.out.println("¿Desea consultar los detalles de un instrumento específico? (si/no)");
        String respuesta = scanner.nextLine();
        System.out.println("Respuesta leída: '" + respuesta + "'");
        try {
            if (respuesta.equalsIgnoreCase("si")) {
                System.out.print("Introduce el nombre del instrumento: ");
                String nombreInstrumento = scanner.nextLine();
                InstrumentoFinanciero instrumento = buscarInstrumentoPorNombre(nombreInstrumento);

                System.out.println("Detalles del instrumento:");
                System.out.println(instrumento);

            } else {
                System.out.println("Consulta finalizada.");
            }
        } catch (InstrumentoNoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public InstrumentoFinanciero buscarInstrumentoPorNombre(String nombreInstrumento) {
        return instrumentoService.buscarInstrumentoPorNombre(nombreInstrumento);
    }

    public void verificarDuplicado(String nombreInstrumento) {
        instrumentoService.verificarDuplicado(nombreInstrumento);
    }

    /*public void modificarNombre(InstrumentoFinanciero instrumento) {
        instrumentoService.modificarNombre(instrumento);
    }

    public void modificarPrecio(InstrumentoFinanciero instrumento) {
        instrumentoService.modificarPrecio(instrumento);
    }

    public void modificarDividendo(InstrumentoFinanciero instrumento) {
        instrumentoService.modificarDividendo((Accion) instrumento);
    }

    public void modificarTasaInteres(InstrumentoFinanciero instrumento) {
        instrumentoService.modificarTasaInteres((Bono) instrumento);
    }

    public void eliminarInstrumentoPorNombre(String nombreInstrumento) {
        try {
            instrumentoService.eliminarInstrumentoPorNombre(nombreInstrumento);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }*/
}