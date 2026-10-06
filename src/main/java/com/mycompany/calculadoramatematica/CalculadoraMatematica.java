package com.mycompany.calculadoramatematica;

import java.util.Locale;
import java.util.Scanner;

/**
 * Calculadora matemática de consola con menú interactivo.
 * Realiza suma, resta, multiplicación y división con dos números double.
 *
 * @author Diego Suero - 2025-2470
 */
public class CalculadoraMatematica {

    // Atributos privados
    private double numero1;
    private double numero2;

    /**
     * Constructor por defecto: ambos números comienzan en 0.
     */
    public CalculadoraMatematica() {
        numero1 = 0;
        numero2 = 0;
    }

    /**
     * Asigna el primer número.
     *
     * @param numero1 valor del primer número
     */
    public void setNumero1(double numero1) {
        this.numero1 = numero1;
    }

    /**
     * Asigna el segundo número.
     *
     * @param numero2 valor del segundo número
     */
    public void setNumero2(double numero2) {
        this.numero2 = numero2;
    }

    /**
     * Suma los dos números.
     *
     * @return numero1 + numero2
     */
    public double sumar() {
        return numero1 + numero2;
    }

    /**
     * Resta el segundo número al primero.
     *
     * @return numero1 - numero2
     */
    public double restar() {
        return numero1 - numero2;
    }

    /**
     * Multiplica los dos números.
     *
     * @return numero1 * numero2
     */
    public double multiplicar() {
        return numero1 * numero2;
    }

    /**
     * Comprueba si se puede dividir, es decir, si el divisor es distinto de cero.
     *
     * @return true si numero2 es distinto de cero
     */
    public boolean puedeDividir() {
        return numero2 != 0;
    }

    /**
     * Divide el primer número entre el segundo. Solo debe llamarse cuando
     * {@link #puedeDividir()} devuelva true.
     *
     * @return numero1 / numero2
     */
    public double dividir() {
        return numero1 / numero2;
    }

    /**
     * Imprime el menú principal en la consola.
     */
    private static void imprimirMenu() {
        System.out.println("\n===== CALCULADORA MATEMATICA =====");
        System.out.println("1. Ingresar numeros");
        System.out.println("2. Sumar");
        System.out.println("3. Restar");
        System.out.println("4. Multiplicar");
        System.out.println("5. Dividir");
        System.out.println("0. Salir");
        System.out.println("==================================");
        System.out.print("Seleccione una opcion: ");
    }

    /**
     * Pide un número decimal y repite la pregunta hasta que el usuario
     * escriba un valor válido.
     *
     * @param entrada Scanner para leer de la consola
     * @param mensaje texto que se muestra al usuario
     * @return el número escrito por el usuario
     */
    private static double pedirNumero(Scanner entrada, String mensaje) {
        System.out.print(mensaje);
        while (!entrada.hasNextDouble()) {
            entrada.next(); // descarta el texto inválido
            System.out.print("Valor invalido. " + mensaje);
        }
        return entrada.nextDouble();
    }

    /**
     * Lee la opción del menú. Si el usuario no escribe un entero devuelve -1.
     *
     * @param entrada Scanner para leer de la consola
     * @return la opción elegida, o -1 si la entrada no es válida
     */
    private static int pedirOpcion(Scanner entrada) {
        if (entrada.hasNextInt()) {
            return entrada.nextInt();
        }
        entrada.next(); // descarta el texto inválido
        return -1;
    }

    /**
     * Método principal: muestra el menú dentro de un do-while y procesa la
     * opción elegida con un switch hasta que el usuario escribe 0.
     *
     * @param args argumentos de consola (no se usan)
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        entrada.useLocale(Locale.US); // acepta el punto como separador decimal

        CalculadoraMatematica calc = new CalculadoraMatematica();
        int opcion;
        boolean hayNumeros = false;

        do {
            imprimirMenu();
            opcion = pedirOpcion(entrada);

            switch (opcion) {
                case 1:
                    calc.setNumero1(pedirNumero(entrada, "\nIngrese el primer numero: "));
                    calc.setNumero2(pedirNumero(entrada, "Ingrese el segundo numero: "));
                    hayNumeros = true;
                    System.out.println("Numeros ingresados correctamente.");
                    break;

                case 2:
                    if (hayNumeros) {
                        System.out.println("\nResultado de la suma: " + calc.sumar());
                    } else {
                        System.out.println("\nPrimero ingrese los numeros (opcion 1).");
                    }
                    break;

                case 3:
                    if (hayNumeros) {
                        System.out.println("\nResultado de la resta: " + calc.restar());
                    } else {
                        System.out.println("\nPrimero ingrese los numeros (opcion 1).");
                    }
                    break;

                case 4:
                    if (hayNumeros) {
                        System.out.println("\nResultado de la multiplicacion: " + calc.multiplicar());
                    } else {
                        System.out.println("\nPrimero ingrese los numeros (opcion 1).");
                    }
                    break;

                case 5:
                    if (!hayNumeros) {
                        System.out.println("\nPrimero ingrese los numeros (opcion 1).");
                    } else if (calc.puedeDividir()) {
                        System.out.println("\nResultado de la division: " + calc.dividir());
                    } else {
                        // Manejo de la división por cero con IF
                        System.out.println("\nError: no se puede dividir entre cero.");
                    }
                    break;

                case 0:
                    System.out.println("\nGracias por usar la calculadora!");
                    break;

                default:
                    System.out.println("\nOpcion invalida. Elija entre 0 y 5.");
            }
        } while (opcion != 0);

        entrada.close();
    }
}