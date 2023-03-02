package hu.deik.online_chess.model;

import hu.deik.online_chess.model.Draw.DrawTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNotationConverter {
    ChessParty chessParty;
    @BeforeEach
    void init() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        chessParty = new ChessParty(player1,player2);
    }

    @Test
    void Test() {
        Table table = chessParty.getTable();

        Position[] positions = {Position.toPosition("G1"), Position.toPosition("F3")};
        String ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        String notation = "Nf3";
        Position[] ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions,ConvertedPositions);
        table.makeMove(notation);


        positions = new Position[]{Position.toPosition("G8"), Position.toPosition("F6")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Nf6";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions,ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C2"), Position.toPosition("C4")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "c4";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions,ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("G7"), Position.toPosition("G6")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "g6";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions,ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("B1"), Position.toPosition("C3")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Nc3";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F8"), Position.toPosition("G7")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Bg7";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D2"), Position.toPosition("D4")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "d4";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E8"), Position.toPosition("G8")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "O-O";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C1"), Position.toPosition("F4")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Bf4";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D7"), Position.toPosition("D5")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "d5";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D1"), Position.toPosition("B3")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Qb3";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D5"), Position.toPosition("C4")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "dxc4";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("B3"), Position.toPosition("C4")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Qxc4";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C7"), Position.toPosition("C6")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "c6";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E2"), Position.toPosition("E4")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "e4";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("B8"), Position.toPosition("D7")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Nbd7";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("A1"), Position.toPosition("D1")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Rd1";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D7"), Position.toPosition("B6")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Nb6";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C4"), Position.toPosition("C5")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Qc5";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C8"), Position.toPosition("G4")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Bg4";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F4"), Position.toPosition("G5")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Bg5";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("B6"), Position.toPosition("A4")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Na4";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C5"), Position.toPosition("A3")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Qa3";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("A4"), Position.toPosition("C3")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Nxc3";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("B2"), Position.toPosition("C3")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "bxc3";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F6"), Position.toPosition("E4")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Nxe4";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("G5"), Position.toPosition("E7")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Bxe7";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D8"), Position.toPosition("B6")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Qb6";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F1"), Position.toPosition("C4")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Bc4";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E4"), Position.toPosition("C3")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Nxc3";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E7"), Position.toPosition("C5")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Bc5";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F8"), Position.toPosition("E8")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Rfe8+";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E1"), Position.toPosition("F1")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Kf1";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("G4"), Position.toPosition("E6")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Be6";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C5"), Position.toPosition("B6")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Bxb6";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E6"), Position.toPosition("C4")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Bxc4+";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F1"), Position.toPosition("G1")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Kg1";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C3"), Position.toPosition("E2")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Ne2+";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("G1"), Position.toPosition("F1")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Kf1";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E2"), Position.toPosition("D4")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Nxd4+";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F1"), Position.toPosition("G1")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Kg1";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D4"), Position.toPosition("E2")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Ne2+";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("G1"), Position.toPosition("F1")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Kf1";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E2"), Position.toPosition("C3")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Nc3+";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F1"), Position.toPosition("G1")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Kg1";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("A7"), Position.toPosition("B6")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "axb6";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("A3"), Position.toPosition("B4")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Qb4";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("A8"), Position.toPosition("A4")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Ra4";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("B4"), Position.toPosition("B6")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Qxb6";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C3"), Position.toPosition("D1")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Nxd1";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("H2"), Position.toPosition("H3")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "h3";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("A4"), Position.toPosition("A2")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Rxa2";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("G1"), Position.toPosition("H2")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Kh2";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D1"), Position.toPosition("F2")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Nxf2";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("H1"), Position.toPosition("E1")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Re1";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E8"), Position.toPosition("E1")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Rxe1";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("B6"), Position.toPosition("D8")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Qd8+";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("G7"), Position.toPosition("F8")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Bf8";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F3"), Position.toPosition("E1")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Nxe1";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C4"), Position.toPosition("D5")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Bd5";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E1"), Position.toPosition("F3")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Nf3";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F2"), Position.toPosition("E4")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Ne4";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D8"), Position.toPosition("B8")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Qb8";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("B7"), Position.toPosition("B5")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "b5";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("H3"), Position.toPosition("H4")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "h4";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("H7"), Position.toPosition("H5")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "h5";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F3"), Position.toPosition("E5")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Ne5";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("G8"), Position.toPosition("G7")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Kg7";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("H2"), Position.toPosition("G1")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Kg1";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F8"), Position.toPosition("C5")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Bc5+";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("G1"), Position.toPosition("F1")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Kf1";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E4"), Position.toPosition("G3")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Ng3+";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F1"), Position.toPosition("E1")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Ke1";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C5"), Position.toPosition("B4")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Bb4+";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E1"), Position.toPosition("D1")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Kd1";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D5"), Position.toPosition("B3")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Bb3+";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D1"), Position.toPosition("C1")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Kc1";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("G3"), Position.toPosition("E2")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Ne2+";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C1"), Position.toPosition("B1")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Kb1";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E2"), Position.toPosition("C3")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Nc3+";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("B1"), Position.toPosition("C1")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Kc1";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("A2"), Position.toPosition("C2")};
        ConvertedNotation = table.positionsToNotation(positions[0], positions[1]);
        notation = "Rc2#";
        ConvertedPositions = table.notationToPositions(notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

    }
}
