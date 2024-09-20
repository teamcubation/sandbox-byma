package org.example.ejercicioGestionAccionesYBonos.controller;

import org.example.ejercicioGestionAccionesYBonos.exception.InstrumentoDuplicadoException;
import org.example.ejercicioGestionAccionesYBonos.exception.InstrumentoNoEncontradoException;
import org.example.ejercicioGestionAccionesYBonos.modelo.InstrumentoFactory;
import org.example.ejercicioGestionAccionesYBonos.modelo.InstrumentoFinanciero;
import org.example.ejercicioGestionAccionesYBonos.modelo.enumsModel.TipoInstrumentoFinanciero;
import org.example.ejercicioGestionAccionesYBonos.repo.InstrumentoFinancieroObserver;
import org.example.ejercicioGestionAccionesYBonos.service.InstrumentoFinancieroServicio;
import org.example.ejercicioGestionAccionesYBonos.service.NotificacionInstrumentoServicio;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class InstrumentoFinancieroController {

    public static final String OPCIÓN_NO_VÁLIDA_POR_FAVOR_INGRESE_UN_NÚMERO_ENTRE_1_Y_3 = "Opción no válida. Por favor, ingrese un número entre 1 y 3.";
    public static final String GRACIAS_POR_USAR_LA_PLATAFORMA_HASTA_PRONTO = "Gracias por usar la plataforma. ¡Hasta pronto!";
    public static final String HA_SELECCIONADO_OPERAR_CON_ACCIONES = "Ha seleccionado operar con ACCIONES.";
    public static final String HA_SELECCIONADO_OPERAR_CON_BONOS = "Ha seleccionado operar con BONOS.";
    public static final String POR_FAVOR_INGRESE_EL_NOMBRE_DEL_INSTRUMENTO_QUE_DESEA_EDITAR = "Por favor, ingrese el nombre del instrumento que desea editar";
    public static final String SELECCIONE_UNA_OPCIÓN = "Seleccione una opción:";
    public static final String EDITAR_NOMBRE = "1. Editar Nombre ";
    public static final String EDITAR_PRECIO = "2. Editar Precio ";
    public static final String INGRESAR_EL_NUEVO_NOMBRE = "Ingresar el nuevo nombre";
    public static final String INGRESAR_EL_NUEVO_PRECIO = "Ingresar el nuevo precio";
    public static final String TIPO_DE_INSTURMENTO_INVALIDO = "Tipo de insturmento invalido";
    public static final String INGRESAR_NOMBRE = "ingresar nombre";
    public static final String INGRESE_PRECIO = "ingrese Precio";
    public static final String POR_FAVOR_SELECCIONE_EL_TIPO_DE_INSTRUMENTO_QUE_DESEA_OPERAR = "Por favor, seleccione el tipo de instrumento que desea operar:";
    public static final String BONOS = "1. Bonos";
    public static final String ACCIONES = "2. Acciones";
    public static final String SALIR = "3. Salir";
    public static final String INGRESE_SU_OPCIÓN_1_3 = "Ingrese su opción (1-3): ";
    public static final String REGISTRAR = "1. Registrar ";
    public static final String CONSULTAR = "2. Consultar ";
    public static final String EDITAR = "3. Editar ";
    public static final String ELIMINAR = "4. Eliminar ";
    public static final String SALIR_SUBMENU = "5. Salir";
    public static final String ERROR_TITULO = "                  ¡ERROR!                   ";
    public static final String DIVISOR_DOBLE = "============================================";
    public static final String DIVISOR_SIMPLE = "--------------------------------------------";
    public static final String VOLVIENDO_AL_MENU_PRINCIPAL = "Volviendo al menu principal...";
    public static final String OPCIÓN_NO_VÁLIDA_POR_FAVOR_INGRESE_UN_NÚMERO_ENTRE_1_Y_5 = "Opción no válida. Por favor, ingrese un número entre 1 y 5.";
    public static final String INGRESE_SU_OPCIÓN_1_5 = "Ingrese su opción (1-5): ";
    public static final String POR_FAVOR_INGRESE_EL_NOMBRE_DEL_INSTRUMENTO = "Por favor, ingrese el nombre del instrumento ";
    public static final String QUE_DESEA_ELIMINAR = " que desea eliminar";
    public static final String ERROR_EL_NOMBRE_INGRESADO_ES_INVALIDO = "Error: El nombre ingresado es invalido!!!";
    private static InstrumentoFinancieroController instancia;
    private InstrumentoFinancieroServicio instrumentoFinancieroServicio;
    private NotificacionInstrumentoServicio notificacionInstrumentoServicio;
    private Scanner scanner;

    private InstrumentoFinancieroController(InstrumentoFinancieroServicio instrumentoFinancieroServicio, NotificacionInstrumentoServicio notificacionInstrumentoServicio) {
        this.instrumentoFinancieroServicio = instrumentoFinancieroServicio;
        this.notificacionInstrumentoServicio = notificacionInstrumentoServicio;
        this.scanner = new Scanner(System.in);
    }

    public static InstrumentoFinancieroController getInstancia(InstrumentoFinancieroServicio servicio, NotificacionInstrumentoServicio notificacionInstrumentoServicio) {
        if (instancia == null) {
            return new InstrumentoFinancieroController(servicio, notificacionInstrumentoServicio);
        }

        return instancia;
    }

    public void iniciarApp() {
        boolean continuar = true;
        TipoInstrumentoFinanciero instrumentoFinancieroSeleccionado;
        while (continuar) {
            this.mostrarMenuInicial();

            try {

                int instrumentoSeleccionado = scanner.nextInt();

                switch (instrumentoSeleccionado) {
                    case 1:

                        System.out.println(HA_SELECCIONADO_OPERAR_CON_BONOS);

                        instrumentoFinancieroSeleccionado = TipoInstrumentoFinanciero.BONO;
                        // Lógica para operar con bonos
                        operarInstrumento(instrumentoFinancieroSeleccionado);
                        break;
                    case 2:

                        System.out.println(HA_SELECCIONADO_OPERAR_CON_ACCIONES);
                        instrumentoFinancieroSeleccionado = TipoInstrumentoFinanciero.ACCION;

                        // Lógica para operar con acciones
                        operarInstrumento(instrumentoFinancieroSeleccionado);
                        break;
                    case 3:

                        System.out.println(GRACIAS_POR_USAR_LA_PLATAFORMA_HASTA_PRONTO);
                        continuar = false; // Termina el ciclo
                        break;
                    default:
                        scanner.nextLine();

                        System.out.println(OPCIÓN_NO_VÁLIDA_POR_FAVOR_INGRESE_UN_NÚMERO_ENTRE_1_Y_3);
                        break;
                }

            } catch (InputMismatchException ex) {
                scanner.nextLine();

                System.err.println(DIVISOR_DOBLE);
                System.err.println("Error: Datos ingresados invalidos!!!");
                System.err.println(DIVISOR_DOBLE);

            } catch (Exception e) {

                scanner.nextLine();

                System.err.println(DIVISOR_DOBLE);

                System.err.println(OPCIÓN_NO_VÁLIDA_POR_FAVOR_INGRESE_UN_NÚMERO_ENTRE_1_Y_3);
                System.err.println(DIVISOR_DOBLE);

            }

        }

        //Fin de la app
        this.scanner.close();
    }

    private void operarInstrumento(TipoInstrumentoFinanciero instrumentoFinancieroSeleccionado) {

        boolean continuarSubmenu = true;

        while (continuarSubmenu) {

            this.mostrarSubMenuOperaciones(instrumentoFinancieroSeleccionado);

            System.out.print(INGRESE_SU_OPCIÓN_1_5);
            try {
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
                        System.out.println(VOLVIENDO_AL_MENU_PRINCIPAL);
                        continuarSubmenu = false; // Termina el ciclo
                        break;
                    default:
                        System.out.println(OPCIÓN_NO_VÁLIDA_POR_FAVOR_INGRESE_UN_NÚMERO_ENTRE_1_Y_5);
                        break;

                }

            } catch (InputMismatchException ex) {
                scanner.nextLine();

                System.err.println(DIVISOR_DOBLE);
                System.err.println("Error: Datos ingresados invalidos!!!");
                System.err.println(DIVISOR_DOBLE);

            } catch (Exception e) {
                scanner.nextLine();

                System.err.println(DIVISOR_DOBLE);
                System.err.println(OPCIÓN_NO_VÁLIDA_POR_FAVOR_INGRESE_UN_NÚMERO_ENTRE_1_Y_5);
                System.err.println(DIVISOR_DOBLE);

            }

        }


    }

    private void eliminarInstrumento(TipoInstrumentoFinanciero instrumentoFinancieroSeleccionado) {
        System.out.println(POR_FAVOR_INGRESE_EL_NOMBRE_DEL_INSTRUMENTO + instrumentoFinancieroSeleccionado + QUE_DESEA_ELIMINAR);


        try {
            scanner.nextLine();
            String nombreInstrumento = scanner.nextLine();
            this.instrumentoFinancieroServicio.eliminarInstrumento(nombreInstrumento, instrumentoFinancieroSeleccionado);
        } catch (InstrumentoNoEncontradoException e) {
            System.err.println(DIVISOR_DOBLE);
            System.err.println(ERROR_TITULO);
            System.err.println(DIVISOR_SIMPLE);
            System.err.println(e.getMessage());
            System.err.println(DIVISOR_SIMPLE);
            System.err.println(DIVISOR_DOBLE);
        } catch (Exception e) {
            System.err.println(DIVISOR_DOBLE);
            System.err.println(ERROR_EL_NOMBRE_INGRESADO_ES_INVALIDO);
            System.err.println(DIVISOR_DOBLE);

        }

    }

    private void editarInstrumento(TipoInstrumentoFinanciero instrumentoFinancieroSeleccionado) {
        System.out.println(POR_FAVOR_INGRESE_EL_NOMBRE_DEL_INSTRUMENTO_QUE_DESEA_EDITAR);

        try {
            scanner.nextLine();

            String nombreInstrumento = scanner.nextLine();

            System.out.println(DIVISOR_DOBLE);
            System.out.println(SELECCIONE_UNA_OPCIÓN);
            System.out.println(EDITAR_NOMBRE);
            System.out.println(EDITAR_PRECIO);
            System.out.println(DIVISOR_DOBLE);


            int opcionSeleccionada = scanner.nextInt();

            switch (opcionSeleccionada) {
                case 1 -> {
                    System.out.println(INGRESAR_EL_NUEVO_NOMBRE);
                    scanner.nextLine();

                    String nuevoNombre = scanner.nextLine();

                    try {
                        this.instrumentoFinancieroServicio.editarNombreInstrumento(nuevoNombre, nombreInstrumento, instrumentoFinancieroSeleccionado);
                    } catch (InstrumentoNoEncontradoException e) {
                        System.err.println(DIVISOR_DOBLE);
                        System.err.println(ERROR_TITULO);
                        System.err.println(DIVISOR_SIMPLE);
                        System.err.println(e.getMessage());
                        System.err.println(DIVISOR_SIMPLE);
                        System.err.println(DIVISOR_DOBLE);
                    }
                }
                case 2 -> {

                    System.out.println(INGRESAR_EL_NUEVO_PRECIO);


                    double nuevoPrecio = scanner.nextDouble();

                    try {
                        this.instrumentoFinancieroServicio.editarPrecioInstrumento(nuevoPrecio, nombreInstrumento, instrumentoFinancieroSeleccionado);

                        Optional<InstrumentoFinanciero> instrumentoFinancieroEditado = this.instrumentoFinancieroServicio.listarInstrumentoPorNombre(nombreInstrumento, instrumentoFinancieroSeleccionado);

                        //notifico a inversores al editare el precio
                        this.notificarObservadores(instrumentoFinancieroEditado.get());
                    } catch (InstrumentoNoEncontradoException e) {
                        System.err.println(DIVISOR_DOBLE);
                        System.err.println(ERROR_TITULO);
                        System.err.println(DIVISOR_SIMPLE);
                        System.err.println(e.getMessage());
                        System.err.println(DIVISOR_SIMPLE);
                        System.err.println(DIVISOR_DOBLE);
                    }
                }
            }
        } catch (InputMismatchException e) {
            scanner.nextLine();

            System.err.println(DIVISOR_DOBLE);
            System.err.println(OPCIÓN_NO_VÁLIDA_POR_FAVOR_INGRESE_UN_NÚMERO_ENTRE_1_Y_3);
            System.err.println(DIVISOR_DOBLE);
        }


    }

    private void consultarInstrumentos(TipoInstrumentoFinanciero instrumentoFinancieroSeleccionado) {

        switch (instrumentoFinancieroSeleccionado) {
            case ACCION ->
                    this.instrumentoFinancieroServicio.listarAcciones().forEach(i -> System.out.println(i.mostrarInstrumento()));
            case BONO ->
                    this.instrumentoFinancieroServicio.listarBonos().forEach(i -> System.out.println(i.mostrarInstrumento()));
            default -> System.out.println(TIPO_DE_INSTURMENTO_INVALIDO);
        }

    }

    private void registrarInstrumento(TipoInstrumentoFinanciero instrumentoFinancieroSeleccionado) {
        scanner.nextLine();

        System.out.println(INGRESAR_NOMBRE);
        String nombre = scanner.nextLine();


        System.out.println(INGRESE_PRECIO);
        double precio = scanner.nextDouble();

        InstrumentoFinanciero nuevoInstrumento = InstrumentoFactory.nuevoInstrumento(nombre, precio, instrumentoFinancieroSeleccionado);

        try {

            instrumentoFinancieroServicio.registrarNuevoInstrumento(nuevoInstrumento, instrumentoFinancieroSeleccionado);
        } catch (InstrumentoDuplicadoException e) {

            scanner.nextLine();

            System.err.println(DIVISOR_DOBLE);
            System.err.println(ERROR_TITULO);
            System.err.println(DIVISOR_SIMPLE);
            System.err.println(e.getMessage());
            System.err.println(DIVISOR_SIMPLE);
            System.err.println(DIVISOR_DOBLE);
        }


    }

    private void mostrarMenuInicial() {

        // Mostrar el menú de opciones
        System.out.println(DIVISOR_DOBLE);
        System.out.println(POR_FAVOR_SELECCIONE_EL_TIPO_DE_INSTRUMENTO_QUE_DESEA_OPERAR);
        System.out.println(BONOS);
        System.out.println(ACCIONES);
        System.out.println(SALIR);
        System.out.println(DIVISOR_DOBLE);

        // Leer la entrada del usuario
        System.out.print(INGRESE_SU_OPCIÓN_1_3);
    }

    private void mostrarSubMenuOperaciones(TipoInstrumentoFinanciero instrumentoFinancieroSeleccionado) {
        System.out.println(DIVISOR_DOBLE);
        System.out.println(SELECCIONE_UNA_OPCIÓN);
        System.out.println(REGISTRAR + instrumentoFinancieroSeleccionado);
        System.out.println(CONSULTAR + instrumentoFinancieroSeleccionado);
        System.out.println(EDITAR + instrumentoFinancieroSeleccionado);
        System.out.println(ELIMINAR + instrumentoFinancieroSeleccionado);
        System.out.println(SALIR_SUBMENU);
        System.out.println(DIVISOR_DOBLE);
    }


    //Observable
    public void registrarObservador(InstrumentoFinancieroObserver inversor) {
        this.notificacionInstrumentoServicio.registrarObservador(inversor);
    }

    public void eliminarObservador(InstrumentoFinancieroObserver inversor) {

        this.notificacionInstrumentoServicio.eliminarObservador(inversor);
    }

    public void notificarObservadores(InstrumentoFinanciero instrumentoFinanciero) {
        this.notificacionInstrumentoServicio.notificarObservadores(instrumentoFinanciero);
    }
}
