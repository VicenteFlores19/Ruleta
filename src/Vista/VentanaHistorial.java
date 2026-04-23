package Vista;

import Controlador.ResultadoController;
import Modelo.Resultado;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaHistorial {
    private final JFrame frame = new JFrame("Historial de Jugadas");
    private final ResultadoController resController; // Dependencia del controlador

    public VentanaHistorial(ResultadoController resController) {
        this.resController = resController;
        configurarVentana();
    }

    private void configurarVentana() {
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Títulos y Tabla
        String[] columnas = {"Número", "Apuesta", "Monto", "Ganancia"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modelo);

        // Llenamos la tabla pidiéndole los datos al controlador
        for (Resultado r : resController.obtenerHistorial()) {
            Object[] fila = {
                    r.getNumeroGanador(),
                    r.getTipoApuesta(),
                    "$" + r.getMontoApostado(),
                    (r.getGanancia() > 0 ? "+$" : "-$") + Math.abs(r.getGanancia())
            };
            modelo.addRow(fila);
        }

        frame.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> frame.dispose()); // Cierra solo esta ventana
        frame.add(btnVolver, BorderLayout.SOUTH);
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}