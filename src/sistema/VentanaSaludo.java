package sistema;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa la ventana de bienvenida al usuario.
 */
public class VentanaSaludo {

    private final JFrame frame = new JFrame("Casino Black Cat - Lobby");
    private final String nombreJugador;

    /**
     * Constructor que recibe el nombre del usuario validado.
     */
    public VentanaSaludo(String nombreJugador) {
        this.nombreJugador = nombreJugador;
        configurarVentana();
    }

    /**
     * Arma el diseño visual del saludo.
     */
    private void configurarVentana() {
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel lblSaludo = new JLabel("¡Bienvenido a la mesa, " + nombreJugador + "!", SwingConstants.CENTER);
        lblSaludo.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(lblSaludo, BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar Sesión");
        btnCerrar.addActionListener(e -> cerrarSesion());
        frame.add(btnCerrar, BorderLayout.SOUTH);
    }

    /**
     * Cierra esta ventana y vuelve al login.
     */
    private void cerrarSesion() {
        frame.dispose(); // Destruye esta ventana
        new VentanaLogin().mostrarVentana(); // Revive la del login
    }

    /**
     * Muestra la ventana centrada en pantalla.
     */
    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}