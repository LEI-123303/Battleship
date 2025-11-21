package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da Lógica de Jogo (Game)")
class GameTest {

    Game game;
    Fleet fleet;

    @BeforeEach
    void setUp() {
        fleet = new Fleet();
        // Adiciona uma Barca em (0,0) para termos um alvo
        fleet.addShip(new Barge(Compass.NORTH, new Position(0, 0)));
        game = new Game(fleet);
    }

    @Test
    @DisplayName("Deve registar um tiro falhado (Água)")
    void testTiroNaAgua() {
        IShip navioAtingido = game.fire(new Position(5, 5)); // Posição vazia

        assertNull(navioAtingido, "Tiro na água deve devolver null");
        assertEquals(0, game.getHits(), "Não deve contar como Hit");
        assertEquals(1, game.getShots().size(), "Deve registar o tiro na lista");
    }

    @Test
    @DisplayName("Deve registar um tiro certeiro e afundar")
    void testTiroCerteiro() {
        // Atira na posição do barco (0,0)
        IShip navioAtingido = game.fire(new Position(0, 0));

        assertNotNull(navioAtingido, "Deve devolver o navio atingido");
        assertEquals("Barca", navioAtingido.getCategory());
        assertEquals(1, game.getHits(), "Deve contar 1 Hit");
        assertEquals(1, game.getSunkShips(), "Como a Barca tem tamanho 1, deve afundar logo");
    }

    @Test
    @DisplayName("Deve contar navios restantes")
    void testNaviosRestantes() {
        // No início temos 1 navio
        assertEquals(1, game.getRemainingShips());

        // Afundamos o navio
        game.fire(new Position(0, 0));

        // Agora restam 0
        assertEquals(0, game.getRemainingShips());
    }

    @Test
    @DisplayName("Deve detetar tiros repetidos")
    void testTiroRepetido() {
        game.fire(new Position(2, 2)); // Primeiro tiro
        game.fire(new Position(2, 2)); // Segundo tiro igual

        assertEquals(1, game.getRepeatedShots(), "Deve contar 1 tiro repetido");
    }

    @Test
    @DisplayName("Deve detetar tiros inválidos (fora do mapa)")
    void testTiroInvalido() {
        game.fire(new Position(-1, 0)); // Posição impossível

        assertEquals(1, game.getInvalidShots(), "Deve contar 1 tiro inválido");
    }

    @Test
    @DisplayName("Deve imprimir tabuleiros sem erros")
    void testPrints() {
        // Apenas invocar para garantir que o código corre (cobertura)
        game.printValidShots();
        game.printFleet();
    }
}