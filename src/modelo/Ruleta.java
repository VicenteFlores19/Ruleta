package modelo;

import java.util.Random;

public class Ruleta {

    // --- ESTADO INTERNO (Atributos) ---
    private static final int[] NUMEROS_ROJOS = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
    private final Random rng;
    private int saldo;

    // NUEVO: Atributo de la interfaz (Abstracción) para aplicar DIP en el Modelo
    private final IRepositorioResultados repositorio;

    /**
     * Constructor: Prepara la ruleta con su saldo inicial y su repositorio inyectado.
     * @param saldoInicial El dinero con el que el jugador se sienta a la mesa.
     * @param repositorio El mecanismo de almacenamiento (Memoria o Archivo).
     */
    public Ruleta(int saldoInicial, IRepositorioResultados repositorio) {
        this.rng = new Random();
        this.saldo = saldoInicial;
        this.repositorio = repositorio; // Inyección de dependencia
    }

    // Constructor por defecto (saldo cero) con repositorio inyectado
    public Ruleta(IRepositorioResultados repositorio) {
        this.rng = new Random();
        this.saldo = 0;
        this.repositorio = repositorio; // Inyección de dependencia
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
     * Determina el color como texto.
     */
    public String colorDe(int numero) {
        if (numero == 0) {
            return "Verde";
        }
        return esRojo(numero) ? "Rojo" : "Negro";
    }

    /**
     * Evalúa la apuesta usando polimorfismo y guarda de forma persistente el resultado.
     */
    public int evaluarApuesta(ApuestaBase apuesta) {

        // 1. Calculamos el número y el color una única vez
        int numero = girar();
        String color = colorDe(numero);

        // 2. Extraemos el monto directamente del objeto apuesta
        int monto = apuesta.getMontoApostado();

        // 3. Late Binding: El objeto ejecuta su propio método acierta
        boolean gana = apuesta.acierta(numero, color);

        int ganancia = gana ? monto : -monto;

        // 4. Ajustamos el saldo según el resultado
        if (gana) {
            this.saldo += monto;
        } else {
            this.saldo -= monto;
        }

        // NUEVO: La Ruleta se encarga de empaquetar y guardar su propia historia.
        // Obtenemos el nombre de la clase hija (ej: "ApuestaRojo", "ApuestaPar") como texto.
        String tipoApuesta = apuesta.getClass().getSimpleName();

        Resultado resultado = new Resultado(numero, monto, ganancia, tipoApuesta);
        repositorio.guardar(resultado); // Se guarda en el CSV o Memoria automáticamente

        return ganancia; // Retorna el monto ganado o perdido (negativo)
    }
}