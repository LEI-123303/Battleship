package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes Unitários da Barco: Caravel")
class CaravelTest {

    Caravel caravel;

    @BeforeEach
    void setUp() {
        // Certifica-te que aqui dentro tens apenas os números, sem escrever "row:"
        caravel = new Caravel(Compass.NORTH, new Position(0, 0));
    }

    @Test
    @DisplayName("Caravel deve ter tamanho 2")
    void testTamanho() {
        assertEquals(2, caravel.getSize(), "O tamanho deve ser 2");
    }

    @Test
    @DisplayName("Deve criar corretamente virado para EAST/WEST")
    void testOrientacaoHorizontal() {
        // Testa o outro 'case' do switch (EAST ou WEST)
        Caravel c = new Caravel(Compass.EAST, new Position(0, 0));
        assertEquals(2, c.getSize());
    }

    @Test
    @DisplayName("Deve lançar erro se a direção for Desconhecida")
    void testDirecaoDesconhecida() {
        // Usa Compass.UNKNOWN para ativar o 'default' do switch
        assertThrows(IllegalArgumentException.class, () -> {
            new Caravel(Compass.UNKNOWN, new Position(0, 0));
        });
    }
}