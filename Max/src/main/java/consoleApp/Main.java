package consoleApp;

import consoleApp.vistas.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = Menu.getMenu();
        menu.abrirMenu();
    }
}