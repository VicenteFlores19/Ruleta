package Vista;

import Controlador.SessionController;
import Controlador.RuletaController;
import Controlador.ResultadoController;
import Modelo.Ruleta;
import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa el menú principal.
 * Actúa como el centro de navegación del sistema.
 */
public class VentanaMenu {

    private final JFrame frame = new JFrame("RULETA - Casino Black Cat");
    private final SessionController session; // Inyección de dependencia única [cite: 348]
    private final Ruleta ruleta;             // El modelo del juego

    private JLabel lblNombreActivo;
    private JLabel lblSaldoActivo;

    /**
     * Constructor 1: Usado al iniciar sesión por primera vez.
     */
    public VentanaMenu(SessionController session) {
        this.session = session;
        this.ruleta = new Ruleta(0);
        configurarVentana();
    }

    /**
     * Constructor 2: Usado al volver desde el juego para mantener el estado.
     */
    public VentanaMenu(SessionController session, Ruleta ruletaExistente) {
        this.session = session;
        this.ruleta = ruletaExistente;
        configurarVentana();
    }

    private void configurarVentana() {
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(crearPanelLateral(), BorderLayout.WEST);
        frame.add(crearPanelCentral(), BorderLayout.CENTER);
    }

    private JPanel crearPanelLateral() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JButton btnJugar = new JButton("Jugar");
        JButton btnPerfil = new JButton("Mi Perfil");
        JButton btnHistorial = new JButton("Historial");
        JButton btnSalir = new JButton("Cerrar Sesión");

        // Listeners de navegación
        btnJugar.addActionListener(e -> abrirJuego());
        btnPerfil.addActionListener(e -> abrirPerfil());
        btnHistorial.addActionListener(e -> abrirHistorial()); // Conexión Iteración 05 [cite: 294]
        btnSalir.addActionListener(e -> cerrarSesion());

        panel.add(new JLabel("Navegación", SwingConstants.CENTER));
        panel.add(btnJugar);
        panel.add(btnPerfil);
        panel.add(btnHistorial);
        panel.add(btnSalir);
        return panel;
    }

    private JPanel crearPanelCentral() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Formato HTML corregido para evitar etiquetas visibles
        String saludo = "<html><h2>Bienvenido, " + session.getNombreUsuario() + "</h2></html>";
        String info = "<html><p>Use el panel lateral para navegar por el casino.</p></html>";

        lblNombreActivo = new JLabel(saludo);
        // Obtenemos el saldo directamente del usuario de la sesión para mayor trazabilidad
        lblSaldoActivo = new JLabel("Saldo en cuenta: $" + session.getUsuarioActual().getSaldo());
        lblSaldoActivo.setFont(new Font("Arial", Font.BOLD, 14));

        panel.add(lblNombreActivo, BorderLayout.NORTH);
        panel.add(new JLabel(info), BorderLayout.CENTER);
        panel.add(lblSaldoActivo, BorderLayout.SOUTH);
        return panel;
    }

    private void abrirPerfil() {
        // Obtenemos los datos desde el modelo Usuario para cumplir con el encapsulamiento
        String msg = "Usuario: " + session.getUsuarioActual().getUsername() + "\n" +
                "Nombre: " + session.getNombreUsuario() + "\n" +
                "Saldo: $" + session.getUsuarioActual().getSaldo();

        String[] opciones = {"Cambiar Nombre", "Recargar Saldo", "Volver"};
        int seleccion = JOptionPane.showOptionDialog(frame, msg, "Gestión de Perfil",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        if (seleccion == 0) { // Cambiar Nombre
            String nuevo = JOptionPane.showInputDialog(frame, "Nuevo nombre:");
            session.getUsuarioActual().setNombre(nuevo);
            lblNombreActivo.setText("<html><h2>Bienvenido, " + session.getNombreUsuario() + "</h2></html>");
        } else if (seleccion == 1) { // Recargar Saldo
            String montoStr = JOptionPane.showInputDialog(frame, "Monto a depositar:");
            try {
                int monto = Integer.parseInt(montoStr);
                session.getUsuarioActual().setSaldo(session.getUsuarioActual().getSaldo() + monto);
                lblSaldoActivo.setText("Saldo en cuenta: $" + session.getUsuarioActual().getSaldo());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Monto no válido");
            }
        }
    }

    private void abrirJuego() {
        frame.dispose();
        // Inyección de dependencias en orden: Ruleta, luego Sesión [cite: 343, 344]
        RuletaController rc = new RuletaController(ruleta, session);
        new VentanaJuego(session, rc).mostrarVentana();
    }

    private void abrirHistorial() {
        // La vista depende del controlador y no del modelo directamente [cite: 326]
        ResultadoController rc = new ResultadoController(session);
        new VentanaHistorial(rc).mostrarVentana();
    }

    private void cerrarSesion() {
        session.cerrarSesion();
        frame.dispose();
        new VentanaLogin(session).mostrarVentana();
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}