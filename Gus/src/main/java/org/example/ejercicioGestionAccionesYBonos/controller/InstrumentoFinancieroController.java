package org.example.ejercicioGestionAccionesYBonos.controller;

import org.example.ejercicioGestionAccionesYBonos.modelo.InstrumentoFactory;
import org.example.ejercicioGestionAccionesYBonos.modelo.InstrumentoFinanciero;
import org.example.ejercicioGestionAccionesYBonos.repo.InstrumentoFinancieroObserver;
import org.example.ejercicioGestionAccionesYBonos.service.InstrumentoFinancieroObservableServicio;
import org.example.ejercicioGestionAccionesYBonos.service.InstrumentoFinancieroServicio;
import org.example.ejercicioGestionAccionesYBonos.exception.InstrumentoDuplicadoException;
import org.example.ejercicioGestionAccionesYBonos.exception.InstrumentoNoEncontradoException;
import org.example.ejercicioGestionAccionesYBonos.util.TipoInstrumentoFinanciero;

import java.util.Optional;
import java.util.Scanner;

public class InstrumentoFinancieroController {

    private InstrumentoFinancieroServicio instrumentoFinancieroServicio;


    private InstrumentoFinancieroObservableServicio instrumentoFinancieroObservableServicio;

    private Scanner scanner;

    private static InstrumentoFinancieroController instancia;

    private InstrumentoFinancieroController(InstrumentoFinancieroServicio instrumentoFinancieroServicio, InstrumentoFinancieroObservableServicio instrumentoFinancieroObservableServicio) {
        this.instrumentoFinancieroServicio = instrumentoFinancieroServicio;
        this.instrumentoFinancieroObservableServicio = instrumentoFinancieroObservableServicio;
        this.scanner = new Scanner(System.in);
    }

    public static InstrumentoFinancieroController getInstancia(InstrumentoFinancieroServicio servicio, InstrumentoFinancieroObservableServicio instrumentoFinancieroObservableServicio) {
        if (instancia == null) {
            return new InstrumentoFinancieroController(servicio, instrumentoFinancieroObservableServicio);
        }

        return instancia;
    }

    public void iniciarApp() {
        boolean continuar = true;
        TipoInstrumentoFinanciero instrumentoFinancieroSeleccionado;
        while (continuar) {
            this.mostrarMenuInicial();

            int instrumentoSeleccionado = scanner.nextInt();
            switch (instrumentoSeleccionado) {
                case 1:
                    System.out.println("Ha seleccionado operar con BONOS.");

                    instrumentoFinancieroSeleccionado = TipoInstrumentoFinanciero.BONO;
                    // Lógica para operar con bonos
                    operarInstrumento(instrumentoFinancieroSeleccionado);
                    break;
                case 2:
                    System.out.println("Ha seleccionado operar con ACCIONES.");
                    instrumentoFinancieroSeleccionado = TipoInstrumentoFinanciero.ACCION;

                    // Lógica para operar con acciones
                    operarInstrumento(instrumentoFinancieroSeleccionado);
                    break;
                case 3:
                    System.out.println("Gracias por usar la plataforma. ¡Hasta pronto!");
                    continuar = false; // Termina el ciclo
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese un número entre 1 y 3.");
                    break;
            }

        }
    }

    private void operarInstrumento(TipoInstrumentoFinanciero instrumentoFinancieroSeleccionado) {

        boolean continuarSubmenu = true;

        while (continuarSubmenu) {

            this.mostrarSubMenuOperaciones(instrumentoFinancieroSeleccionado);

            System.out.print("Ingrese su opción (1-5): ");
            int opcionSubmenu = scanner.nextInt();

            switch (opcionSubmenu) {
                case 1:
                    registrarInstrumento(instrumentoFinancieroSeleccionado);
                    break;
                case 2:
                    consultarInstrumentos(instrumentoFinancieroSeleccionado);
                    break;
                case 3:
                    editarInstrumento(instrumentoFinancieroSeleccionado);
                    break;
                case 4:
                    eliminarInstrumento(instrumentoFinancieroSeleccionado);
                    break;
                case 5:
                    System.out.println("Gracias por usar la plataforma. ¡Hasta pronto!");
                    continuarSubmenu = false; // Termina el ciclo
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese un número entre 1 y 5.");
                    break;

            }
        }


    }

    private void eliminarInstrumento(TipoInstrumentoFinanciero instrumentoFinancieroSeleccionado) {
        System.out.println("Por favor, ingrese el nombre del instrumento " + instrumentoFinancieroSeleccionado + " que desea eliminar");
        scanner.nextLine();
        String nombreInstrumento = scanner.nextLine();

        try {
            this.instrumentoFinancieroServicio.eliminarInstrumento(nombreInstrumento);
        } catch (InstrumentoNoEncontradoException e) {
            System.err.println("============================================");
            System.err.println("                  ¡ERROR!                   ");
            System.err.println("--------------------------------------------");
            System.err.println(e.getMessage());
            System.err.println("--------------------------------------------");
            System.err.println("============================================");
        }

    }

    private void editarInstrumento(TipoInstrumentoFinanciero instrumentoFinancieroSeleccionado) {
        System.out.println("Por favor, ingrese el nombre del instrumento que desea editar");
        scanner.nextLine();
        String nombreInstrumento = scanner.nextLine();

        System.out.println("=================================");
        System.out.println("Seleccione una opción:");
        System.out.println("1. Editar Nombre ");
        System.out.println("2. Editar Precio ");

        int opcionSeleccionada = scanner.nextInt();

        switch (opcionSeleccionada) {
            case 1 -> {
                System.out.println("Ingresar el nuevo nombre");

                scanner.nextLine();
                String nuevoNombre = scanner.nextLine();

                try {
                    this.instrumentoFinancieroServicio.editarNombreInstrumento(nuevoNombre, nombreInstrumento);
                } catch (InstrumentoNoEncontradoException e) {
                    System.err.println("============================================");
                    System.err.println("                  ¡ERROR!                   ");
                    System.err.println("--------------------------------------------");
                    System.err.println(e.getMessage());
                    System.err.println("--------------------------------------------");
                    System.err.println("============================================");
                }
            }
            case 2 -> {

                System.out.println("Ingresar el nuevo precio");

                double nuevoPrecio = scanner.nextDouble();

                try {
                    this.instrumentoFinancieroServicio.editarPrecioInstrumento(nuevoPrecio, nombreInstrumento);

                    Optional<InstrumentoFinanciero> instrumentoFinancieroEditado = this.instrumentoFinancieroServicio.listarInstrumentoPorNombre(nombreInstrumento);

                    //notifico a inversores al editare el precio
                    this.notificarObservadores(instrumentoFinancieroEditado.get());
                } catch (InstrumentoNoEncontradoException e) {
                    System.err.println("============================================");
                    System.err.println("                  ¡ERROR!                   ");
                    System.err.println("--------------------------------------------");
                    System.err.println(e.getMessage());
                    System.err.println("--------------------------------------------");
                    System.err.println("============================================");
                }
            }
        }


    }

    private void consultarInstrumentos(TipoInstrumentoFinanciero instrumentoFinancieroSeleccionado) {

        switch (instrumentoFinancieroSeleccionado) {
            case ACCION ->
                    this.instrumentoFinancieroServicio.listarAcciones().forEach(i -> System.out.println(i.mostrarInstrumento()));
            case BONO ->
                    this.instrumentoFinancieroServicio.listarBonos().forEach(i -> System.out.println(i.mostrarInstrumento()));
            default -> System.out.println("Tipo de insturmento invalido");
        }

    }

    private void registrarInstrumento(TipoInstrumentoFinanciero instrumentoFinancieroSeleccionado) {
        System.out.println("ingresar nombre");
        scanner.nextLine();
        String nombre = scanner.nextLine();
        System.out.println("ingrese Precio");
        double precio = scanner.nextDouble();

        InstrumentoFinanciero nuevoInstrumento = InstrumentoFactory.nuevoInstrumento(nombre, precio, instrumentoFinancieroSeleccionado);

        try {
            instrumentoFinancieroServicio.registrarNuevoInstrumento(nuevoInstrumento);
        } catch (InstrumentoDuplicadoException e) {
            System.err.println("============================================");
            System.err.println("                  ¡ERROR!                   ");
            System.err.println("--------------------------------------------");
            System.err.println(e.getMessage());
            System.err.println("--------------------------------------------");
            System.err.println("============================================");
        }


    }

    private void mostrarMenuInicial() {
        System.out.println("¡Bienvenido a la plataforma de operaciones financieras!");

        // Mostrar el menú de opciones
        System.out.println("=================================");
        System.out.println("Por favor, seleccione el tipo de instrumento que desea operar:");
        System.out.println("1. Bonos");
        System.out.println("2. Acciones");
        System.out.println("3. Salir");
        System.out.println("=================================");

        // Leer la entrada del usuario
        System.out.print("Ingrese su opción (1-3): ");
    }

    private void mostrarSubMenuOperaciones(TipoInstrumentoFinanciero instrumentoFinancieroSeleccionado) {
        System.out.println("=================================");
        System.out.println("Seleccione una opción:");
        System.out.println("1. Registrar " + instrumentoFinancieroSeleccionado);
        System.out.println("2. Consultar " + instrumentoFinancieroSeleccionado);
        System.out.println("3. Editar " + instrumentoFinancieroSeleccionado);
        System.out.println("4. Eliminar " + instrumentoFinancieroSeleccionado);
        System.out.println("5. Salir");
        System.out.println("=================================");
    }

    //Observable
    public void registrarObservador(InstrumentoFinancieroObserver inversor){
        this.instrumentoFinancieroObservableServicio.registrarObservador(inversor);
    }

    public void eliminarObservador(InstrumentoFinancieroObserver inversor) {

        this.instrumentoFinancieroObservableServicio.eliminarObservador(inversor);
    }

    public void notificarObservadores(InstrumentoFinanciero instrumentoFinanciero) {
        this.instrumentoFinancieroObservableServicio.notificarObservadores(instrumentoFinanciero);
    }
    }
