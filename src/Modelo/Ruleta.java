package Modelo;

import java.util.Random;

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

    // Constructor por defecto (saldo cero)
    public Ruleta() {
        this.rng = new Random();
        this.saldo = 0;
    }

    // --- GETTERS ---
    public int getSaldo() {
        return saldo;
    }

    // Método de negocio para recargar saldo
    public void depositar(int monto) {
        if (monto > 0) {
            this.saldo += monto;
        }
    }

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
     * Evalúa la apuesta usando el ENUM.
     */
    public int evaluarApuesta(int numero, TipoDeApuesta tipo, int monto) {
        // Regla de oro del casino: Si sale 0, las apuestas simples pierden.
        if (numero == 0) {
            this.saldo -= monto;
            return -monto;
        }

        boolean gana = false;

        // Evaluamos usando el Enum en lugar del char
        switch (tipo) {
            case PAR: gana = (numero % 2 == 0); break;
            case IMPAR: gana = (numero % 2 != 0); break;
            case ROJO: gana = esRojo(numero); break;
            case NEGRO: gana = !esRojo(numero); break;
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