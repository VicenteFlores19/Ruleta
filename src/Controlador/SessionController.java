package Controlador;

import Modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class SessionController {

    // Nuestra "Base de datos" temporal
    private final List<Usuario> baseDeDatosUsuarios = new ArrayList<>();
    private Usuario usuarioActual;

    public void registrarUsuario(String u, String p, String n) {
        if (u == null || u.isBlank() || p == null || p.isBlank() || n == null || n.isBlank()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }

        // Evitar usuarios duplicados
        for (Usuario user : baseDeDatosUsuarios) {
            if (user.getUsername().equals(u)) {
                throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
            }
        }

        baseDeDatosUsuarios.add(new Usuario(u, p, n));
    }

    public boolean iniciarSesion(String u, String p) {
        for (Usuario user : baseDeDatosUsuarios) {
            if (user.validarCredenciales(u, p)) {
                usuarioActual = user;
                return true;
            }
        }
        return false;
    }

    // Nuevo método: Elimina la cuenta si las credenciales son correctas
    public boolean eliminarUsuario(String u, String p) {
        for (Usuario user : baseDeDatosUsuarios) {
            if (user.validarCredenciales(u, p)) {
                baseDeDatosUsuarios.remove(user);
                return true;
            }
        }
        return false;
    }

    // --- Métodos de Sesión Intactos ---
    public boolean hayUsuario() { return usuarioActual != null; }
    public String getNombreUsuario() { return hayUsuario() ? usuarioActual.getNombre() : ""; }
    public Usuario getUsuarioActual() { return usuarioActual; }
    public void cerrarSesion() { usuarioActual = null; }
}