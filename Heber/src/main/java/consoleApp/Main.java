package consoleApp;

import consoleApp.controllers.InstrumentoController;


public class Main {
    public static void main(String[] args) {

        InstrumentoController instrumentoController = InstrumentoController.getInstance();
        instrumentoController.iniciarGestion();
    }
}