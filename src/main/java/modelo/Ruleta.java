package modelo;

import java.util.Random;

public class Ruleta {

    private int saldo;
    private final IRepositorioResultados repositorio;
    private final Random random;

    // Caso 1: Constructor rechaza saldo negativo
    public Ruleta(int saldoInicial, IRepositorioResultados repositorio) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("Saldo inicial inválido");
        }
        this.saldo = saldoInicial;
        this.repositorio = repositorio;
        this.random = new Random();
    }

    // Caso 2: Depósito incrementa el saldo
    public void depositar(int monto) {
        this.saldo = this.saldo + monto;
    }

    // Casos 3 y 4: Validaciones al intentar jugar
    public void jugar(ApuestaBase apuesta) {
        if (apuesta == null) {
            throw new IllegalArgumentException("Apuesta requerida");
        }

        if (apuesta.getMontoApostado() > this.saldo) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        // La lógica del giro y descuento de saldo se implementará más adelante
    }

    public int girar() {
        return random.nextInt(37);
    }

    public String colorDe(int numero) {
        if (numero == 0) {
            return "VERDE";
        }
        return (numero % 2 == 0) ? "ROJO" : "NEGRO";
    }

    public void registrarResultado(Resultado r) {
        if (repositorio != null) {
            repositorio.guardar(r);
        }
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}