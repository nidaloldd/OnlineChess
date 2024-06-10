package java.hu.deik.online_chess.model;

import hu.deik.online_chess.data.ChessParty;
import hu.deik.online_chess.model.NotationConverter;
import hu.deik.online_chess.model.Position;
import hu.deik.online_chess.model.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNotationConverter {
    ChessParty chessParty;
    Table table;
    @BeforeEach
    void init() {
        chessParty = new ChessParty();
        table = chessParty.getTable();
    }

    @Test
    void Test() {

        Position[] positions = {Position.toPosition("G1"), Position.toPosition("F3")};
        String ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        String notation = "Nf3";
        Position[] ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions,ConvertedPositions);
        table.makeMove(notation);


        positions = new Position[]{Position.toPosition("G8"), Position.toPosition("F6")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Nf6";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions,ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C2"), Position.toPosition("C4")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "c4";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions,ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("G7"), Position.toPosition("G6")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "g6";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions,ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("B1"), Position.toPosition("C3")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Nc3";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F8"), Position.toPosition("G7")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Bg7";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D2"), Position.toPosition("D4")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "d4";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E8"), Position.toPosition("G8")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "O-O";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C1"), Position.toPosition("F4")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Bf4";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D7"), Position.toPosition("D5")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "d5";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D1"), Position.toPosition("B3")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Qb3";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D5"), Position.toPosition("C4")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "dxc4";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("B3"), Position.toPosition("C4")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Qxc4";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C7"), Position.toPosition("C6")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "c6";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E2"), Position.toPosition("E4")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "e4";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("B8"), Position.toPosition("D7")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Nbd7";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("A1"), Position.toPosition("D1")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Rd1";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D7"), Position.toPosition("B6")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Nb6";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C4"), Position.toPosition("C5")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Qc5";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C8"), Position.toPosition("G4")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Bg4";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F4"), Position.toPosition("G5")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Bg5";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("B6"), Position.toPosition("A4")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Na4";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C5"), Position.toPosition("A3")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Qa3";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("A4"), Position.toPosition("C3")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Nxc3";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("B2"), Position.toPosition("C3")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "bxc3";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F6"), Position.toPosition("E4")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Nxe4";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("G5"), Position.toPosition("E7")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Bxe7";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D8"), Position.toPosition("B6")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Qb6";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F1"), Position.toPosition("C4")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Bc4";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E4"), Position.toPosition("C3")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Nxc3";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E7"), Position.toPosition("C5")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Bc5";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F8"), Position.toPosition("E8")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Rfe8+";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E1"), Position.toPosition("F1")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Kf1";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("G4"), Position.toPosition("E6")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Be6";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C5"), Position.toPosition("B6")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Bxb6";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E6"), Position.toPosition("C4")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Bxc4+";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F1"), Position.toPosition("G1")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Kg1";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C3"), Position.toPosition("E2")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Ne2+";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("G1"), Position.toPosition("F1")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Kf1";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E2"), Position.toPosition("D4")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Nxd4+";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F1"), Position.toPosition("G1")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Kg1";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D4"), Position.toPosition("E2")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Ne2+";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("G1"), Position.toPosition("F1")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Kf1";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E2"), Position.toPosition("C3")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Nc3+";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F1"), Position.toPosition("G1")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Kg1";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("A7"), Position.toPosition("B6")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "axb6";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("A3"), Position.toPosition("B4")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Qb4";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("A8"), Position.toPosition("A4")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Ra4";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("B4"), Position.toPosition("B6")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Qxb6";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C3"), Position.toPosition("D1")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Nxd1";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("H2"), Position.toPosition("H3")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "h3";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("A4"), Position.toPosition("A2")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Rxa2";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("G1"), Position.toPosition("H2")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Kh2";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D1"), Position.toPosition("F2")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Nxf2";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("H1"), Position.toPosition("E1")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Re1";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E8"), Position.toPosition("E1")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Rxe1";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("B6"), Position.toPosition("D8")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Qd8+";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("G7"), Position.toPosition("F8")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Bf8";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F3"), Position.toPosition("E1")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Nxe1";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C4"), Position.toPosition("D5")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Bd5";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E1"), Position.toPosition("F3")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Nf3";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F2"), Position.toPosition("E4")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Ne4";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D8"), Position.toPosition("B8")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Qb8";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("B7"), Position.toPosition("B5")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "b5";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("H3"), Position.toPosition("H4")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "h4";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("H7"), Position.toPosition("H5")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "h5";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F3"), Position.toPosition("E5")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Ne5";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("G8"), Position.toPosition("G7")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Kg7";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("H2"), Position.toPosition("G1")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Kg1";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F8"), Position.toPosition("C5")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Bc5+";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("G1"), Position.toPosition("F1")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Kf1";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E4"), Position.toPosition("G3")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Ng3+";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("F1"), Position.toPosition("E1")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Ke1";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C5"), Position.toPosition("B4")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Bb4+";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E1"), Position.toPosition("D1")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Kd1";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D5"), Position.toPosition("B3")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Bb3+";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("D1"), Position.toPosition("C1")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Kc1";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("G3"), Position.toPosition("E2")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Ne2+";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("C1"), Position.toPosition("B1")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Kb1";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("E2"), Position.toPosition("C3")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Nc3+";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("B1"), Position.toPosition("C1")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Kc1";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

        positions = new Position[]{Position.toPosition("A2"), Position.toPosition("C2")};
        ConvertedNotation = NotationConverter.positionsToNotation(table,positions[0], positions[1]);
        notation = "Rc2#";
        ConvertedPositions = NotationConverter.notationToPositions(table,notation);

        assertEquals(notation, ConvertedNotation);
        assertArrayEquals(positions, ConvertedPositions);
        table.makeMove(notation);

    }
}
