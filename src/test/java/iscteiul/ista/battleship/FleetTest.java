package iscteiul.ista.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.List;

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
    @DisplayName("Adicionar navios até atingir o limite da frota (10) deve falhar o 11º")
    void testAddShipsLimit() {
        // CORREÇÃO: Usar coordenadas seguras (0, 0) a (0, 9) que não colidem e estão dentro.
        for (int i = 0; i < IFleet.FLEET_SIZE; i++) {
            assertTrue(fleet.addShip(createTestBarge(0, i)), "O barco " + (i + 1) + " deve ser adicionado.");
        }

        // Tenta adicionar o 11º
        assertFalse(fleet.addShip(createTestBarge(9, 9)), "O 11º barco deve falhar a adição (limite excedido).");
        assertEquals(IFleet.FLEET_SIZE.intValue(), fleet.getShips().size(), "A frota deve ter exatamente 10 navios.");
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