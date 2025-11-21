package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes Unitários da Fragata (Frigate)")
class FrigateTest {

    Frigate fragata;

    @BeforeEach
    void setUp() {
        // Inicializa a fragata (Tamanho 4)
        fragata = new Frigate(Compass.NORTH, new Position(0, 0));
    }

    @Test
    @DisplayName("Deve ter tamanho 4")
    void testTamanho() {
        assertEquals(4, fragata.getSize());
    }

    @Test
    @DisplayName("Deve lançar erro com direção Desconhecida")
    void testDirecaoInvalida() {
        // Este teste cobre o 'default' do switch no Frigate.java
        assertThrows(IllegalArgumentException.class, () -> {
            new Frigate(Compass.UNKNOWN, new Position(0, 0));
        });
    }

    @Test
    @DisplayName("Deve criar corretamente virado para EAST/WEST")
    void testOrientacaoHorizontal() {
        // Testa o caso horizontal (EAST/WEST)
        Frigate f = new Frigate(Compass.EAST, new Position(0, 0));
        assertEquals(4, f.getSize());
    }
}