package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da Classe Fleet (Frota)")
public class FleetTest {

    private Fleet fleet;

    @BeforeEach
    void setUp() {
        fleet = new Fleet();
    }

    private Barge createTestBarge(int r, int c) {
        return new Barge(Compass.NORTH, new Position(r, c));
    }

    // A. AddShip: Testar regras de validação

    @Test
    @DisplayName("Adicionar barco fora do tabuleiro (limite 10) deve falhar (cobre isInsideBoard)")
    void testAddShipOutsideBoard() {
        // Barco (tamanho 1) em (10, 10), fora do tabuleiro 10x10 (0-9)
        assertFalse(fleet.addShip(createTestBarge(10, 10)), "Barco fora do tabuleiro deve falhar a adição.");
        // Barco (tamanho 1) em (-1, -1), fora do tabuleiro
        assertFalse(fleet.addShip(createTestBarge(-1, -1)), "Barco fora do tabuleiro deve falhar a adição.");
    }

    @Test
    @DisplayName("Adicionar barco em posição de colisão deve falhar (cobre colisionRisk)")
    void testAddShipCollisionRisk() {
        // Barco 1 em (5, 5)
        fleet.addShip(createTestBarge(5, 5));

        // Barco 2 adjacente em (5, 6)
        assertFalse(fleet.addShip(createTestBarge(5, 6)), "Barco adjacente deve falhar a adição (colisão/tooCloseTo).");
    }

    @Test
    @DisplayName("Deve respeitar o limite da frota (Máximo 10)")
    void testAddShipsLimit() {
        int count = 0;
        // Estratégia: Adicionar barcos espaçados (ex: 0,0; 0,2; 0,4...)
        // para não violar a regra de 'tooCloseTo'
        for (int row = 0; row < 10; row += 2) {
            for (int col = 0; col < 10; col += 2) {
                if (count < Fleet.FLEET_SIZE) {
                    boolean added = fleet.addShip(new Barge(Compass.NORTH, new Position(row, col)));
                    assertTrue(added, "O barco " + (count + 1) + " deveria ter sido adicionado");
                    count++;
                }
            }
        }

        // Verificar que temos 10 navios
        assertEquals(10, fleet.getShips().size());

        // Tentar adicionar o 11º navio (deve falhar)
        // Posição (9,9) está livre de colisões neste esquema, por isso serve para testar o limite
        boolean extraShip = fleet.addShip(new Barge(Compass.NORTH, new Position(9, 9)));
        assertFalse(extraShip, "Não deve ser possível adicionar mais do que 10 navios");
    }

    @Test
    @DisplayName("getFloatingShips deve retornar apenas barcos a flutuar")
    void testGetFloatingShips() {
        IShip s_floating = createTestBarge(1, 1);
        IShip s_sunk = createTestBarge(5, 5);
        s_sunk.shoot(s_sunk.getPositions().get(0)); // Afundar

        fleet.addShip(s_floating);
        fleet.addShip(s_sunk);

        assertEquals(1, fleet.getFloatingShips().size());
    }

    @Test
    @DisplayName("getShipsLike deve retornar barcos da categoria correta")
    void testGetShipsLike() {
        IShip barge = createTestBarge(1, 1);
        fleet.addShip(barge);
        assertEquals(1, fleet.getShipsLike("Barca").size());
        assertTrue(fleet.getShipsLike("Submarino").isEmpty());
    }
}