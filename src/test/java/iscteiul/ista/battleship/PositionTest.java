package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    @DisplayName("Construtor: Verifica se Linha e Coluna ficam guardadas")
    void testConstructor() {
        // Arrange
        int linha = 5;
        int coluna = 10;

        // Act
        Position p = new Position(linha, coluna);

        // Assert
        assertEquals(linha, p.getRow(), "A linha (row) deve ser 5");
        assertEquals(coluna, p.getColumn(), "A coluna (column) deve ser 10");

        // Verifica o estado inicial (não ocupado, não atingido)
        assertFalse(p.isOccupied(), "Inicialmente não deve estar ocupado");
        assertFalse(p.isHit(), "Inicialmente não deve ter sido atingido");
    }

    @Test
    @DisplayName("Estado: Verifica methods occupy() e shoot()")
    void testStateChanges() {
        Position p = new Position(1, 1);

        // Testar Ocupação
        p.occupy();
        assertTrue(p.isOccupied(), "Deve retornar true após chamar occupy()");

        // Testar Tiro
        p.shoot();
        assertTrue(p.isHit(), "Deve retornar true após chamar shoot()");
    }

    @Test
    @DisplayName("Equals: Posições com mesmas coordenadas são iguais")
    void testEquals() {
        Position p1 = new Position(3, 3);
        Position p2 = new Position(3, 3);
        Position p3 = new Position(4, 4);

        assertEquals(p1, p2, "Posições (3,3) e (3,3) devem ser iguais");
        assertNotEquals(p1, p3, "Posições (3,3) e (4,4) devem ser diferentes");
    }

    @Test
    @DisplayName("Adjacência: Verifica se detecta vizinhos corretamente")
    void testAdjacency() {
        Position centro = new Position(5, 5);

        Position vizinhoLado = new Position(5, 6);
        Position vizinhoDiagonal = new Position(4, 4); // O código Math.abs <= 1 permite diagonais
        Position longe = new Position(1, 1);

        assertTrue(centro.isAdjacentTo(vizinhoLado), "(5,6) deve ser adjacente a (5,5)");
        assertTrue(centro.isAdjacentTo(vizinhoDiagonal), "(4,4) deve ser adjacente a (5,5)");
        assertFalse(centro.isAdjacentTo(longe), "(1,1) NÃO deve ser adjacente a (5,5)");
    }

    @Test
    @DisplayName("ToString: Verifica o formato do texto")
    void testToString() {
        Position p = new Position(2, 8);
        String textoEsperado = "Linha = 2 Coluna = 8";

        assertEquals(textoEsperado, p.toString(), "O toString deve seguir o formato exato");
    }
}