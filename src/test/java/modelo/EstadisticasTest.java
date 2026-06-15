package modelo;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EstadisticaTest {

    @Test
    void testCalcular_HistorialMixto_CalculaCorrectamente() {
        // Arrange: Preparamos un historial mixto simulado
        List<Resultado> historialSimulado = new ArrayList<>();
        historialSimulado.add(new Resultado(10, 100, 100, "ROJO"));  // Victoria
        historialSimulado.add(new Resultado(15, 100, 100, "NEGRO")); // Victoria (Racha = 2)
        historialSimulado.add(new Resultado(0, 100, -100, "ROJO"));  // Derrota (Corta racha)
        historialSimulado.add(null);                                 // Nulo, tu código debe saltarlo
        historialSimulado.add(new Resultado(20, 100, 100, "ROJO"));  // Victoria

        // Creamos un repositorio falso al vuelo que devuelva nuestro historial
        IRepositorioResultados repoFalso = new IRepositorioResultados() {
            @Override
            public void guardar(Resultado resultado) {}

            @Override
            public List<Resultado> obtenerTodos() {
                return historialSimulado;
            }
        };

        Estadistica est = new Estadistica();

        // Act: Hacemos que la estadística calcule usando nuestro repositorio
        est.calcular(repoFalso);

        // Assert: Verificamos que las matemáticas sean exactas
        assertEquals(4, est.getTotalJugadas(), "Debe contar 4 jugadas reales, ignorando el null");
        assertEquals(3, est.getVictorias(), "Deben haber 3 victorias en total");
        assertEquals(2, est.getRachaMaxima(), "La racha máxima consecutiva es 2");
        assertEquals(75.0, est.getPorcentajeVictorias(), "El porcentaje debe ser 75.0 (3 de 4)");
        assertEquals("ROJO", est.getTipoMasJugado(), "El tipo más jugado fue ROJO");
    }
}