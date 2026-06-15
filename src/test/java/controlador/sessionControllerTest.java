package controlador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SessionControllerTest {

    private SessionController sessionController;

    @BeforeEach
    void setUp() {
        sessionController = new SessionController();
    }

    @Test
    void testIniciarSesion_UsuarioNoRegistrado_RechazaAcceso() {
        boolean loginExitoso = sessionController.iniciarSesion("usuarioFantasma", "clave123");
        assertFalse(loginExitoso, "El sistema debe denegar el acceso a usuarios no registrados");
    }

    @Test
    void testIniciarSesion_UsernameNulo_RechazaAcceso() {
        boolean loginExitoso = sessionController.iniciarSesion(null, "clave123");
        assertFalse(loginExitoso, "El sistema debe rechazar el inicio de sesión si el username es null");
    }
}