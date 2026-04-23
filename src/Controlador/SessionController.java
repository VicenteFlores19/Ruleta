package Controlador;

import Modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador de Sesión: Actúa como el cerebro que gestiona quién está
 * conectado y la base de datos temporal de usuarios.
 */
public class SessionController {

    // Lista dinámica que funciona como base de datos en memoria
    private final List<Usuario> baseDeDatosUsuarios = new ArrayList<>();

    // Referencia al usuario que tiene la sesión activa
    private Usuario usuarioActual;

    /**
     * Registra un nuevo usuario en la lista tras validar los datos.
     */
    public void registrarUsuario(String u, String p, String n) {
        // Validación: No permitir campos vacíos
        if (u == null || u.isBlank() || p == null || p.isBlank() || n == null || n.isBlank()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }

        // Validación: Evitar duplicados revisando la lista ignorando mayúsculas/minúsculas
        for (Usuario user : baseDeDatosUsuarios) {
            if (user.getUsername().equalsIgnoreCase(u)) {
                throw new IllegalArgumentException("El nombre de usuario ya existe.");
            }
        }

        // Si todo está bien, se crea y agrega a la "base de datos"
        baseDeDatosUsuarios.add(new Usuario(u, p, n));
    }

    /**
     * Valida las credenciales contra la lista de usuarios registrados.
     */
    public boolean iniciarSesion(String u, String p) {
        for (Usuario user : baseDeDatosUsuarios) {
            if (user.validarCredenciales(u, p)) {
                this.usuarioActual = user; // Guardamos quién entró
                return true;
            }
        }
        return false;
    }

    /**
     * Elimina a un usuario de la lista si proporciona los datos correctos.
     */
    public boolean eliminarUsuario(String u, String p) {
        for (Usuario user : baseDeDatosUsuarios) {
            if (user.validarCredenciales(u, p)) {
                baseDeDatosUsuarios.remove(user);
                return true;
            }
        }
        return false;
    }

    // --- Métodos de consulta de estado ---

    public boolean hayUsuario() {
        return usuarioActual != null; //
    }

    public String getNombreUsuario() {
        // Devuelve el nombre real usando el getter del modelo
        return hayUsuario() ? usuarioActual.getNombre() : "";
    }

    public Usuario getUsuarioActual() {
        return usuarioActual; //
    }

    public void cerrarSesion() {
        usuarioActual = null; //
    }
}