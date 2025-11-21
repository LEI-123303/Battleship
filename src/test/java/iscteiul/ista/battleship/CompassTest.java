package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompassTest {

    @Test
    @DisplayName("Quantidade: Verifica se existem 5 direções (incluindo UNKNOWN)")
    void testEnumCount() {
        // CORREÇÃO: Agora esperamos 5 valores em vez de 4
        assertEquals(5, Compass.values().length, "O Compass deve ter 5 valores (N, S, E, O, U)");
    }

    @Test
    @DisplayName("Valores: Verifica se todas as direções existem")
    void testEnumValues() {
        assertNotNull(Compass.NORTH, "Deve existir NORTH");
        assertNotNull(Compass.SOUTH, "Deve existir SOUTH");
        assertNotNull(Compass.EAST, "Deve existir EAST");
        assertNotNull(Compass.WEST, "Deve existir WEST (representado por 'o')");
        assertNotNull(Compass.UNKNOWN, "Deve existir UNKNOWN");
    }

    @Test
    @DisplayName("Conversão: Verifica charToCompass (Cobre o switch case)")
    void testCharToCompass() {
        // Testar os casos válidos
        assertEquals(Compass.NORTH, Compass.charToCompass('n'));
        assertEquals(Compass.SOUTH, Compass.charToCompass('s'));
        assertEquals(Compass.EAST, Compass.charToCompass('e'));
        assertEquals(Compass.WEST, Compass.charToCompass('o')); // Nota: no código é 'o' de Oeste

        // Testar casos inválidos ou desconhecidos (vai para o default)
        assertEquals(Compass.UNKNOWN, Compass.charToCompass('u'));
        assertEquals(Compass.UNKNOWN, Compass.charToCompass('x'), "Carateres inválidos devem dar UNKNOWN");
    }

    @Test
    @DisplayName("Métodos Auxiliares: getDirection e toString")
    void testAuxiliaryMethods() {
        // Testar getDirection
        assertEquals('n', Compass.NORTH.getDirection());
        assertEquals('o', Compass.WEST.getDirection());

        // Testar toString
        assertEquals("n", Compass.NORTH.toString());
        assertEquals("u", Compass.UNKNOWN.toString());
    }
}