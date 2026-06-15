package controlador;

import modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class SessionController {

    private final List<Usuario> baseDeDatosUsuarios = new ArrayList<>();
    private Usuario usuarioActual;

    public void registrarUsuario(String u, String p, String n) {
        if (u == null || u.isBlank() || p == null || p.isBlank() || n == null || n.isBlank()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }

        for (Usuario user : baseDeDatosUsuarios) {
            if (user.getUsername().equalsIgnoreCase(u)) {
                throw new IllegalArgumentException("El nombre de usuario ya existe.");
            }
        }

        baseDeDatosUsuarios.add(new Usuario(u, p, n));
    }

    public boolean iniciarSesion(String u, String p) {
        // Validación del Caso 7: Bloquear identificador nulo
        if (u == null) {
            return false;
        }

        for (Usuario user : baseDeDatosUsuarios) {
            if (user.validarCredenciales(u, p)) {
                this.usuarioActual = user;
                return true;
            }
        }
        return false;
    }

    public boolean eliminarUsuario(String u, String p) {
        for (Usuario user : baseDeDatosUsuarios) {
            if (user.validarCredenciales(u, p)) {
                baseDeDatosUsuarios.remove(user);
                return true;
            }
        }
        return false;
    }

    public boolean hayUsuario() {
        return usuarioActual != null;
    }

    public String getNombreUsuario() {
        return hayUsuario() ? usuarioActual.getNombre() : "";
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void cerrarSesion() {
        usuarioActual = null;
    }
}