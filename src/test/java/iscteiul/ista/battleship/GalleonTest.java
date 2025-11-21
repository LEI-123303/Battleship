package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes Unitários do Galeão (Galleon)")
class GalleonTest {

    Galleon galeao;

    @BeforeEach
    void setUp() {
        // Inicializa o galeão (Tamanho 5)
        galeao = new Galleon(Compass.NORTH, new Position(0, 0));
    }

    @Test
    @DisplayName("Deve ter tamanho 5")
    void testTamanho() {
        assertEquals(5, galeao.getSize());
    }

    @Test
    @DisplayName("Deve lançar erro com direção Desconhecida")
    void testDirecaoInvalida() {
        // Cobre o 'default' do switch
        assertThrows(IllegalArgumentException.class, () -> {
            new Galleon(Compass.UNKNOWN, new Position(0, 0));
        });
    }

    @Test
    @DisplayName("Deve criar corretamente virado para SUL")
    void testOrientacaoSul() {
        // Obriga o código a entrar no método fillSouth()
        Galleon g = new Galleon(Compass.SOUTH, new Position(0, 0));
        assertEquals(5, g.getSize());
    }

    @Test
    @DisplayName("Deve criar corretamente virado para ESTE")
    void testOrientacaoEste() {
        // Obriga o código a entrar no método fillEast()
        Galleon g = new Galleon(Compass.EAST, new Position(0, 0));
        assertEquals(5, g.getSize());
    }

    @Test
    @DisplayName("Deve criar corretamente virado para OESTE")
    void testOrientacaoOeste() {
        // Obriga o código a entrar no método fillWest()
        Galleon g = new Galleon(Compass.WEST, new Position(0, 0));
        assertEquals(5, g.getSize());
    }
}