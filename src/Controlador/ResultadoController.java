package Controlador;

import Modelo.Resultado;
import java.util.List;

public class ResultadoController {
    private final SessionController session; // Inyección de dependencia [cite: 323]

    public ResultadoController(SessionController session) {
        this.session = session;
    }

    // El controlador accede al historial del usuario autenticado en la sesión [cite: 331, 335]
    public List<Resultado> obtenerHistorial() {
        if (session.hayUsuario()) {
            return session.getUsuarioActual().getHistorial();
        }
        return List.of(); // Lista vacía si no hay nadie conectado
    }
}