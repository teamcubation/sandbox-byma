package org.example;

public class Calculadora {

    double suma(double num1, double num2) {
        return num1 + num2;
    }
    double resta(double numero1, double num2){
        return numero1 - num2;
    };
    double multiplicacion(double numero1, double num2){
        return numero1*num2;
    };
    double division(double numero1, double num2) throws ArithmeticException{
        return  numero1 / num2;
    };
}
