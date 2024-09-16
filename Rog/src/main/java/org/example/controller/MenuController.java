package org.example.controller;

import org.example.exception.InstrumentoNoEncontradoException;
import org.example.factory.InstrumentoFinancieroFactory;
import org.example.model.InstrumentoFinanciero;
import org.example.service.InstrumentoFinancieroService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuController {
    private final InstrumentoFinancieroService instrumentoFinancieroService = new InstrumentoFinancieroService();
    private final Scanner scanner = new Scanner(System.in);

    public void iniciarMenu() {
        boolean salir = false;
        while (!salir) {
            System.out.println("Menu de Gestion de Instrumentos Financieros");
            System.out.println("1. Registrar Instrumento");
            System.out.println("2. Consultar Instrumentos");
            System.out.println("3. Editar Instrumento");
            System.out.println("4. Eliminar Instrumento");
            System.out.println("5. Salir");

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
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un numero valido.");
                scanner.nextLine();
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
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
        String nuevoValorStr = scanner.nextLine();

        try{
            instrumentoFinancieroService.editarInstrumento(nombre, atributo, nuevoValorStr);
            System.out.println("Instrumento editado correctamente.");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El valor ingresado no es un numero valido. Por favor, ingrese un valor numerico.");
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
                for (InstrumentoFinanciero instrumento : instrumentoFinancieroService.listarTodosLosInstrumentos()) {
                    System.out.println(instrumento);
                }
            } else if (opcion == 2) {
                System.out.println("Ingrese el nombre del instrumento: ");
                String nombre = scanner.nextLine();
                InstrumentoFinanciero instrumento = instrumentoFinancieroService.buscarInstrumentoPorNombre(nombre);
                if (instrumento != null) {
                    System.out.println(instrumento);
                } else {
                    System.out.println("Instrumento no encontrado.");
                }
            } else {
                System.out.println("Opción no válida.");
            }
        } catch (RuntimeException e) {
            System.out.println("Error al consultar los instrumentos: " + e.getMessage());
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
