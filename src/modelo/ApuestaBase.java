package modelo;

public abstract class ApuestaBase {
    protected int montoApostado;
    protected String etiqueta;

    public ApuestaBase(int montoApostado, String etiqueta) {
        this.montoApostado = montoApostado;
        this.etiqueta = etiqueta;
    }

    public abstract boolean acierta(int numero, String color);

    public int getMontoApostado() { return montoApostado; }
    public String getEtiqueta() { return etiqueta; }
}