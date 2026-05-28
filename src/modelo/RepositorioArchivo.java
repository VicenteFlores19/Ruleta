package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivo implements IRepositorioResultados {
    private static final String RUTA_ARCHIVO = "historial_apuestas.csv";

    @Override
    public void guardar(Resultado resultado) {
        // Guarda en formato CSV: numeroGanador,montoApostado,ganancia,tipoApuesta
        try (FileWriter fw = new FileWriter(RUTA_ARCHIVO, true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(resultado.getNumeroGanador() + "," +
                    resultado.getMontoApostado() + "," +
                    resultado.getGanancia() + "," +
                    resultado.getTipoApuesta());

        } catch (IOException e) {
            System.err.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }

    @Override
    public List<Resultado> obtenerTodos() {
        List<Resultado> historialGuardado = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            // Lee línea por línea hasta que se acabe el archivo
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(","); // Separa por la coma

                if (partes.length == 4) {
                    int numero = Integer.parseInt(partes[0]);
                    int monto = Integer.parseInt(partes[1]);
                    int ganancia = Integer.parseInt(partes[2]);
                    String tipo = partes[3];

                    // Reconstruye el objeto y lo agrega a la lista
                    historialGuardado.add(new Resultado(numero, monto, ganancia, tipo));
                }
            }
        } catch (IOException e) {
            // Si el archivo no existe aún, no hacemos nada grave, solo retornará la lista vacía
            System.err.println("El historial aún no existe o no se puede leer.");
        }

        return historialGuardado;
    }
}