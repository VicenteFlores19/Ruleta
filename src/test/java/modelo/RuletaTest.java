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
}