package Controlador;

import Modelo.Ruleta;
import Modelo.TipoDeApuesta;

public class RuletaController {
    private final Ruleta ruleta;
    private final SessionController session;

    // El controlador recibe la ruleta y la sesión para poder trabajar con ellas
    public RuletaController(SessionController session, Ruleta ruleta) {
        this.session = session;
        this.ruleta = ruleta;
    }

    // Este método es llamado por el botón de la vista
    public String jugar(int monto, TipoDeApuesta tipo) {
        if (monto <= 0) return "El monto debe ser mayor a 0";
        if (monto > ruleta.getSaldo()) return "Saldo insuficiente. Vaya a su Perfil a recargar.";

        int numeroQueSalio = ruleta.girar();
        int resultadoPesos = ruleta.evaluarApuesta(numeroQueSalio, tipo, monto);

        if (resultadoPesos > 0) {
            return "¡Salió el " + numeroQueSalio + "! GANASTE $" + resultadoPesos;
        } else {
            return "Salió el " + numeroQueSalio + ". Perdiste $" + Math.abs(resultadoPesos);
        }
    }

    public int getSaldoActual() {
        return ruleta.getSaldo();
    }
}