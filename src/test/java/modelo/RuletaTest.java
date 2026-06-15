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
        int saldoInvalido = -5000;

        Exception excepcion = assertThrows(IllegalArgumentException.class, () -> {
            new Ruleta(saldoInvalido, repoMock);
        });

        assertEquals("Saldo inicial inválido", excepcion.getMessage());
    }

    @Test
    void testDepositar_MontoValido_IncrementaSaldo() {
        Ruleta ruleta = new Ruleta(1000, repoMock);
        int montoDeposito = 500;

        ruleta.depositar(montoDeposito);

        assertEquals(1500, ruleta.getSaldo(), "El saldo debería ser 1500 tras depositar 500");
    }

    @Test
    void testJugar_ApuestaNula_LanzaExcepcion() {
        Ruleta ruleta = new Ruleta(1000, repoMock);

        Exception excepcion = assertThrows(IllegalArgumentException.class, () -> {
            ruleta.jugar(null);
        });

        assertEquals("Apuesta requerida", excepcion.getMessage());
    }

    @Test
    void testJugar_MontoMayorAlSaldo_LanzaExcepcion() {
        Ruleta ruleta = new Ruleta(1000, repoMock);

        // Creamos una apuesta gigante al vuelo usando tu clase abstracta para la prueba
        ApuestaBase apuestaGigante = new ApuestaBase(5000, "PRUEBA") {
            @Override
            public boolean acierta(int numero, String color) {
                return false;
            }
        };

        Exception excepcion = assertThrows(IllegalArgumentException.class, () -> {
            ruleta.jugar(apuestaGigante);
        });

        assertEquals("Saldo insuficiente", excepcion.getMessage());
    }
}