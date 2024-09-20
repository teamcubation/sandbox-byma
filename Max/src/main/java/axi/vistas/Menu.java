package axi.vistas;

import java.util.Scanner;

public class Menu {
    final static String MSG_MENU = "ingrese en que menu desea operar:" +
            "\n1. Menu instrumentos\n2. Menu inversor\n3. Menu observer";
    private static Menu menu;
    private MenuInversor menuInversor;
    private MenuInstrumento menuInstrumento;
    private MenuObserver menuObserver;
    Scanner scanner;

    private Menu() {
        this.menuInstrumento = MenuInstrumento.getMenu();
        this.menuInversor = MenuInversor.getMenu();
        this.menuObserver = MenuObserver.getMenu();
        scanner = new Scanner(System.in);
    }

    public static Menu getMenu() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }


    public void abrirMenu() {
        System.out.println(MSG_MENU);
        String opcion = scanner.next();
        while (opcion.equals("1") || opcion.equals("2")|| opcion.equals("3")) {
            switch (opcion) {
                case "1":
                    menuInstrumento.abrirMenu();
                    break;
                case "2":
                    menuInversor.abrirMenu();
                    break;
                case "3":
                    menuObserver.abrirMenu();
                    break;
            }
            System.out.println(MSG_MENU);
            opcion = scanner.next();
        }
    }
}
