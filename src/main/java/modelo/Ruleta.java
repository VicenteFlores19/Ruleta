package modelo;

import java.util.Random;

public class Ruleta {

    private int saldo;
    private final IRepositorioResultados repositorio;
    private final Random random;

    // Constructor actualizado con la validación para pasar el Test 1
    public Ruleta(int saldoInicial, IRepositorioResultados repositorio) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("Saldo inicial inválido");
        }
        this.saldo = saldoInicial;
        this.repositorio = repositorio;
        this.random = new Random();
    }

    public int girar() {
        // Genera un número entre 0 y 36
        return random.nextInt(37);
    }

    public String colorDe(int numero) {
        if (numero == 0) {
            return "VERDE";
        }
        // Lógica simple: pares rojos, impares negros (puedes ajustarlo a las reglas de tu casino)
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

    public void depositar(int monto) {
        this.saldo = this.saldo + monto;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}