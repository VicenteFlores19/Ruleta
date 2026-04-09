package sistema;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa la ventana de Login del Casino Black Cat.
 * Permite ingresar credenciales para acceder al sistema.
 */
public class VentanaLogin {

    // --- Lista dinámica de usuarios ---
    public static final List<Usuario> USUARIOS = new ArrayList<>();

    // --- UI ---
    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");

    /**
     * Constructor que inicializa la ventana de login.
     * Configura el tamaño, los componentes y los eventos.
     */
    public VentanaLogin() {
        cargarUsuarios();    // Fase 1: Carga la lista de usuarios
        configurarVentana(); // Fase 2: Configura el diseño visual
        configurarEventos(); // Fase 3: Conecta los botones
    }

    /**
     * Carga los usuarios base del sistema.
     */
    private void cargarUsuarios() {
        if (USUARIOS.isEmpty()) {
            USUARIOS.add(new Usuario("admin", "1234", "Don Donnie"));
            USUARIOS.add(new Usuario("jugador1", "ruleta", "Vicente Flores"));
        }
    }

    /**
     * Arma el diseño de la interfaz gráfica.
     */
    private void configurarVentana() {
        frame.setSize(350, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new java.awt.GridLayout(3, 2, 10, 10)); // 3 filas, 2 columnas, márgenes

        frame.add(lblUsuario);
        frame.add(txtUsuario);
        frame.add(lblClave);
        frame.add(txtClave);
        frame.add(new JLabel("")); // Espacio vacío para alinear el botón a la derecha
        frame.add(btnIngresar);
    }

    /**
     * Conecta los botones con sus acciones.
     */
    private void configurarEventos() {
        btnIngresar.addActionListener(e -> login());
    }

    /**
     * Muestra la ventana en pantalla.
     * Debe centrarla y hacerla visible.
     */
    public void mostrarVentana() {
        frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        frame.setVisible(true);
    }

    /**
     * Maneja el evento de login al presionar el botón.
     * Debe validar credenciales y abrir la siguiente ventana o mostrar error.
     */
    private void login() {
        String u = txtUsuario.getText();
        String p = new String(txtClave.getPassword());
        String nombre = validarCredenciales(u, p);

        if (!nombre.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "¡Acceso exitoso, " + nombre + "!");
            // TODO: abrir ventana de saludo o juego
        } else {
            JOptionPane.showMessageDialog(frame, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Valida las credenciales del usuario contra el arreglo hardcoded.
     *
     * @param u nombre de usuario ingresado
     * @param p clave ingresada
     * @return el nombre del usuario si es válido, o cadena vacía si no coincide
     */
    private String validarCredenciales(String u, String p) {
        for (Usuario user : USUARIOS) {
            if (user.validarCredenciales(u, p)) {
                return user.getNombre();
            }
        }
        return "";
    }

    /**
     * Abre la ventana de registro para crear un nuevo usuario.
     * Debe cerrar la ventana actual e invocar a VentanaRegistro.
     */
    void abrirRegistro() {
        // TODO: abrir ventana de registro y cerrar login
    }
}