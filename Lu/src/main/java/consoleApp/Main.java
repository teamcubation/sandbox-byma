import consoleApp.controlador.Controlador;
import consoleApp.vista.*;

public class Main {
    public static void main(String[] args) {
        Vista vista = new Vista();

        Controlador controlador = new Controlador(vista);
        controlador.gestionarInstrumentosFinancieros();
    }
}