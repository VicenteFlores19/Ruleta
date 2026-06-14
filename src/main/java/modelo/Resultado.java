package modelo;

public class Resultado {
    private final int numeroGanador;
    private final int montoApostado;
    private final int ganancia; // Positivo si ganó dinero, negativo si perdió

    // 1. Cambiamos TipoDeApuesta por String
    private final String tipoApuesta;

    // 2. Actualizamos el tipo de dato en el constructor
    public Resultado(int numeroGanador, int montoApostado, int ganancia, String tipoApuesta) {
        this.numeroGanador = numeroGanador;
        this.montoApostado = montoApostado;
        this.ganancia = ganancia;
        this.tipoApuesta = tipoApuesta;
    }

    public int getNumeroGanador() { return numeroGanador; }
    public int getMontoApostado() { return montoApostado; }
    public int getGanancia() { return ganancia; }

    // 3. Actualizamos el tipo de retorno del getter
    public String getTipoApuesta() { return tipoApuesta; }

    public boolean esVictoria() {
        return ganancia > 0;
    }
}