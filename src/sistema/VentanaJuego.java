package sistema;

import juego.Ruleta; // ¡Importamos el cerebro lógico!
import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa la interfaz gráfica de la mesa de Ruleta.
 * Delega toda la lógica matemática a la clase Ruleta.
 */
public class VentanaJuego {

    private final JFrame frame = new JFrame("Mesa de Ruleta - Casino Black Cat");
    private final Ruleta motor; // El cerebro del juego

    // Componentes visuales
    private JComboBox<String> cmbTipoApuesta;
    private JTextField txtMonto;
    private JLabel lblResultado;
    private JLabel lblSaldo;

    /**
     * Constructor: Inicializa la vista y crea el motor con saldo inicial.
     */
    public VentanaJuego() {
        this.motor = new Ruleta(1000); // Sentamos al jugador con 1000 fichas
        configurarVentana();
    }

    /**
     * Arma el layout principal.
     */
    private void configurarVentana() {
        frame.setSize(500, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra esta ventana, no todo el programa
        frame.setLayout(new BorderLayout());

        frame.add(crearPanelNorte(), BorderLayout.NORTH);
        frame.add(crearPanelControles(), BorderLayout.CENTER);
        frame.add(crearPanelResultados(), BorderLayout.SOUTH);
    }

    /**
     * Crea el botón de acción principal.
     */
    private JPanel crearPanelNorte() {
        JPanel panel = new JPanel();
        JButton btnGirar = new JButton("¡Girar Ruleta!");
        btnGirar.addActionListener(e -> capturarYJugar());
        panel.add(btnGirar);
        return panel;
    }

    /**
     * Crea los campos para ingresar la apuesta.
     */
    private JPanel crearPanelControles() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        String[] opciones = {"Rojo (R)", "Negro (N)", "Par (P)", "Impar (I)"};
        cmbTipoApuesta = new JComboBox<>(opciones);
        txtMonto = new JTextField("100");

        panel.add(new JLabel("Tipo de Apuesta:"));
        panel.add(cmbTipoApuesta);
        panel.add(new JLabel("Monto a apostar:"));
        panel.add(txtMonto);
        return panel;
    }

    /**
     * Crea las etiquetas de texto para mostrar victorias o derrotas.
     */
    private JPanel crearPanelResultados() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblResultado = new JLabel("Haz tu apuesta para comenzar.", SwingConstants.CENTER);
        lblSaldo = new JLabel("Saldo actual: " + motor.getSaldo(), SwingConstants.CENTER);

        panel.add(lblResultado);
        panel.add(lblSaldo);
        return panel;
    }

    /**
     * Extrae los datos de la interfaz y valida que sean números.
     */
    private void capturarYJugar() {
        try {
            int monto = Integer.parseInt(txtMonto.getText());
            if (monto > motor.getSaldo() || monto <= 0) {
                JOptionPane.showMessageDialog(frame, "Monto inválido o saldo insuficiente.");
                return;
            }
            // Extrae la letra clave (R, N, P, I) del texto seleccionado
            String seleccion = (String) cmbTipoApuesta.getSelectedItem();
            char tipo = seleccion.charAt(seleccion.indexOf('(') + 1);

            comunicarConMotor(tipo, monto);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Ingrese un monto numérico.");
        }
    }

    /**
     * Envía los datos a la clase Ruleta y dibuja la respuesta.
     */
    private void comunicarConMotor(char tipo, int monto) {
        int numeroSorteado = motor.girar();
        int ganancia = motor.evaluarApuesta(numeroSorteado, tipo, monto);

        String msj = "Salió el " + numeroSorteado + ". ";
        msj += (ganancia > 0) ? "¡GANASTE " + ganancia + " fichas!" : "Perdiste " + monto + " fichas.";

        lblResultado.setText(msj);
        lblSaldo.setText("Saldo actual: " + motor.getSaldo());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}