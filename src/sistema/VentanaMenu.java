package sistema;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa el menú principal tras iniciar sesión.
 * Actúa como puente para acceder al juego o al historial.
 */
public class VentanaMenu {

    private final JFrame frame = new JFrame("RULETA - Casino Black Cat");
    private final String nombreJugador;

    /**
     * Constructor que inicializa el menú con el nombre del jugador.
     */
    public VentanaMenu(String nombre) {
        this.nombreJugador = nombre;
        configurarVentana();
    }

    /**
     * Arma el layout principal dividiendo la pantalla en zonas.
     */
    private void configurarVentana() {
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(crearPanelLateral(), BorderLayout.WEST);
        frame.add(crearPanelCentral(), BorderLayout.CENTER);
    }

    /**
     * Crea los botones de navegación de la izquierda.
     */
    private JPanel crearPanelLateral() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JButton btnJugar = new JButton("Jugar");
        btnJugar.addActionListener(e -> abrirJuego());

        JButton btnHistorial = new JButton("Historial");
        // TODO: btnHistorial.addActionListener(e -> abrirHistorial());

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> cerrarSesion());

        panel.add(new JLabel("Inicio", SwingConstants.CENTER));
        panel.add(btnJugar);
        panel.add(btnHistorial);
        panel.add(btnSalir);
        return panel;
    }

    /**
     * Crea el texto explicativo y de bienvenida en el centro.
     */
    private JPanel crearPanelCentral() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String texto = "<html><h3>Bienvenido/a al menú principal.</h3>" +
                "<p>A la izquierda tienes:</p>" +
                "<ul><li><b>Jugar:</b> abre la ventana de juego.</li>" +
                "<li><b>Historial:</b> abre la ventana de historial.</li>" +
                "<li><b>Salir:</b> cierra sesión y vuelve al login.</li></ul></html>";

        panel.add(new JLabel(texto), BorderLayout.NORTH);
        panel.add(new JLabel("Jugador activo: " + nombreJugador), BorderLayout.SOUTH);
        return panel;
    }

    /**
     * Acción para el botón Jugar.
     */
    private void abrirJuego() {
        // Dejamos este mensaje temporal hasta que creemos la VentanaJuego
        JOptionPane.showMessageDialog(frame, "La Ventana de Juego se implementará en el próximo paso.");
    }

    /**
     * Acción para cerrar sesión y volver atrás.
     */
    private void cerrarSesion() {
        frame.dispose(); // Destruye el menú
        new VentanaLogin().mostrarVentana(); // Revive el login
    }

    /**
     * Hace visible la ventana en el centro de la pantalla.
     */
    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}