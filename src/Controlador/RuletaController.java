package Controlador;

import Modelo.Ruleta;
import Modelo.Resultado;
import Modelo.Usuario;
import Modelo.TipoDeApuesta;

public class RuletaController {
    private final Ruleta ruleta;
    private final SessionController session; // Inyección de dependencia

    public RuletaController(Ruleta ruleta, SessionController session) {
        this.ruleta = ruleta;
        this.session = session;
    }

    public String jugar(int monto, TipoDeApuesta tipo) {
        Usuario user = session.getUsuarioActual();

        if (user.getSaldo() < monto) {
            return "Saldo insuficiente";
        }

        int numeroGanador = ruleta.girar();
        boolean gano = ruleta.verificarGanador(numeroGanador, tipo);
        int ganancia = gano ? monto : -monto; // Ajuste simple: ganas lo apostado o lo pierdes

        user.setSaldo(user.getSaldo() + ganancia);

        // 4. Registrar en el historial (Iteración 5)
        Resultado r = new Resultado(numeroGanador, monto, ganancia, tipo);
        user.agregarResultado(r);

        return gano ? "¡Ganaste! Salió el " + numeroGanador : "Perdiste. Salió el " + numeroGanador;
    }

    public int getSaldoActual() {
        // Ahora el saldo se lo pedimos al usuario de la sesión, no a la ruleta
        return session.getUsuarioActual().getSaldo();
    }

    public Ruleta getRuletaModelo() {
        return ruleta;
    }
}