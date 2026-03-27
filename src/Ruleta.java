import javax.swing.*;
import java.util.Random;
import java.util.Scanner;
public class Ruleta {
    public static final int MAX_HISTORIAL = 100;
    public static int[] historialNumeros = new int[MAX_HISTORIAL];
    public static int[] historialApuestas = new int[MAX_HISTORIAL];
    public static boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    public static int historialSize = 0;
    public static Random rng = new Random();
    public static int[] numerosRojos =
            {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
/**
 * Método principal: inicia el programa llamando al menú.
 */
public static void main(String[] args) {
    menu();
}
    /**
     * Controla el flujo principal del programa mostrando un menú en consola.
     */
    public static void menu() {
        Scanner in = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion(in);
            ejecutarOpcion(opcion, in);
        } while (opcion != 3);
        in.close();
    }    /**
     * Muestra en consola las opciones disponibles del menú.
     */
    public static void mostrarMenu() {
        System.out.println("\n--- CASINO BLACK CAT ---");
        System.out.println("1. Jugar a la Ruleta");
        System.out.println("2. Ver Estadísticas");
        System.out.println("3. Retirarse (Salir)");
        System.out.print("Elija una opción: ");
    }    /**
     * Lee la opción elegida por el usuario desde teclado.
     * @param in Scanner para entrada por consola.
     * @return número de opción ingresado.
     */
    public static int leerOpcion(Scanner in) {
        return in.nextInt();
    }
    /**
     * Ejecuta la acción correspondiente a la opción del menú.
     * @param opcion opción elegida por el usuario.
     * @param in Scanner para entrada por consola.
     */
    public static void ejecutarOpcion(int opcion, Scanner in) {
        switch (opcion) {
            case 1:
                iniciarRonda(in);
                break;
            case 2:
                mostrarEstadisticas();
                break;
            case 3:
                System.out.println("Gracias por visitar el Casino Black Cat de Don Donnie.");
                break;
            default:
                System.out.println("Opción inválida. Intente de nuevo.");
        }
    }/**
 * Inicia una ronda de la ruleta: leer apuesta, girar, evaluar y mostrar resultado.
 * @param in Scanner para entrada por consola.
 */
public static void iniciarRonda(Scanner in) {
    System.out.print("/ningrese el monto que quiere apostar: ");
    int apuesta = in.nextInt();

    char tipo = leerTipoApuesta(in);
    int numero = girarRuleta();
    boolean gano = evaluarResultado(numero, tipo);
    registrarResultado(numero, apuesta, gano);
    mostrarResultado(numero,tipo, apuesta, gano);
}
    /**
     * Permite al usuario seleccionar el tipo de apuesta (R/N/P/I).
     * @param in Scanner para entrada por consola.
    3
     * @return el tipo de apuesta elegido.
     */
    public static char leerTipoApuesta(Scanner in) {
        System.out.print("ingrese tipo de apuesta (R: roojo, N:negro, I:Impar, P:Par)");
        return
                in.next().toUpperCase().charAt(0);
    }
    /**
     * Simula el giro de la ruleta generando un número aleatorio de 0 a 36.
     * @return número de la ruleta.
     */
    public static int girarRuleta() {
        return 0;
    }
/*** Evalúa si la apuesta realizada por el jugador fue acertada.
 * @param numero número obtenido en la ruleta.
 * @param tipo tipo de apuesta elegida.
 * @return true si acertó, false si perdió.
 */
public static boolean evaluarResultado(int numero, char tipo) {
    return false;
}
    /**
     * Determina si un número corresponde a color rojo.
     * @param n número de la ruleta.
     * @return true si es rojo, false en caso contrario.
     */
    public static boolean esRojo(int n) {
        return false;
    }
/**
 * Registra los resultados de la ronda en los arreglos de historial.
 * @param numero número obtenido en la ruleta.
 * @param apuesta monto apostado.
 * @param acierto si el jugador acertó o no.
 */
public static void registrarResultado(int numero, int apuesta, boolean acierto) {}
    /**
     * Muestra en consola el resultado de la ronda.
     * @param numero número obtenido en la ruleta.
     * @param tipo tipo de apuesta realizada.
     * @param monto monto apostado.
     * @param acierto si el jugador ganó o perdió.
     */
    public static void mostrarResultado(int numero, char tipo, int monto, boolean
            acierto) {}

    /**
     * Muestra estadísticas generales de todas las rondas jugadas.
     */
    public static void mostrarEstadisticas() {}
}