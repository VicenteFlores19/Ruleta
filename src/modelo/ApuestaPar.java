package modelo;

public class ApuestaPar extends ApuestaBase {

    public ApuestaPar(int monto) {
        super(monto, "Par");
    }

    @Override
    public boolean acierta(int numero, String color) {
        // Retorna true si el número no es 0 y es divisible por 2 exactamente
        return numero != 0 && numero % 2 == 0;
    }
}