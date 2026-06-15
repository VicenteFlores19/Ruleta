package modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RuletaTest {

    private IRepositorioResultados repoMock;

    @BeforeEach
    void setUp() {
        // Inicializamos un repositorio simulado (vacío) antes de cada prueba
        repoMock = new RepositorioArchivo();
    }

    @Test
    void testConstructor_SaldoNegativo_LanzaExcepcion() {
        // Arrange (Preparar los datos)
        int saldoInvalido = -5000;

        // Act & Assert (Ejecutar y Verificar)
        Exception excepcion = assertThrows(IllegalArgumentException.class, () -> {
            new Ruleta(saldoInvalido, repoMock);
        });

        // Verificamos que el mensaje de error sea exactamente el esperado
        assertEquals("Saldo inicial inválido", excepcion.getMessage());
    }

    @Test
    void testDepositar_MontoValido_IncrementaSaldo() {
        // Arrange: Preparamos una ruleta con 1000 de saldo inicial
        Ruleta ruleta = new Ruleta(1000, repoMock);
        int montoDeposito = 500;

        // Act: Ejecutamos el método depositar (saldrá en rojo al principio)
        ruleta.depositar(montoDeposito);

        // Assert: Verificamos que 1000 + 500 sea exactamente 1500
        assertEquals(1500, ruleta.getSaldo(), "El saldo debería ser 1500 tras depositar 500");
    }

    @Test
    void testJugar_ApuestaNula_LanzaExcepcion() {
        Ruleta ruleta = new Ruleta(1000, repoMock);

        Exception excepcion = assertThrows(IllegalArgumentException.class, () -> {
            ruleta.jugar(null); // Esto saldrá rojo al principio
        });

        assertEquals("Apuesta requerida", excepcion.getMessage());
    }
}