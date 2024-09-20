package org.example.controller;

import org.example.exception.InstrumentoNoEncontradoException;
import org.example.factory.InstrumentoFinancieroFactory;
import org.example.model.InstrumentoFinanciero;
import org.example.model.Inversor;
import org.example.service.InstrumentoFinancieroService;
import org.example.service.NotificadorService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuController {

    public static final String ERROR_MSG_INPUT_MISMATCH = "Error: Ingrese un número válido.";
    public static final String ERROR_MSG_INVALID_OPTION = "Opción no válida.";

    private final InstrumentoFinancieroService instrumentoFinancieroService = new InstrumentoFinancieroService();
    private final NotificadorService notificadorService = NotificadorService.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    public void iniciarMenu() {
        boolean salir = false;
        while (!salir) {
            System.out.println("Menu de Gestion de Instrumentos Financieros");
            System.out.println("1. Registrar Instrumento");
            System.out.println("2. Consultar Instrumentos");
            System.out.println("3. Editar Instrumento");
            System.out.println("4. Eliminar Instrumento");
            System.out.println("5. Suscribir Inversor");
            System.out.println("6. Salir");

            try{
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        registrarInstrumento();
                        break;
                    case 2:
                        consultarInstrumento();
                        break;
                    case 3:
                        editarInstrumento();
                        break;
                    case 4:
                        eliminarInstrumento();
                        break;
                    case 5:
                        suscribirInversor();
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println(ERROR_MSG_INVALID_OPTION);
                }
            } catch (InputMismatchException e) {
                System.out.println(ERROR_MSG_INPUT_MISMATCH);
                scanner.nextLine();
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void suscribirInversor() {
        System.out.print("Ingrese el nombre del inversor: ");
        String nombreInversor = scanner.nextLine();
        System.out.print("Ingrese el nombre del instrumento al que se va a suscribir: ");
        String nombreInstrumento = scanner.nextLine();

        Inversor inversor = new Inversor(nombreInversor);
        InstrumentoFinanciero instrumento = instrumentoFinancieroService.buscarInstrumentoPorNombre(nombreInstrumento);

        if (instrumento != null) {
            notificadorService.agregarObservador(nombreInstrumento, inversor);
            System.out.println("Inversor suscrito correctamente.");
        } else {
            System.out.println("Instrumento no encontrado.");
        }
    }

    private void eliminarInstrumento() {
        System.out.print("Ingrese el nombre del instrumento a eliminar: ");
        String nombre = scanner.nextLine();
        try{
            instrumentoFinancieroService.eliminarInstrumento(nombre);
            System.out.println("Instrumento eliminado correctamente.");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void editarInstrumento() {
        System.out.print("Ingrese el nombre del instrumento a editar: ");
        String nombre = scanner.nextLine();
        System.out.println("¿Que atributo desea modificar?");
        System.out.println("Ingrese el texto correspondiente a la opocion desada: ");
        System.out.println("Nombre");
        System.out.println("Precio");
        System.out.println("Tasa de interes (solo para Bonos)");
        String atributo = scanner.nextLine();
        System.out.print("Ingrese el nuevo valor para " + atributo + ": ");
        String nuevoValor = scanner.nextLine();

        try{
            instrumentoFinancieroService.editarInstrumento(nombre, atributo, nuevoValor);
            System.out.println("Instrumento editado correctamente.");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MSG_INPUT_MISMATCH);
        } catch (InstrumentoNoEncontradoException | IllegalArgumentException e) {
            throw new RuntimeException("Error al editar el instrumento: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void consultarInstrumento() {
        System.out.println("Seleccione una opción: ");
        System.out.println("1. Ver todos los instrumentos");
        System.out.println("2. Buscar un instrumento por nombre");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        try{
            if (opcion == 1) {
                mostrarInstrumentoFinancieroList(instrumentoFinancieroService.listarTodosLosInstrumentos());
            } else if (opcion == 2) {
                mostrarInstrumentoFinanciero();
            } else {
                System.out.println(ERROR_MSG_INVALID_OPTION);
            }
        } catch (RuntimeException e) {
            System.out.println("Error al consultar los instrumentos: " + e.getMessage());
        }
    }

    private void mostrarInstrumentoFinanciero() {
        System.out.println("Ingrese el nombre del instrumento: ");
        String nombre = scanner.nextLine();
        InstrumentoFinanciero instrumento = instrumentoFinancieroService.buscarInstrumentoPorNombre(nombre);
        if (instrumento != null) {
            System.out.println(instrumento.toString());
        } else {
            System.out.println("Instrumento no encontrado.");
        }
    }

    private void mostrarInstrumentoFinancieroList(List<InstrumentoFinanciero> instrumentoFinancieroList) {
        for (InstrumentoFinanciero instrumento : instrumentoFinancieroList) {
            System.out.println(instrumento.toString());
        }
    }

    private void registrarInstrumento() {
        System.out.println("Ingrese el tipo de instrumento (Accion/Bono): ");
        String tipo = scanner.nextLine();
        System.out.println("Ingrese el nombre del instrumento: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el precio del instrumento: ");
        double precio = scanner.nextDouble();

        try{
            InstrumentoFinanciero instrumento;
            if ("Bono".equalsIgnoreCase(tipo)) {
                System.out.println("Ingrese la tasa de interés del bono: ");
                double tasaDeInteres = scanner.nextDouble();
                instrumento = InstrumentoFinancieroFactory.crearInstrumento(tipo, nombre, precio, tasaDeInteres);
            } else {
                instrumento = InstrumentoFinancieroFactory.crearInstrumento(tipo, nombre, precio, null);
            }
            instrumentoFinancieroService.registrarInstrumento(instrumento);
            System.out.println("Instrumento registrado correctamente.");
        } catch (RuntimeException e) {
            System.out.println("Error al registrar el instrumento: " + e.getMessage());
        }
    }
}
