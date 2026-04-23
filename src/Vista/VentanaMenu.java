package Vista;

import Controlador.SessionController;
import Controlador.RuletaController;
import Modelo.Ruleta;
import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa el menú principal tras iniciar sesión.
 * Usa el patrón MVC y el principio de Responsabilidad Única.
 */
public class VentanaMenu {

    private final JFrame frame = new JFrame("RULETA - Casino Black Cat");
    private final SessionController session; // El cerebro de la sesión
    private final Ruleta ruleta;             // El modelo del juego

    private JLabel lblNombreActivo;
    private JLabel lblSaldoActivo;

    /**
     * Constructor: Recibe la sesión desde el Login.
     */
    public VentanaMenu(SessionController session) {
        this.session = session;
        // Iniciamos la ruleta con saldo 0 como pide el PDF
        this.ruleta = new Ruleta(0);
        configurarVentana();
    }

    private void configurarVentana() {
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(crearPanelLateral(), BorderLayout.WEST);
        frame.add(crearPanelCentral(), BorderLayout.CENTER);
    }

    /**
     * Crea los botones de navegación de la izquierda.
     */
    private JPanel crearPanelLateral() {
        // Aumentamos a 5 filas para incluir el botón de Perfil
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JButton btnJugar = new JButton("Jugar");
        btnJugar.addActionListener(e -> abrirJuego());

        // REQUISITO: Botón de Perfil para gestionar datos
        JButton btnPerfil = new JButton("Mi Perfil");
        btnPerfil.addActionListener(e -> abrirPerfil());

        JButton btnHistorial = new JButton("Historial");

        JButton btnSalir = new JButton("Cerrar Sesión");
        btnSalir.addActionListener(e -> cerrarSesion());

        panel.add(new JLabel("Navegación", SwingConstants.CENTER));
        panel.add(btnJugar);
        panel.add(btnPerfil);
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

        // Obtenemos el nombre mediante el Getter de la sesión
        String saludo = "<html><h2>Bienvenido, " + session.getNombreUsuario() + "</h2>";
        String info = "<p>Use el panel lateral para navegar por el casino.</p></html>";

        lblNombreActivo = new JLabel(saludo);
        // Mostramos el saldo actual usando el Getter del modelo
        lblSaldoActivo = new JLabel("Saldo en cuenta: $" + ruleta.getSaldo());
        lblSaldoActivo.setFont(new Font("Arial", Font.BOLD, 14));

        panel.add(lblNombreActivo, BorderLayout.NORTH);
        panel.add(new JLabel(info), BorderLayout.CENTER);
        panel.add(lblSaldoActivo, BorderLayout.SOUTH);
        return panel;
    }

    /**
     * Lógica para la sección de Perfil (Recarga y cambio de nombre)
     */
    private void abrirPerfil() {
        String msg = "Usuario: " + session.getUsuarioActual().getUsername() + "\n" +
                "Nombre: " + session.getNombreUsuario() + "\n" +
                "Saldo: $" + ruleta.getSaldo();

        String[] opciones = {"Cambiar Nombre", "Recargar Saldo", "Volver"};
        int seleccion = JOptionPane.showOptionDialog(frame, msg, "Gestión de Perfil",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        if (seleccion == 0) { // Cambiar Nombre
            String nuevo = JOptionPane.showInputDialog(frame, "Nuevo nombre:");
            session.getUsuarioActual().setNombre(nuevo); // Setter con validación
            lblNombreActivo.setText("<html><h2>Bienvenido, " + session.getNombreUsuario() + "</h2></html>");
        } else if (seleccion == 1) { // Recargar Saldo
            String montoStr = JOptionPane.showInputDialog(frame, "Monto a depositar:");
            try {
                int monto = Integer.parseInt(montoStr);
                ruleta.depositar(monto); // Método de negocio
                lblSaldoActivo.setText("Saldo en cuenta: $" + ruleta.getSaldo());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Monto no válido");
            }
        }
    }

    private void abrirJuego() {
        frame.dispose();
        // Al abrir el juego, creamos su controlador y le pasamos la sesión y la ruleta
        RuletaController rc = new RuletaController(session, ruleta);
        new VentanaJuego(session, rc).mostrarVentana();
    }

    private void cerrarSesion() {
        session.cerrarSesion();
        frame.dispose();
        new VentanaLogin(session).mostrarVentana(); // Volvemos al login con la misma sesión
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}