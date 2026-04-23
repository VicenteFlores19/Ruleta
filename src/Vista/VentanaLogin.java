package Vista;

import Controlador.SessionController;
import javax.swing.*;
import java.awt.*;

public class VentanaLogin {

    // --- Referencia al controlador (Cerebro) ---
    private final SessionController session;

    // --- UI ---
    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");

    /**
     * Constructor: Ahora recibe obligatoriamente la sesión
     */
    public VentanaLogin(SessionController session) {
        this.session = session;
        configurarVentana();
        configurarEventos();
    }

    private void configurarVentana() {
        frame.setSize(350, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2, 10, 10));

        frame.add(lblUsuario);
        frame.add(txtUsuario);
        frame.add(lblClave);
        frame.add(txtClave);
        frame.add(new JLabel(""));
        frame.add(btnIngresar);
    }

    private void configurarEventos() {
        // Al presionar el botón, llamamos al método intentarLogin
        btnIngresar.addActionListener(e -> intentarLogin());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Lógica de login: La vista captura los datos y los envía al controlador
     */
    private void intentarLogin() {
        String u = txtUsuario.getText();
        String p = new String(txtClave.getPassword());

        // La vista NO valida, solo le pide al controlador que lo haga
        if (session.iniciarSesion(u, p)) {
            JOptionPane.showMessageDialog(frame, "¡Acceso exitoso, " + session.getNombreUsuario() + "!");
            frame.dispose();

            // Pasamos la MISMA sesión a la siguiente ventana
            new VentanaMenu(session).mostrarVentana();
        } else {
            JOptionPane.showMessageDialog(frame, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}