package org.example;

import javax.swing.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar =true;
        Calculadora calculadora = new Calculadora();

        double primerTermino;
        double segundoTermino;

        double resultado = 0;

        while(continuar) {
            System.out.println("Seleccione la operacion a realizar: \n1.Suma\n2.Resta \n3. Multiplicacion \n4 Division \n 5. Salir");
            int  operacionSeleccionada = scanner.nextInt();

            switch (operacionSeleccionada){

                case 1:
                    System.out.println("ingresar primer termino");
                     primerTermino = scanner.nextDouble();

                    System.out.println("ingresar segundo termino");

                     segundoTermino = scanner.nextDouble();

                    resultado = calculadora.suma(primerTermino, segundoTermino);
                    System.out.println("Resultado suma: " + resultado);
                    break;
                case 2:
                    System.out.println("ingresar primer termino");
                     primerTermino = scanner.nextDouble();

                    System.out.println("ingresar segundo termino");

                     segundoTermino = scanner.nextDouble();

                    resultado = calculadora.resta(primerTermino, segundoTermino);
                    System.out.println("Resultado resta: " + resultado);
                    break;
                case 3:
                    System.out.println("ingresar primer termino");
                    primerTermino = scanner.nextDouble();

                    System.out.println("ingresar segundo termino");

                    segundoTermino = scanner.nextDouble();

                    resultado = calculadora.multiplicacion(primerTermino, segundoTermino);
                    System.out.println("Resultado multiplicacion: " + resultado);
                    break;
                case 4:
                    System.out.println("ingresar primer termino");
                    primerTermino = scanner.nextDouble();

                    System.out.println("ingresar segundo termino");

                    segundoTermino = scanner.nextDouble();
                    if (segundoTermino == 0){
                        System.out.println("Error: division por 0 no valida");
                    } else {
                        resultado = calculadora.division(primerTermino, segundoTermino);
                        System.out.println("Resultado division: " + resultado);
                    }
                    
                    break;
                case 5:
                    continuar = false;
                    break;
                 default:
                     System.out.println("Opcion invalida!!!");
                    break;

            }


        }

    }
}