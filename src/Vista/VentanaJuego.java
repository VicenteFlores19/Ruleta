package Vista;

import Controlador.RuletaController;
import Controlador.SessionController;
import Modelo.TipoDeApuesta;

import javax.swing.*;
import java.awt.*;

public class VentanaJuego {
    private final SessionController session;
    private final RuletaController ruletaController;

    private final JFrame frame = new JFrame("Ruleta - Casino Black Cat");
    private JLabel lblSaldo;
    private JComboBox<String> cboTipo;
    private JTextField txtMonto;
    private JButton btnApostar;
    private JButton btnVolver;

    public VentanaJuego(SessionController session, RuletaController ruletaController) {
        this.session = session;
        this.ruletaController = ruletaController;
        configurarVentana();
    }

    private void configurarVentana() {
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        // Obtenemos el saldo a través del controlador
        lblSaldo = new JLabel("Saldo: $" + ruletaController.getSaldoActual());

        // Las opciones del combo deben coincidir EXACTAMENTE con los nombres de tu Enum
        cboTipo = new JComboBox<>(new String[]{"ROJO", "NEGRO", "PAR", "IMPAR"});
        txtMonto = new JTextField();

        btnApostar = new JButton("Girar Ruleta");
        btnVolver = new JButton("Volver al Menú");

        btnApostar.addActionListener(e -> apostar());
        btnVolver.addActionListener(e -> volver());

        frame.add(new JLabel("Tipo de Apuesta:"));
        frame.add(cboTipo);
        frame.add(new JLabel("Monto a apostar:"));
        frame.add(txtMonto);
        frame.add(btnApostar);
        frame.add(lblSaldo);
        frame.add(new JLabel("")); // Espacio vacío
        frame.add(btnVolver);
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void apostar() {
        try {
            int monto = Integer.parseInt(txtMonto.getText());

            // CONVERSIÓN MÁGICA: Convertimos el texto seleccionado ("ROJO") al Enum TipoDeApuesta.ROJO
            String seleccion = cboTipo.getSelectedItem().toString().toUpperCase();
            TipoDeApuesta tipo = TipoDeApuesta.valueOf(seleccion);

            // Le pasamos el trabajo al controlador
            String mensaje = ruletaController.jugar(monto, tipo);

            // Mostramos resultado y actualizamos saldo
            JOptionPane.showMessageDialog(frame, mensaje);
            lblSaldo.setText("Saldo: $" + ruletaController.getSaldoActual());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Ingrese un monto numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volver() {
        frame.dispose();
        // Ahora le devolvemos la ruleta con nuestro saldo al menú
        new VentanaMenu(session, ruletaController.getRuletaModelo()).mostrarVentana();
    }
}