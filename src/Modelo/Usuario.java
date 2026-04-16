package Modelo;

public class Usuario {
    private String username;
    private String password;
    private String nombre;

    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
    }

    // Constructor sin parámetros (nace como invitado por defecto)
    public Usuario() {
        this("invitado", "1234", "Invitado");
    }

    public String getUsername() { return username; }
    public String getNombre() { return nombre; }

    // Este setter solo permite cambiar el nombre si no está vacío
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        }
    }

    // Verifica si las credenciales coinciden
    public boolean validarCredenciales(String u, String p) {
        return this.username.equals(u) && this.password.equals(p);
    }
}