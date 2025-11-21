package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da Classe Abstrata Ship (Barco Genérico)")
public class ShipTest {

    private Barge createTestBarge(int row, int col, Compass bearing) {
        return new Barge(bearing, new Position(row, col));
    }
    private Frigate createTestFrigate(int row, int col, Compass bearing) {
        return new Frigate(bearing, new Position(row, col));
    }

    @Nested
    @DisplayName("Grupo 1: Estado, Colisão e Bounding Box")
    class StateCollisionAndBoundsTests {
        @Test
        @DisplayName("stillFloating e shoot (cobre Barge)")
        void testStillFloatingAndShoot() {
            Barge barge = createTestBarge(1, 1, Compass.NORTH);
            barge.shoot(barge.getPositions().get(0));
            assertFalse(barge.stillFloating());
        }

        @Test
        @DisplayName("tooCloseTo(IShip) deve ser true se adjacente")
        void testTooCloseToShipTrue() {
            Barge b1 = createTestBarge(5, 5, Compass.NORTH);
            Barge b2 = createTestBarge(5, 6, Compass.SOUTH);
            assertTrue(b1.tooCloseTo(b2));
        }

        @Test
        @DisplayName("Bounding Box de um Frigate (cobre todos os getters de limites)")
        void testFrigateBounds() {
            Frigate frigate = createTestFrigate(2, 3, Compass.NORTH); // (2,3) a (5,3)
            assertEquals(2, frigate.getTopMostPos());
            assertEquals(5, frigate.getBottomMostPos());
            assertEquals(3, frigate.getLeftMostPos());
            assertEquals(3, frigate.getRightMostPos());
        }
    }

    @Nested
    @DisplayName("Grupo 2: Factory Method (buildShip)")
    class FactoryMethodTests {
        @Test
        @DisplayName("buildShip deve retornar a classe correta (cobre Switch)")
        void testBuildShipKnown() {
            assertTrue(Ship.buildShip("caravela", Compass.NORTH, new Position(0, 0)) instanceof Caravel);
            assertTrue(Ship.buildShip("galeao", Compass.NORTH, new Position(0, 0)) instanceof Galleon);
            assertNull(Ship.buildShip("submarino", Compass.NORTH, new Position(0, 0)));
        }
    }
}