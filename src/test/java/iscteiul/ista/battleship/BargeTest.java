package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes Unitários da Barco: Barge")
class BargeTest {
    Barge barco;

    @BeforeEach
    void setUp() {

        barco = new Barge(Compass.NORTH, new Position(0, 0));
    }

    @Test
    @DisplayName("Barge deve ter tamanho 1")
    void testTamanho() {
        assertEquals(1, barco.getSize(), "O tamanho deve ser 1");
    }
}