package controlador;

import modelo.*;

public class RuletaController {
    private final Ruleta ruleta;
    private final SessionController session;
    private final GestorArchivo gestorArchivo; // Agregado para manejar el TXT

    public RuletaController(Ruleta ruleta, SessionController session) {
        this.ruleta = ruleta;
        this.session = session;
        this.gestorArchivo = new GestorArchivo(); // Inicializamos el gestor de archivos
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

        // Guardamos el resultado físicamente en el archivo historial_apuestas.txt
        gestorArchivo.guardarResultado(r);

        return gano ? "¡Ganaste! Salió el " + numeroGanador : "Perdiste. Salió el " + numeroGanador;
    }

    public int getSaldoActual() {
        return session.getUsuarioActual().getSaldo();
    }

    public Ruleta getRuletaModelo() {
        return ruleta;
    }
}