package controlador;

import modelo.*;

public class RuletaController {
    private final Ruleta ruleta;
    private final SessionController session;

    // Aquí inyectamos la interfaz (DIP)
    private final IRepositorioResultados repositorio;

    public RuletaController(Ruleta ruleta, SessionController session) {
        this.ruleta = ruleta;
        this.session = session;
        // Instanciamos la clase concreta que maneja el CSV
        this.repositorio = new RepositorioArchivo();
    }

    public String jugar(int monto, String tipoSeleccionado) {
        Usuario user = session.getUsuarioActual();

        if (user.getSaldo() < monto) {
            return "Saldo insuficiente";
        }

        ApuestaBase apuesta = null;
        switch (tipoSeleccionado.toUpperCase()) {
            case "ROJO": apuesta = new ApuestaRojo(monto); break;
            case "NEGRO": apuesta = new ApuestaNegro(monto); break;
            case "PAR": apuesta = new ApuestaPar(monto); break;
            case "IMPAR": apuesta = new ApuestaImpar(monto); break;
            default: return "Apuesta no válida";
        }

        int numeroGanador = ruleta.girar();
        String colorGanador = ruleta.colorDe(numeroGanador);

        boolean gano = apuesta.acierta(numeroGanador, colorGanador);
        int ganancia = gano ? monto : -monto;

        user.setSaldo(user.getSaldo() + ganancia);

        Resultado r = new Resultado(numeroGanador, monto, ganancia, tipoSeleccionado);
        user.agregarResultado(r);

        // Guardamos usando el contrato de la interfaz
        repositorio.guardar(r);

        return gano ? "¡Ganaste! Salió el " + numeroGanador : "Perdiste. Salió el " + numeroGanador;
    }

    public int getSaldoActual() {
        return session.getUsuarioActual().getSaldo();
    }

    public Ruleta getRuletaModelo() {
        return ruleta;
    }
}