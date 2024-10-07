package org.example;

import java.util.Stack;

public class CalculadoraDos {

    public static double evaluarExpresion(String expresion) {
        // Eliminamos espacios en blanco
        expresion = expresion.replaceAll("\\s+", "");

        Stack<Double> numeros = new Stack<>();
        Stack<Character> operadores = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            if (Character.isDigit(c)) {
                StringBuilder num = new StringBuilder();
                while (i < expresion.length() && (Character.isDigit(expresion.charAt(i)))) {
                    num.append(expresion.charAt(i));
                    i++;
                }
                i--;
                numeros.push(Double.parseDouble(num.toString()));
            }
            else if (c == '(') {
                operadores.push(c);
            }
            else if (c == ')') {
                while (!operadores.isEmpty() && operadores.peek() != '(') {
                    numeros.push(aplicarOperacion(operadores.pop(), numeros.pop(), numeros.pop()));
                }
                operadores.pop(); // Eliminar el '('
            }
            else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operadores.isEmpty() && precedencia(operadores.peek()) >= precedencia(c)) {
                    numeros.push(aplicarOperacion(operadores.pop(), numeros.pop(), numeros.pop()));
                }
                operadores.push(c);
            }
        }

        while (!operadores.isEmpty()) {
            numeros.push(aplicarOperacion(operadores.pop(), numeros.pop(), numeros.pop()));
        }

        return numeros.pop();
    }

    private static int precedencia(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }

    private static double aplicarOperacion(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("División por cero");
                return a / b;
        }
        return 0;
    }

    public static void main(String[] args) {
        String expresion = "1+2+3+4+5";
        double resultado = evaluarExpresion(expresion);
        System.out.println("Resultado de " + expresion + " = " + resultado);

        expresion = "4*(5+3)-10";
        resultado = evaluarExpresion(expresion);
        System.out.println("Resultado de " + expresion + " = " + resultado);

        expresion = "(40/4)*10-1500";
        resultado = evaluarExpresion(expresion);
        System.out.println("Resultado de " + expresion + " = " + resultado);
    }
}
