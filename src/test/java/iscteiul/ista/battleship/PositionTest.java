package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da Classe Position")
public class PositionTest {

    @Nested
    @DisplayName("Grupo 1: Criação e Atributos")
    class CreationTests {

        @Test
        @DisplayName("Construtor deve guardar coordenadas")
        void testConstructor() {
            Position p = new Position(5, 10);
            assertEquals(5, p.getRow());
            assertEquals(10, p.getColumn());
        }

        @Test
        @DisplayName("toString não deve falhar")
        void testToString() {
            Position p = new Position(2, 8);
            assertNotNull(p.toString());
        }
    }

    @Nested
    @DisplayName("Grupo 2: Alteração de Estado")
    class StateTests {

        @Test
        @DisplayName("Deve mudar estado com occupy() e shoot()")
        void testStateChanges() {
            Position p = new Position(1, 1);

            // Antes
            assertFalse(p.isOccupied());
            assertFalse(p.isHit());

            // Ação
            p.occupy();
            p.shoot();

            // Depois
            assertTrue(p.isOccupied());
            assertTrue(p.isHit());
        }

        @Test
        @DisplayName("Deve calcular adjacências corretamente")
        void testAdjacency() {
            Position centro = new Position(5, 5);

            assertTrue(centro.isAdjacentTo(new Position(5, 6)), "Deve ser vizinho");
            assertTrue(centro.isAdjacentTo(new Position(4, 4)), "Diagonal também é vizinho");
            assertFalse(centro.isAdjacentTo(new Position(1, 1)), "Longe não é vizinho");
        }
    }

    @Nested
    @DisplayName("Grupo 3: Igualdade")
    class EqualityTests {
        @Test
        void testEquals() {
            Position p1 = new Position(3, 3);
            Position p2 = new Position(3, 3);
            assertEquals(p1, p2);
        }
    }
}