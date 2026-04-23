package Vista;

import Controlador.SessionController;
import javax.swing.*;
import java.awt.*;

public class VentanaRegistro {
    private final SessionController session;
    private final JFrame frame = new JFrame("Registro - Casino Black Cat");

    private final JTextField txtUsername = new JTextField();
    private final JPasswordField txtPassword = new JPasswordField();
    private final JTextField txtNombre = new JTextField();

    public VentanaRegistro(SessionController session) {
        this.session = session;
        configurarVentana();
    }

    private void configurarVentana() {
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        JButton btnGuardar = new JButton("Crear Cuenta");
        JButton btnCancelar = new JButton("Volver");

        btnGuardar.addActionListener(e -> registrar());
        btnCancelar.addActionListener(e -> volver());

        frame.add(new JLabel(" Nombre de Usuario:"));
        frame.add(txtUsername);
        frame.add(new JLabel(" Contraseña:"));
        frame.add(txtPassword);
        frame.add(new JLabel(" Tu Nombre Real:"));
        frame.add(txtNombre);
        frame.add(btnCancelar);
        frame.add(btnGuardar);
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void registrar() {
        String u = txtUsername.getText();
        String p = new String(txtPassword.getPassword());
        String n = txtNombre.getText();

        try {
            session.registrarUsuario(u, p, n); // Mandamos los datos al cerebro
            JOptionPane.showMessageDialog(frame, "¡Cuenta creada con éxito! Ahora puedes iniciar sesión.");
            volver();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volver() {
        frame.dispose();
        new VentanaLogin(session).mostrarVentana();
    }
}