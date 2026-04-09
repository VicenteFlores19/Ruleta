package juego;

import java.util.Random;

/**
 * Clase que representa el motor lógico del juego de la Ruleta.
 * Aplica el Principio de Responsabilidad Única (SRP): Solo calcula, no muestra nada en pantalla.
 */
public class Ruleta {

    // --- ESTADO INTERNO (Atributos) ---
    private static final int[] NUMEROS_ROJOS = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
    private final Random rng;
    private int saldo;

    /**
     * Constructor: Prepara la ruleta para un nuevo jugador.
     * @param saldoInicial El dinero con el que el jugador se sienta a la mesa.
     */
    public Ruleta(int saldoInicial) {
        this.rng = new Random();
        this.saldo = saldoInicial;
    }

    // --- GETTERS ---
    public int getSaldo() {
        return saldo;
    }

    // --- REGLAS DE NEGOCIO (Métodos) ---

    /**
     * Simula el giro de la ruleta.
     * @return Un número aleatorio entre 0 y 36.
     */
    public int girar() {
        return rng.nextInt(37);
    }

    /**
     * Evalúa si un número pertenece al conjunto de los rojos.
     */
    public boolean esRojo(int numero) {
        for (int rojo : NUMEROS_ROJOS) {
            if (numero == rojo) {
                return true;
            }
        }
        return false;
    }

    /**
     * Evalúa la apuesta, ajusta el saldo y determina el resultado.
     * @param numero El número que salió en el giro.
     * @param tipo El tipo de apuesta ('P', 'I', 'R', 'N').
     * @param monto La cantidad de fichas apostadas.
     * @return El monto ganado (positivo) o perdido (negativo).
     */
    public int evaluarApuesta(int numero, char tipo, int monto) {
        // Regla de oro del casino: Si sale 0, las apuestas simples pierden.
        if (numero == 0) {
            this.saldo -= monto;
            return -monto;
        }

        boolean gana = false;
        switch (Character.toUpperCase(tipo)) {
            case 'P': gana = (numero % 2 == 0); break;
            case 'I': gana = (numero % 2 != 0); break;
            case 'R': gana = esRojo(numero); break;
            case 'N': gana = !esRojo(numero); break;
        }

        // Ajustamos el saldo según el resultado
        if (gana) {
            this.saldo += monto;
            return monto;
        } else {
            this.saldo -= monto;
            return -monto;
        }
    }
}