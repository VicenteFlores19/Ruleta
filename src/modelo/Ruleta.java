package modelo;

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
        return rng.nextInt(37); // Genera de 0 a 36
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
     * NUEVO: Método auxiliar para determinar el color como texto.
     * Necesario para enviarlo a la superclase ApuestaBase.
     */
    public String colorDe(int numero) {
        if (numero == 0) {
            return "Verde";
        }
        return esRojo(numero) ? "Rojo" : "Negro";
    }

    /**
     * NUEVO: Se aplica el Polimorfismo.
     * Reemplaza a los antiguos evaluarApuesta y verificarGanador.
     * Ya no hay Enum ni bloques Switch.
     */
    public int evaluarApuesta(ApuestaBase apuesta) {

        // 1. Calculamos el número y el color una única vez
        int numero = girar();
        String color = colorDe(numero);

        // 2. Extraemos el monto directamente del objeto apuesta
        int monto = apuesta.getMontoApostado();

        // 3. Late Binding: El objeto ejecuta su propio método acierta
        boolean gana = apuesta.acierta(numero, color);

        // 4. Ajustamos el saldo según el resultado
        if (gana) {
            this.saldo += monto;
            return monto; // Retorna lo que ganó
        } else {
            this.saldo -= monto;
            return -monto; // Retorna lo que perdió en negativo
        }
    }
}