package modelo;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class GestorArchivo {
    // Nombre del archivo que se creará en la carpeta de tu proyecto
    private static final String RUTA_ARCHIVO = "historial_apuestas.txt";

    public void guardarResultado(Resultado resultado) {
        // El 'true' significa "Append": agrega texto al final sin borrar lo anterior
        try (FileWriter fw = new FileWriter(RUTA_ARCHIVO, true);
             PrintWriter pw = new PrintWriter(fw)) {

            // Usamos el método toString() de tu clase Resultado
            pw.println(resultado.toString());

        } catch (IOException e) {
            System.err.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }
}