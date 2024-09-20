package consoleversion.controller;

import consoleversion.exceptions.InstrumentoDuplicadoException;
import consoleversion.exceptions.InstrumentoNoEncontradoException;
import consoleversion.exceptions.InversorYaRegistradoException;
import consoleversion.exceptions.NoExisteEseTipoDeInstrumentoException;
import consoleversion.model.Inversor;
import consoleversion.model.instrumentoFinanciero.InstrumentoFinanciero;
import consoleversion.service.InstrumentoFinancieroService;
import consoleversion.model.instrumentoFinanciero.TipoInstrumentoFinanciero;
import consoleversion.service.InversorService;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    private boolean continuar;
    private Scanner scanner;
    private InstrumentoFinancieroService instrumentoFinancieroService;
    private InversorService inversorService;
    public Menu() {
        continuar = true;
        scanner = new Scanner(System.in);
        instrumentoFinancieroService = InstrumentoFinancieroService.getInstance();
        inversorService = InversorService.getInstance();
    }

    public void desplegarMenu() {
        while (continuar) {
            int opcionElegida;
            System.out.println("\nIngrese una opcion para utilizar el servicio:\n" +
                    "1. Registrar un nuevo instrumento financiero.\n" +
                    "2. Consultar bonos o acciones.\n" +
                    "3. Editar bono o acción existente.\n" +
                    "4. Eliminar bonos o acciones.\n" +
                    "5. Operar inversores. \n" + //Esto esta mal aca pero quería probar el observer
                    "6. Salir.\n");
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
                    break;
                case 5:
                    this.operarInversores();
                    break;
                case 6:
                    System.out.println("Gracias por utilizar nuestro servicio. Nos vemos! :)\n");
                    continuar = false;
                    break;
                default:
                    System.out.println("La opción ingresada no es valida. Intente nuevamente. \n");
            }
        }
        scanner.close();
    }

    private void editarBonoOAccionExistente() {
        String nombreActual;
        int atributo;
        System.out.println("Ingrese el nombre del instrumento financiero que desea editar:\n");
        nombreActual = scanner.next();
        System.out.println("Ingrese el atributo que desea editar:\n" +
                "1. Nombre\n" +
                "2. Precio\n");
        atributo = scanner.nextInt();

        switch (atributo) {
            case 1:
                String nombreNuevo;
                System.out.println("Ingrese el nuevo nombre del instrumento financiero: \n");
                nombreNuevo = scanner.next();
                try{
                    InstrumentoFinanciero instrumentoFinancieroEditado = instrumentoFinancieroService.editarNombreInstrumentoFinanciero(nombreActual, nombreNuevo);
                    System.out.println("El nuevo instrumento fue editado correctamente en el sistema.");
                    System.out.println("Instrumento financiero editado: " + instrumentoFinancieroEditado.toString());
                } catch (InstrumentoNoEncontradoException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case 2:
                double precioNuevo;
                System.out.println("Ingrese el nuevo precio del instrumento financiero: \n");
                precioNuevo = scanner.nextDouble();
                try {
                    InstrumentoFinanciero instrumentoFinancieroEditado = instrumentoFinancieroService.editarPrecioInstrumentoFinanciero(nombreActual, precioNuevo);
                    System.out.println("El nuevo instrumento fue editado correctamente en el sistema.");
                    System.out.println("Instrumento financiero editado: " + instrumentoFinancieroEditado.toString());
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
                String nombre = scanner.next();
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
            nombreInstrumento = scanner.next();
            // TODO: debería chequarse aca si ya esta registrado ese nombre asi no le pide los demás datos
            System.out.println("Ingrese el precio del nuevo instrumento: \n");
            precioInstrumento = scanner.nextDouble();
            System.out.println("Ingrese la fecha de emision del nuevo instrumento con formato yyyy-mm-dd: \n");
            date = scanner.next();
            dateParseada = LocalDate.parse(date);
            TipoInstrumentoFinanciero tipoInstrumentoFinanciero = obtenerTipoInstrumento(tipoInstrumento);
            try {
                InstrumentoFinanciero instrumentoFinancieroAgregado = instrumentoFinancieroService.registrarInstrumentoFinanciero(nombreInstrumento,precioInstrumento,dateParseada,tipoInstrumentoFinanciero);
                System.out.println("El nuevo instrumento fue registrado correctamente en el sistema.");
                System.out.println("Instrumento financiero creado: " + instrumentoFinancieroAgregado.toString());

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
        String nombreInstrumentoAEliminar;
        System.out.println("Ingrese el nombre del instrumento financiero que desea eliminar: \n");
        nombreInstrumentoAEliminar = scanner.next();

        try {
            instrumentoFinancieroService.eliminarInstrumentoFinanciero(nombreInstrumentoAEliminar);
            System.out.println("El instrumento ha sido eliminado satisfactoriamente del sistema.");
        } catch (InstrumentoNoEncontradoException e) {
            System.err.println(e.getMessage());
        }
    }

    private void operarInversores() {
        int opcion = 0;
        System.out.println("Ingrese la opcion que desea realizar:\n" +
                "1. Agregar inversor.\n" +
                "2. Eliminar inversor. \n"+
                "3. Listar inversores. \n");
        opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                String nombreInversor;
                System.out.println("Ingrese el nombre del inversor: \n");
                nombreInversor = scanner.next();
                try {
                    Inversor newInversor = inversorService.registrarInversor(nombreInversor);
                    System.out.println("Se ha registrado correctamente en el sistema a " + newInversor.toString());
                } catch (InversorYaRegistradoException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case 2:
                // TODO: Eliminar inversor
                break;
            case 3:
                System.out.println(inversorService.listarInversores());
                break;
        }
    }

    private void validarOpcionElegida(int opciónElegida) {
        String opcion = String.valueOf(opciónElegida);
        if(! opcion.matches("[1234]")) {
            throw new IllegalArgumentException("La opción ingresada no es valida");
        }
    }
}
