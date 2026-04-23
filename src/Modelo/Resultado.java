package Modelo;

public class Resultado {
    private final int numeroGanador;
    private final int montoApostado;
    private final int ganancia; // Positivo si ganó dinero, negativo si perdió
    private final TipoDeApuesta tipoApuesta; // Relación exigida por el PDF

    public Resultado(int numeroGanador, int montoApostado, int ganancia, TipoDeApuesta tipoApuesta) {
        this.numeroGanador = numeroGanador;
        this.montoApostado = montoApostado;
        this.ganancia = ganancia;
        this.tipoApuesta = tipoApuesta;
    }

    public int getNumeroGanador() { return numeroGanador; }
    public int getMontoApostado() { return montoApostado; }
    public int getGanancia() { return ganancia; }
    public TipoDeApuesta getTipoApuesta() { return tipoApuesta; }

    public boolean esVictoria() {
        return ganancia > 0;
    }
}