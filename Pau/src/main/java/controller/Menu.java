package controller;

import exceptions.InstrumentoDuplicadoException;
import exceptions.InstrumentoNoEncontradoException;
import exceptions.NoExisteEseTipoDeInstrumentoException;
import service.InstrumentoFinancieroService;
import model.instrumentoFinanciero.TipoInstrumentoFinanciero;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    private boolean continuar;
    private Scanner scanner;
    private InstrumentoFinancieroService instrumentoFinancieroService;
    public Menu() {
        continuar = true;
        scanner = new Scanner(System.in);
        instrumentoFinancieroService = InstrumentoFinancieroService.getInstance();
    }

    public void desplegarMenu() {
        while (continuar) {
            int opcionElegida;
            System.out.println("Ingrese una opcion para utilizar el servicio:\n" +
                    "1. Registrar un nuevo instrumento financiero.\n" +
                    "2. Consultar bonos o acciones.\n" +
                    "3. Editar bono o acción existente.\n" +
                    "4. Eliminar bonos o acciones.\n" +
                    "5. Salir.\n");
            opcionElegida = scanner.nextInt();

            switch (opcionElegida) {
                case 1:
                    this.registrarNuevoInstrumento();
                    break;
                case 2:
                    this.consultarBonosOAcciones();
                    break;
                case 3:
                    this.editarBonoOAccionExistente();
                    break;
                case 4:
                    this.eliminarBonoOAccionExistente();
                case 5:
                    System.out.println("Gracias por utilizar nuestro servicio. Nos vemos! :)\n");
                    continuar = false;
                    break;
                default:
                    System.out.println("La opción ingresada no es valida. Intente nuevamente. \n");
            }

            scanner.close();

        }
    }

    private void editarBonoOAccionExistente() {
        String nombreActual;
        int atributo;
        System.out.println("Ingrese el nombre del instrumento financiero que desea editar:\n");
        nombreActual = scanner.nextLine();
        System.out.println("Ingrese el atributo que desea editar:\n" +
                "1. Nombre\n" +
                "2. Precio\n");
        atributo = scanner.nextInt();

        switch (atributo) {
            case 1:
                String nombreNuevo;
                System.out.println("Ingrese el nuevo nombre del instrumento financiero: \n");
                nombreNuevo = scanner.nextLine();
                try{
                    instrumentoFinancieroService.editarNombreInstrumentoFinanciero(nombreActual, nombreNuevo);
                } catch (InstrumentoNoEncontradoException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case 2:
                double precioNuevo;
                System.out.println("Ingrese el nuevo precio del instrumento financiero: \n");
                precioNuevo = scanner.nextDouble();
                try {
                    instrumentoFinancieroService.editarPrecioInstrumentoFinanciero(nombreActual, precioNuevo);
                } catch (InstrumentoNoEncontradoException e) {
                    System.err.println(e.getMessage()); throw new RuntimeException(e);
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
                break;
            default:
                System.out.println("La opción ingresada no es valida. Lo enviaremos al menu principal. \n");
        }
    }

    private void consultarBonosOAcciones() {
        int tipoConsulta;
        System.out.println("Ingrese la consulta que desea hacer:\n" +
                "\t1. Consultar todos los instrumentos conocidos\n" +
                "\t2. Consultar por un instrumento en particular\n");
        tipoConsulta = scanner.nextInt();

        switch (tipoConsulta){
            case 1:
                System.out.println("Los instrumentos conocidos son los siguientes: \n");
                System.out.println(instrumentoFinancieroService.consultarInstrumentosFinancierosToString());
                break;
            case 2:
                System.out.println("Ingrese el nombre del instrumento financiero a consultar:\n");
                String nombre = scanner.nextLine();
                try {
                    System.out.println(instrumentoFinancieroService.consultarPorUnInstrumentoFinancieroToString(nombre));
                } catch (InstrumentoNoEncontradoException e) {
                    System.err.println(e.getMessage());
                }
                break;
            default:
                System.out.println("La opción ingresada no es valida. Lo enviaremos al menu principal. \n");
        }
        System.out.println("\n");
    }

    private void registrarNuevoInstrumento() {
        InstrumentoFinancieroService instrumentoFinancieroService = InstrumentoFinancieroService.getInstance();
        int tipoInstrumento;
        String nombreInstrumento;
        double precioInstrumento;
        String date;
        LocalDate dateParseada;
        System.out.println("Ingrese el tipo de instrumento que desea registrar:\n" +
                "1. Bono.\n" +
                "2. Accion. \n");
        tipoInstrumento = scanner.nextInt();
        if(tipoInstrumento == 1 || tipoInstrumento == 2){
            System.out.println("Ingrese el nombre del nuevo instrumento: \n");
            nombreInstrumento = scanner.nextLine();
            System.out.println("Ingrese el precio del nuevo instrumento: \n");
            precioInstrumento = scanner.nextDouble();
            System.out.println("Ingrese la fecha de emision del nuevo instrumento con formato yyyy-mm-dd: \n");
            date = scanner.nextLine();
            dateParseada = LocalDate.parse(date);
            TipoInstrumentoFinanciero tipoInstrumentoFinanciero = obtenerTipoInstrumento(tipoInstrumento);
            try {
                System.out.println(instrumentoFinancieroService.registrarInstrumentoFinanciero(nombreInstrumento,precioInstrumento,dateParseada,tipoInstrumentoFinanciero).toString());
            } catch (NoExisteEseTipoDeInstrumentoException e) {
                System.err.println(e.getMessage());
            } catch (InstrumentoNoEncontradoException e) {
                System.err.println(e.getMessage());
            } catch (InstrumentoDuplicadoException e) {
                System.err.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.out.println("La opción ingresada no es valida. Lo enviaremos al menu principal. \n");
        }
    }

    private TipoInstrumentoFinanciero obtenerTipoInstrumento(int intTipoInstrumento) {
        TipoInstrumentoFinanciero tipoInstrumentoFinanciero = null;
        if(intTipoInstrumento == 1){
            tipoInstrumentoFinanciero = TipoInstrumentoFinanciero.BONO;
        } else if(intTipoInstrumento == 2){
            tipoInstrumentoFinanciero = TipoInstrumentoFinanciero.ACCION;
        }
        return tipoInstrumentoFinanciero;
    }

    private void eliminarBonoOAccionExistente() {
    }

    private void validarOpcionElegida(int opciónElegida) {
        String opcion = String.valueOf(opciónElegida);
        if(! opcion.matches("[1234]")) {
            throw new IllegalArgumentException("La opción ingresada no es valida");
        }
    }
}
