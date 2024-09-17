package axi.vistas;

import axi.Broker;

import java.util.Scanner;

public class MenuInversor {
    private static MenuInversor menu;
    private Broker broker;
    Scanner scanner;

    private MenuInversor(){
        this.broker = Broker.getBroker();
        this.scanner = new Scanner(System.in);
    }
    public MenuInversor getMenu(){
        if (menu==null)
            menu = new MenuInversor();
        return menu;
    }


}
