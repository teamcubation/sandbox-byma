package org.example.ejercicioGestionAccionesYBonos.app;

import org.example.ejercicioGestionAccionesYBonos.modelo.InstrumentoFactory;
import org.example.ejercicioGestionAccionesYBonos.modelo.InstrumentoFinanciero;
import org.example.ejercicioGestionAccionesYBonos.repo.InstrumentoFinancieroObserver;
import org.example.ejercicioGestionAccionesYBonos.service.InstrumentoFinancieroService;
import org.example.ejercicioGestionAccionesYBonos.util.InstrumentoDuplicadoException;
import org.example.ejercicioGestionAccionesYBonos.util.InstrumentoNoEncontradoException;
import org.example.ejercicioGestionAccionesYBonos.util.TipoInstrumentoFinanciero;

import java.util.Optional;
import java.util.Scanner;

public class AplicacionAccionesYBonos {

    private InstrumentoFinancieroService instrumentoFinancieroService;
    private Scanner scanner;

    private static AplicacionAccionesYBonos instancia = null;

    int instrumentoSeleccionado;
    TipoInstrumentoFinanciero instrumentoFinancieroSeleccionado;


    private AplicacionAccionesYBonos() {
        this.instrumentoFinancieroService = InstrumentoFinancieroService.getInstancia();
        this.scanner = new Scanner(System.in);
        this.instrumentoSeleccionado = 0;
    }

    public static AplicacionAccionesYBonos obtenerInstancia() {
        if (instancia == null) {
            return new AplicacionAccionesYBonos();
        }

        return instancia;
    }


    public void iniciar() {
        boolean continuar = true;

        while (continuar) {

            mostrarMenuInicial();
            instrumentoSeleccionado = scanner.nextInt();


            // Procesar la opción seleccionada
            switch (instrumentoSeleccionado) {
                case 1:
                    System.out.println("Ha seleccionado operar con BONOS.");

                    instrumentoFinancieroSeleccionado = TipoInstrumentoFinanciero.BONO;
                    // Lógica para operar con bonos
                    operarInstrumento(continuar);
                    break;
                case 2:
                    System.out.println("Ha seleccionado operar con ACCIONES.");
                    instrumentoFinancieroSeleccionado = TipoInstrumentoFinanciero.ACCION;

                    // Lógica para operar con acciones
                    operarInstrumento(continuar);
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

    private void operarInstrumento(boolean continuar) {


        while (continuar) {

            mostrarSubMenuOperaciones(instrumentoFinancieroSeleccionado);


            // Leer la entrada del usuario
            System.out.print("Ingrese su opción (1-5): ");
            int opcion = scanner.nextInt();

            // Procesar la opción seleccionada
            switch (opcion) {
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
                    continuar = false; // Termina el ciclo
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese un número entre 1 y 5.");
                    break;

            }

        }

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

    private void eliminarInstrumento(TipoInstrumentoFinanciero instrumentoFinancieroSeleccionado) {
        this.consultarInstrumentos(instrumentoFinancieroSeleccionado);
        System.out.println("Por favor, ingrese el nombre de la accion que desea eliminar");

        scanner.nextLine();
        String nombreInstrumento = scanner.nextLine();

        try {
            instrumentoFinancieroService.eliminarInstrumento(nombreInstrumento);
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

        this.consultarInstrumentos(instrumentoFinancieroSeleccionado);
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
                    this.instrumentoFinancieroService.editarNombreInstrumento(nuevoNombre, nombreInstrumento);
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
                    this.instrumentoFinancieroService.editarPrecioInstrumento(nuevoPrecio, nombreInstrumento);

                    Optional<InstrumentoFinanciero> instrumentoFinancieroEditado = this.instrumentoFinancieroService.listarInstrumentoPorNombre(nombreInstrumento);

                    this.instrumentoFinancieroService.notificarObservadores(instrumentoFinancieroEditado.get());
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
                    this.instrumentoFinancieroService.consultarAcciones().forEach(i -> System.out.println(i.mostrarInstrumento()));
            case BONO ->
                    this.instrumentoFinancieroService.consultarBonos().forEach(i -> System.out.println(i.mostrarInstrumento()));
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
            instrumentoFinancieroService.registrarNuevoInstrumento(nuevoInstrumento);
        } catch (InstrumentoDuplicadoException e) {
            System.err.println("============================================");
            System.err.println("                  ¡ERROR!                   ");
            System.err.println("--------------------------------------------");
            System.err.println(e.getMessage());
            System.err.println("--------------------------------------------");
            System.err.println("============================================");
        }
    }

    public void registrarObservador(InstrumentoFinancieroObserver observador) {
        instrumentoFinancieroService.registrarObservador(observador);
    }

    public void eliminarObservador(InstrumentoFinancieroObserver observador) {
        instrumentoFinancieroService.eliminarObservador(observador);
    }


}
