package modelo;

public class ApuestaNegro extends ApuestaBase {

    public ApuestaNegro(int monto) {
        super(monto, "Negro");
    }

    @Override
    public boolean acierta(int numero, String color) {
        // Retorna true si el número no es 0 y el color es Negro
        return numero != 0 && color.equalsIgnoreCase("Negro");
    }
}