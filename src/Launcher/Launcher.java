package Launcher;

import Controlador.SessionController;
import Vista.VentanaLogin;

public class Launcher {
    public static void main(String[] args) {
        // 1. Creamos la única instancia de la sesión para todo el sistema
        SessionController session = new SessionController();

        session.registrarUsuario("admin", "1234", "Don Donnie");
        session.registrarUsuario("jugador1", "ruleta", "Vicente Flores");

        new VentanaLogin(session).mostrarVentana();
    }
}