package axi.vistas;

import java.util.Scanner;

public class Menu {

    private static Menu menu;
    private MenuInversor menuInversor;
    private MenuInstrumento menuInstrumento;
    Scanner scanner;

    private Menu() {
        this.menuInstrumento = MenuInstrumento.getMenu();
        this.menuInversor = MenuInversor.getMenu();
        scanner = new Scanner(System.in);
    }

    public static Menu getMenu() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }


    public void abrirMenu() {
        System.out.println("ingrese en que menu desea operar: 1. Menu instrumentos  2. Menu inversor");
        String opcion = scanner.next();
        while (opcion.equals("1") || opcion.equals("2")) {
            switch (opcion) {
                case "1":
                    menuInstrumento.abrirMenu();
                    break;
                case "2":
                    menuInversor.abrirMenu();
                    break;
            }
            System.out.println("ingrese en que menu desea operar: 1. Menu instrumentos  2. Menu inversor");
            opcion = scanner.next();
        }
    }
}
