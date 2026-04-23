package Vista;

import Controlador.SessionController;
import javax.swing.*;
import java.awt.*;

public class VentanaLogin {

    private final SessionController session;
    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtClave = new JPasswordField();

    public VentanaLogin(SessionController session) {
        this.session = session;
        configurarVentana();
    }

    private void configurarVentana() {
        frame.setSize(350, 200); // Un poco más alta para los botones nuevos
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        JButton btnIngresar = new JButton("Ingresar");
        JButton btnRegistrar = new JButton("Registrarse");
        JButton btnEliminar = new JButton("Eliminar Cuenta");

        btnIngresar.addActionListener(e -> intentarLogin());

        // Abrimos la nueva ventana de registro
        btnRegistrar.addActionListener(e -> {
            frame.dispose();
            new VentanaRegistro(session).mostrarVentana();
        });

        // Evento de eliminar
        btnEliminar.addActionListener(e -> eliminarCuenta());

        frame.add(new JLabel(" Usuario:"));
        frame.add(txtUsuario);
        frame.add(new JLabel(" Clave:"));
        frame.add(txtClave);
        frame.add(btnRegistrar);
        frame.add(btnIngresar);
        frame.add(new JLabel("")); // Espacio
        frame.add(btnEliminar);
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void intentarLogin() {
        String u = txtUsuario.getText();
        String p = new String(txtClave.getPassword());

        if (session.iniciarSesion(u, p)) {
            frame.dispose();
            new VentanaMenu(session).mostrarVentana();
        } else {
            JOptionPane.showMessageDialog(frame, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCuenta() {
        String u = txtUsuario.getText();
        String p = new String(txtClave.getPassword());

        if (u.isEmpty() || p.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Escriba su usuario y clave arriba para eliminar su cuenta.");
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(frame, "¿Seguro que desea eliminar a " + u + "?", "Peligro", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            if (session.eliminarUsuario(u, p)) {
                JOptionPane.showMessageDialog(frame, "Usuario eliminado permanentemente.");
                txtUsuario.setText("");
                txtClave.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Credenciales incorrectas. No se pudo eliminar.");
            }
        }
    }
}