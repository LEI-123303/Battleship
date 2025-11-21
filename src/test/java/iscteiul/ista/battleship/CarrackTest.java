package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes Unitários da Barco: Barge")
class CarrackTest {
    Carrack nau;

    @BeforeEach
    void setUp() {
        nau = new Carrack(Compass.NORTH, new Position(0, 0));
    }

    @Test
    @DisplayName("Barge deve ter tamanho 3")
    void testTamanho() {
        assertEquals(3, nau.getSize(), "O tamanho deve ser 3"); // Verifica se o método é getSize() ou getTamanho()
    }

    @Test
    @DisplayName("Deve lançar erro com direção Desconhecida")
    void testDirecaoInvalida() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Carrack(Compass.UNKNOWN, new Position(0,0));
        });
    }

    @Test
    @DisplayName("Deve criar corretamente virado para EAST/WEST")
    void testOrientacaoHorizontal() {
        // Testa o bloco 'case EAST: case WEST:' do código
        Carrack c = new Carrack(Compass.EAST, new Position(0, 0));
        assertEquals(3, c.getSize());
    }
}