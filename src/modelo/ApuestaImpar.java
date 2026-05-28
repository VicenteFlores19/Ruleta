package modelo;

public class ApuestaImpar extends ApuestaBase {

    public ApuestaImpar(int monto) {
        super(monto, "Impar");
    }

    @Override
    public boolean acierta(int numero, String color) {
        // Retorna true si el número no es 0 y NO es divisible por 2 exactamente
        return numero != 0 && numero % 2 != 0;
    }
}