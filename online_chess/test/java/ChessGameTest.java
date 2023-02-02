import model.ChessParty;
import model.Draw.DrawTable;
import model.Player;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ChessGameTest {
    ChessParty chessParty;

    @BeforeEach
    void init() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        chessParty = new ChessParty(player1,player2);
    }

    @Test
    void TestChessGame(){

        chessParty.getTable().makeMove(Position.toPosition("G1"),Position.toPosition("F3"));
        assertEquals("""
                         BR  BN  BB  BQ  BK  BB  BN  BR\s
                         BP  BP  BP  BP  BP  BP  BP  BP\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  WN  00  00\s
                         WP  WP  WP  WP  WP  WP  WP  WP\s
                         WR  WN  WB  WQ  WK  WB  00  WR\s
                        """
                , DrawTable.makeTableToString(chessParty.getTable())
        );

        chessParty.getTable().makeMove(Position.toPosition("G8"),Position.toPosition("F6"));
        assertEquals("""
                         BR  BN  BB  BQ  BK  BB  00  BR\s
                         BP  BP  BP  BP  BP  BP  BP  BP\s
                         00  00  00  00  00  BN  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  WN  00  00\s
                         WP  WP  WP  WP  WP  WP  WP  WP\s
                         WR  WN  WB  WQ  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );

        chessParty.getTable().makeMove(Position.toPosition("C2"),Position.toPosition("C4"));
        assertEquals("""
                         BR  BN  BB  BQ  BK  BB  00  BR\s
                         BP  BP  BP  BP  BP  BP  BP  BP\s
                         00  00  00  00  00  BN  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WP  00  00  00  00  00\s
                         00  00  00  00  00  WN  00  00\s
                         WP  WP  00  WP  WP  WP  WP  WP\s
                         WR  WN  WB  WQ  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );

        chessParty.getTable().makeMove(Position.toPosition("G7"),Position.toPosition("G6"));
        assertEquals("""
                         BR  BN  BB  BQ  BK  BB  00  BR\s
                         BP  BP  BP  BP  BP  BP  00  BP\s
                         00  00  00  00  00  BN  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WP  00  00  00  00  00\s
                         00  00  00  00  00  WN  00  00\s
                         WP  WP  00  WP  WP  WP  WP  WP\s
                         WR  WN  WB  WQ  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );

        chessParty.getTable().makeMove(Position.toPosition("B1"),Position.toPosition("C3"));
        assertEquals("""
                         BR  BN  BB  BQ  BK  BB  00  BR\s
                         BP  BP  BP  BP  BP  BP  00  BP\s
                         00  00  00  00  00  BN  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WP  00  00  00  00  00\s
                         00  00  WN  00  00  WN  00  00\s
                         WP  WP  00  WP  WP  WP  WP  WP\s
                         WR  00  WB  WQ  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );

        chessParty.getTable().makeMove(Position.toPosition("F8"),Position.toPosition("G7"));
        assertEquals("""
                         BR  BN  BB  BQ  BK  00  00  BR\s
                         BP  BP  BP  BP  BP  BP  BB  BP\s
                         00  00  00  00  00  BN  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WP  00  00  00  00  00\s
                         00  00  WN  00  00  WN  00  00\s
                         WP  WP  00  WP  WP  WP  WP  WP\s
                         WR  00  WB  WQ  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );

        chessParty.getTable().makeMove(Position.toPosition("D2"),Position.toPosition("D4"));
        assertEquals("""
                         BR  BN  BB  BQ  BK  00  00  BR\s
                         BP  BP  BP  BP  BP  BP  BB  BP\s
                         00  00  00  00  00  BN  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WP  WP  00  00  00  00\s
                         00  00  WN  00  00  WN  00  00\s
                         WP  WP  00  00  WP  WP  WP  WP\s
                         WR  00  WB  WQ  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("E8"),Position.toPosition("G8"));
        assertEquals("""
                         BR  BN  BB  BQ  00  BR  BK  00\s
                         BP  BP  BP  BP  BP  BP  BB  BP\s
                         00  00  00  00  00  BN  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WP  WP  00  00  00  00\s
                         00  00  WN  00  00  WN  00  00\s
                         WP  WP  00  00  WP  WP  WP  WP\s
                         WR  00  WB  WQ  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("C1"),Position.toPosition("F4"));
        assertEquals("""
                         BR  BN  BB  BQ  00  BR  BK  00\s
                         BP  BP  BP  BP  BP  BP  BB  BP\s
                         00  00  00  00  00  BN  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WP  WP  00  WB  00  00\s
                         00  00  WN  00  00  WN  00  00\s
                         WP  WP  00  00  WP  WP  WP  WP\s
                         WR  00  00  WQ  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("D7"),Position.toPosition("D5"));
        assertEquals("""
                         BR  BN  BB  BQ  00  BR  BK  00\s
                         BP  BP  BP  00  BP  BP  BB  BP\s
                         00  00  00  00  00  BN  BP  00\s
                         00  00  00  BP  00  00  00  00\s
                         00  00  WP  WP  00  WB  00  00\s
                         00  00  WN  00  00  WN  00  00\s
                         WP  WP  00  00  WP  WP  WP  WP\s
                         WR  00  00  WQ  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("D1"),Position.toPosition("B3"));
        assertEquals("""
                         BR  BN  BB  BQ  00  BR  BK  00\s
                         BP  BP  BP  00  BP  BP  BB  BP\s
                         00  00  00  00  00  BN  BP  00\s
                         00  00  00  BP  00  00  00  00\s
                         00  00  WP  WP  00  WB  00  00\s
                         00  WQ  WN  00  00  WN  00  00\s
                         WP  WP  00  00  WP  WP  WP  WP\s
                         WR  00  00  00  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("D5"),Position.toPosition("C4"));
        assertEquals("""
                         BR  BN  BB  BQ  00  BR  BK  00\s
                         BP  BP  BP  00  BP  BP  BB  BP\s
                         00  00  00  00  00  BN  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  BP  WP  00  WB  00  00\s
                         00  WQ  WN  00  00  WN  00  00\s
                         WP  WP  00  00  WP  WP  WP  WP\s
                         WR  00  00  00  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("B3"),Position.toPosition("C4"));
        assertEquals("""
                         BR  BN  BB  BQ  00  BR  BK  00\s
                         BP  BP  BP  00  BP  BP  BB  BP\s
                         00  00  00  00  00  BN  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WQ  WP  00  WB  00  00\s
                         00  00  WN  00  00  WN  00  00\s
                         WP  WP  00  00  WP  WP  WP  WP\s
                         WR  00  00  00  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("C7"),Position.toPosition("C6"));
        assertEquals("""
                         BR  BN  BB  BQ  00  BR  BK  00\s
                         BP  BP  00  00  BP  BP  BB  BP\s
                         00  00  BP  00  00  BN  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WQ  WP  00  WB  00  00\s
                         00  00  WN  00  00  WN  00  00\s
                         WP  WP  00  00  WP  WP  WP  WP\s
                         WR  00  00  00  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("E2"),Position.toPosition("E4"));
        assertEquals("""
                         BR  BN  BB  BQ  00  BR  BK  00\s
                         BP  BP  00  00  BP  BP  BB  BP\s
                         00  00  BP  00  00  BN  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WQ  WP  WP  WB  00  00\s
                         00  00  WN  00  00  WN  00  00\s
                         WP  WP  00  00  00  WP  WP  WP\s
                         WR  00  00  00  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("B8"),Position.toPosition("D7"));
        assertEquals("""
                         BR  00  BB  BQ  00  BR  BK  00\s
                         BP  BP  00  BN  BP  BP  BB  BP\s
                         00  00  BP  00  00  BN  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WQ  WP  WP  WB  00  00\s
                         00  00  WN  00  00  WN  00  00\s
                         WP  WP  00  00  00  WP  WP  WP\s
                         WR  00  00  00  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("A1"),Position.toPosition("D1"));
        assertEquals("""
                         BR  00  BB  BQ  00  BR  BK  00\s
                         BP  BP  00  BN  BP  BP  BB  BP\s
                         00  00  BP  00  00  BN  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WQ  WP  WP  WB  00  00\s
                         00  00  WN  00  00  WN  00  00\s
                         WP  WP  00  00  00  WP  WP  WP\s
                         00  00  00  WR  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("D7"),Position.toPosition("B6"));
        assertEquals("""
                         BR  00  BB  BQ  00  BR  BK  00\s
                         BP  BP  00  00  BP  BP  BB  BP\s
                         00  BN  BP  00  00  BN  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WQ  WP  WP  WB  00  00\s
                         00  00  WN  00  00  WN  00  00\s
                         WP  WP  00  00  00  WP  WP  WP\s
                         00  00  00  WR  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("C4"),Position.toPosition("C5"));
        assertEquals("""
                         BR  00  BB  BQ  00  BR  BK  00\s
                         BP  BP  00  00  BP  BP  BB  BP\s
                         00  BN  BP  00  00  BN  BP  00\s
                         00  00  WQ  00  00  00  00  00\s
                         00  00  00  WP  WP  WB  00  00\s
                         00  00  WN  00  00  WN  00  00\s
                         WP  WP  00  00  00  WP  WP  WP\s
                         00  00  00  WR  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("C8"),Position.toPosition("G4"));
        assertEquals("""
                         BR  00  00  BQ  00  BR  BK  00\s
                         BP  BP  00  00  BP  BP  BB  BP\s
                         00  BN  BP  00  00  BN  BP  00\s
                         00  00  WQ  00  00  00  00  00\s
                         00  00  00  WP  WP  WB  BB  00\s
                         00  00  WN  00  00  WN  00  00\s
                         WP  WP  00  00  00  WP  WP  WP\s
                         00  00  00  WR  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("F4"),Position.toPosition("G5"));
        assertEquals("""
                         BR  00  00  BQ  00  BR  BK  00\s
                         BP  BP  00  00  BP  BP  BB  BP\s
                         00  BN  BP  00  00  BN  BP  00\s
                         00  00  WQ  00  00  00  WB  00\s
                         00  00  00  WP  WP  00  BB  00\s
                         00  00  WN  00  00  WN  00  00\s
                         WP  WP  00  00  00  WP  WP  WP\s
                         00  00  00  WR  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("B6"),Position.toPosition("A4"));
        assertEquals("""
                         BR  00  00  BQ  00  BR  BK  00\s
                         BP  BP  00  00  BP  BP  BB  BP\s
                         00  00  BP  00  00  BN  BP  00\s
                         00  00  WQ  00  00  00  WB  00\s
                         BN  00  00  WP  WP  00  BB  00\s
                         00  00  WN  00  00  WN  00  00\s
                         WP  WP  00  00  00  WP  WP  WP\s
                         00  00  00  WR  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("C5"),Position.toPosition("A3"));
        assertEquals("""
                         BR  00  00  BQ  00  BR  BK  00\s
                         BP  BP  00  00  BP  BP  BB  BP\s
                         00  00  BP  00  00  BN  BP  00\s
                         00  00  00  00  00  00  WB  00\s
                         BN  00  00  WP  WP  00  BB  00\s
                         WQ  00  WN  00  00  WN  00  00\s
                         WP  WP  00  00  00  WP  WP  WP\s
                         00  00  00  WR  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("A4"),Position.toPosition("C3"));
        assertEquals("""
                         BR  00  00  BQ  00  BR  BK  00\s
                         BP  BP  00  00  BP  BP  BB  BP\s
                         00  00  BP  00  00  BN  BP  00\s
                         00  00  00  00  00  00  WB  00\s
                         00  00  00  WP  WP  00  BB  00\s
                         WQ  00  BN  00  00  WN  00  00\s
                         WP  WP  00  00  00  WP  WP  WP\s
                         00  00  00  WR  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("B2"),Position.toPosition("C3"));
        assertEquals("""
                         BR  00  00  BQ  00  BR  BK  00\s
                         BP  BP  00  00  BP  BP  BB  BP\s
                         00  00  BP  00  00  BN  BP  00\s
                         00  00  00  00  00  00  WB  00\s
                         00  00  00  WP  WP  00  BB  00\s
                         WQ  00  WP  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("F6"),Position.toPosition("E4"));
        assertEquals("""
                         BR  00  00  BQ  00  BR  BK  00\s
                         BP  BP  00  00  BP  BP  BB  BP\s
                         00  00  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  WB  00\s
                         00  00  00  WP  BN  00  BB  00\s
                         WQ  00  WP  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("G5"),Position.toPosition("E7"));
        assertEquals("""
                         BR  00  00  BQ  00  BR  BK  00\s
                         BP  BP  00  00  WB  BP  BB  BP\s
                         00  00  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  WP  BN  00  BB  00\s
                         WQ  00  WP  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("D8"),Position.toPosition("B6"));
        assertEquals("""
                         BR  00  00  00  00  BR  BK  00\s
                         BP  BP  00  00  WB  BP  BB  BP\s
                         00  BQ  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  WP  BN  00  BB  00\s
                         WQ  00  WP  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  WK  WB  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("F1"),Position.toPosition("C4"));
        assertEquals("""
                         BR  00  00  00  00  BR  BK  00\s
                         BP  BP  00  00  WB  BP  BB  BP\s
                         00  BQ  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WB  WP  BN  00  BB  00\s
                         WQ  00  WP  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  WK  00  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("E4"),Position.toPosition("C3"));
        assertEquals("""
                         BR  00  00  00  00  BR  BK  00\s
                         BP  BP  00  00  WB  BP  BB  BP\s
                         00  BQ  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WB  WP  00  00  BB  00\s
                         WQ  00  BN  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  WK  00  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("E7"),Position.toPosition("C5"));
        assertEquals("""
                         BR  00  00  00  00  BR  BK  00\s
                         BP  BP  00  00  00  BP  BB  BP\s
                         00  BQ  BP  00  00  00  BP  00\s
                         00  00  WB  00  00  00  00  00\s
                         00  00  WB  WP  00  00  BB  00\s
                         WQ  00  BN  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  WK  00  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("F8"),Position.toPosition("E8"));
        assertEquals("""
                         BR  00  00  00  BR  00  BK  00\s
                         BP  BP  00  00  00  BP  BB  BP\s
                         00  BQ  BP  00  00  00  BP  00\s
                         00  00  WB  00  00  00  00  00\s
                         00  00  WB  WP  00  00  BB  00\s
                         WQ  00  BN  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  WK  00  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("E1"),Position.toPosition("F1"));
        assertEquals("""
                         BR  00  00  00  BR  00  BK  00\s
                         BP  BP  00  00  00  BP  BB  BP\s
                         00  BQ  BP  00  00  00  BP  00\s
                         00  00  WB  00  00  00  00  00\s
                         00  00  WB  WP  00  00  BB  00\s
                         WQ  00  BN  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  00  WK  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("G4"),Position.toPosition("E6"));
        assertEquals("""
                         BR  00  00  00  BR  00  BK  00\s
                         BP  BP  00  00  00  BP  BB  BP\s
                         00  BQ  BP  00  BB  00  BP  00\s
                         00  00  WB  00  00  00  00  00\s
                         00  00  WB  WP  00  00  00  00\s
                         WQ  00  BN  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  00  WK  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("C5"),Position.toPosition("B6"));
        assertEquals("""
                         BR  00  00  00  BR  00  BK  00\s
                         BP  BP  00  00  00  BP  BB  BP\s
                         00  WB  BP  00  BB  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  WB  WP  00  00  00  00\s
                         WQ  00  BN  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  00  WK  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("E6"),Position.toPosition("C4"));
        assertEquals("""
                         BR  00  00  00  BR  00  BK  00\s
                         BP  BP  00  00  00  BP  BB  BP\s
                         00  WB  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  BB  WP  00  00  00  00\s
                         WQ  00  BN  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  00  WK  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("F1"),Position.toPosition("G1"));
        assertEquals("""
                         BR  00  00  00  BR  00  BK  00\s
                         BP  BP  00  00  00  BP  BB  BP\s
                         00  WB  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  BB  WP  00  00  00  00\s
                         WQ  00  BN  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  00  00  WK  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("C3"),Position.toPosition("E2"));
        assertEquals("""
                         BR  00  00  00  BR  00  BK  00\s
                         BP  BP  00  00  00  BP  BB  BP\s
                         00  WB  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  BB  WP  00  00  00  00\s
                         WQ  00  00  00  00  WN  00  00\s
                         WP  00  00  00  BN  WP  WP  WP\s
                         00  00  00  WR  00  00  WK  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("G1"),Position.toPosition("F1"));
        assertEquals("""
                         BR  00  00  00  BR  00  BK  00\s
                         BP  BP  00  00  00  BP  BB  BP\s
                         00  WB  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  BB  WP  00  00  00  00\s
                         WQ  00  00  00  00  WN  00  00\s
                         WP  00  00  00  BN  WP  WP  WP\s
                         00  00  00  WR  00  WK  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );

        chessParty.getTable().makeMove(Position.toPosition("E2"),Position.toPosition("D4"));
        assertEquals("""
                         BR  00  00  00  BR  00  BK  00\s
                         BP  BP  00  00  00  BP  BB  BP\s
                         00  WB  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  BB  BN  00  00  00  00\s
                         WQ  00  00  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  00  WK  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("F1"),Position.toPosition("G1"));
        assertEquals("""
                         BR  00  00  00  BR  00  BK  00\s
                         BP  BP  00  00  00  BP  BB  BP\s
                         00  WB  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  BB  BN  00  00  00  00\s
                         WQ  00  00  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  00  00  WK  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("D4"),Position.toPosition("E2"));
        assertEquals("""
                         BR  00  00  00  BR  00  BK  00\s
                         BP  BP  00  00  00  BP  BB  BP\s
                         00  WB  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  BB  00  00  00  00  00\s
                         WQ  00  00  00  00  WN  00  00\s
                         WP  00  00  00  BN  WP  WP  WP\s
                         00  00  00  WR  00  00  WK  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("G1"),Position.toPosition("F1"));
        assertEquals("""
                         BR  00  00  00  BR  00  BK  00\s
                         BP  BP  00  00  00  BP  BB  BP\s
                         00  WB  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  BB  00  00  00  00  00\s
                         WQ  00  00  00  00  WN  00  00\s
                         WP  00  00  00  BN  WP  WP  WP\s
                         00  00  00  WR  00  WK  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("E2"),Position.toPosition("C3"));
        assertEquals("""
                         BR  00  00  00  BR  00  BK  00\s
                         BP  BP  00  00  00  BP  BB  BP\s
                         00  WB  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  BB  00  00  00  00  00\s
                         WQ  00  BN  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  00  WK  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("F1"),Position.toPosition("G1"));
        assertEquals("""
                         BR  00  00  00  BR  00  BK  00\s
                         BP  BP  00  00  00  BP  BB  BP\s
                         00  WB  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  BB  00  00  00  00  00\s
                         WQ  00  BN  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  00  00  WK  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("A7"),Position.toPosition("B6"));
        assertEquals("""
                         BR  00  00  00  BR  00  BK  00\s
                         00  BP  00  00  00  BP  BB  BP\s
                         00  BP  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  BB  00  00  00  00  00\s
                         WQ  00  BN  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  00  00  WK  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("A3"),Position.toPosition("B4"));
        assertEquals("""
                         BR  00  00  00  BR  00  BK  00\s
                         00  BP  00  00  00  BP  BB  BP\s
                         00  BP  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  WQ  BB  00  00  00  00  00\s
                         00  00  BN  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  00  00  WK  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("A8"),Position.toPosition("A4"));
        assertEquals("""
                         00  00  00  00  BR  00  BK  00\s
                         00  BP  00  00  00  BP  BB  BP\s
                         00  BP  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         BR  WQ  BB  00  00  00  00  00\s
                         00  00  BN  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  00  00  WK  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("B4"),Position.toPosition("B6"));
        assertEquals("""
                         00  00  00  00  BR  00  BK  00\s
                         00  BP  00  00  00  BP  BB  BP\s
                         00  WQ  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         BR  00  BB  00  00  00  00  00\s
                         00  00  BN  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  WR  00  00  WK  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("C3"),Position.toPosition("D1"));
        assertEquals("""
                         00  00  00  00  BR  00  BK  00\s
                         00  BP  00  00  00  BP  BB  BP\s
                         00  WQ  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         BR  00  BB  00  00  00  00  00\s
                         00  00  00  00  00  WN  00  00\s
                         WP  00  00  00  00  WP  WP  WP\s
                         00  00  00  BN  00  00  WK  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("H2"),Position.toPosition("H3"));
        assertEquals("""
                         00  00  00  00  BR  00  BK  00\s
                         00  BP  00  00  00  BP  BB  BP\s
                         00  WQ  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         BR  00  BB  00  00  00  00  00\s
                         00  00  00  00  00  WN  00  WP\s
                         WP  00  00  00  00  WP  WP  00\s
                         00  00  00  BN  00  00  WK  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("A4"),Position.toPosition("A2"));
        assertEquals("""
                         00  00  00  00  BR  00  BK  00\s
                         00  BP  00  00  00  BP  BB  BP\s
                         00  WQ  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  BB  00  00  00  00  00\s
                         00  00  00  00  00  WN  00  WP\s
                         BR  00  00  00  00  WP  WP  00\s
                         00  00  00  BN  00  00  WK  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("G1"),Position.toPosition("H2"));
        assertEquals("""
                         00  00  00  00  BR  00  BK  00\s
                         00  BP  00  00  00  BP  BB  BP\s
                         00  WQ  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  BB  00  00  00  00  00\s
                         00  00  00  00  00  WN  00  WP\s
                         BR  00  00  00  00  WP  WP  WK\s
                         00  00  00  BN  00  00  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("D1"),Position.toPosition("F2"));
        assertEquals("""
                         00  00  00  00  BR  00  BK  00\s
                         00  BP  00  00  00  BP  BB  BP\s
                         00  WQ  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  BB  00  00  00  00  00\s
                         00  00  00  00  00  WN  00  WP\s
                         BR  00  00  00  00  BN  WP  WK\s
                         00  00  00  00  00  00  00  WR\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("H1"),Position.toPosition("E1"));
        assertEquals("""
                         00  00  00  00  BR  00  BK  00\s
                         00  BP  00  00  00  BP  BB  BP\s
                         00  WQ  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  BB  00  00  00  00  00\s
                         00  00  00  00  00  WN  00  WP\s
                         BR  00  00  00  00  BN  WP  WK\s
                         00  00  00  00  WR  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("E8"),Position.toPosition("E1"));
        assertEquals("""
                         00  00  00  00  00  00  BK  00\s
                         00  BP  00  00  00  BP  BB  BP\s
                         00  WQ  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  BB  00  00  00  00  00\s
                         00  00  00  00  00  WN  00  WP\s
                         BR  00  00  00  00  BN  WP  WK\s
                         00  00  00  00  BR  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("B6"),Position.toPosition("D8"));
        assertEquals("""
                         00  00  00  WQ  00  00  BK  00\s
                         00  BP  00  00  00  BP  BB  BP\s
                         00  00  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  BB  00  00  00  00  00\s
                         00  00  00  00  00  WN  00  WP\s
                         BR  00  00  00  00  BN  WP  WK\s
                         00  00  00  00  BR  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("G7"),Position.toPosition("F8"));
        assertEquals("""
                         00  00  00  WQ  00  BB  BK  00\s
                         00  BP  00  00  00  BP  00  BP\s
                         00  00  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  BB  00  00  00  00  00\s
                         00  00  00  00  00  WN  00  WP\s
                         BR  00  00  00  00  BN  WP  WK\s
                         00  00  00  00  BR  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("F3"),Position.toPosition("E1"));
        assertEquals("""
                         00  00  00  WQ  00  BB  BK  00\s
                         00  BP  00  00  00  BP  00  BP\s
                         00  00  BP  00  00  00  BP  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  BB  00  00  00  00  00\s
                         00  00  00  00  00  00  00  WP\s
                         BR  00  00  00  00  BN  WP  WK\s
                         00  00  00  00  WN  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("C4"),Position.toPosition("D5"));
        assertEquals("""
                         00  00  00  WQ  00  BB  BK  00\s
                         00  BP  00  00  00  BP  00  BP\s
                         00  00  BP  00  00  00  BP  00\s
                         00  00  00  BB  00  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  00  00  WP\s
                         BR  00  00  00  00  BN  WP  WK\s
                         00  00  00  00  WN  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("E1"),Position.toPosition("F3"));
        assertEquals("""
                         00  00  00  WQ  00  BB  BK  00\s
                         00  BP  00  00  00  BP  00  BP\s
                         00  00  BP  00  00  00  BP  00\s
                         00  00  00  BB  00  00  00  00\s
                         00  00  00  00  00  00  00  00\s
                         00  00  00  00  00  WN  00  WP\s
                         BR  00  00  00  00  BN  WP  WK\s
                         00  00  00  00  00  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("F2"),Position.toPosition("E4"));
        assertEquals("""
                         00  00  00  WQ  00  BB  BK  00\s
                         00  BP  00  00  00  BP  00  BP\s
                         00  00  BP  00  00  00  BP  00\s
                         00  00  00  BB  00  00  00  00\s
                         00  00  00  00  BN  00  00  00\s
                         00  00  00  00  00  WN  00  WP\s
                         BR  00  00  00  00  00  WP  WK\s
                         00  00  00  00  00  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("D8"),Position.toPosition("B8"));
        assertEquals("""
                         00  WQ  00  00  00  BB  BK  00\s
                         00  BP  00  00  00  BP  00  BP\s
                         00  00  BP  00  00  00  BP  00\s
                         00  00  00  BB  00  00  00  00\s
                         00  00  00  00  BN  00  00  00\s
                         00  00  00  00  00  WN  00  WP\s
                         BR  00  00  00  00  00  WP  WK\s
                         00  00  00  00  00  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("B7"),Position.toPosition("B5"));
        assertEquals("""
                         00  WQ  00  00  00  BB  BK  00\s
                         00  00  00  00  00  BP  00  BP\s
                         00  00  BP  00  00  00  BP  00\s
                         00  BP  00  BB  00  00  00  00\s
                         00  00  00  00  BN  00  00  00\s
                         00  00  00  00  00  WN  00  WP\s
                         BR  00  00  00  00  00  WP  WK\s
                         00  00  00  00  00  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("H3"),Position.toPosition("H4"));
        assertEquals("""
                         00  WQ  00  00  00  BB  BK  00\s
                         00  00  00  00  00  BP  00  BP\s
                         00  00  BP  00  00  00  BP  00\s
                         00  BP  00  BB  00  00  00  00\s
                         00  00  00  00  BN  00  00  WP\s
                         00  00  00  00  00  WN  00  00\s
                         BR  00  00  00  00  00  WP  WK\s
                         00  00  00  00  00  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("H7"),Position.toPosition("H5"));
        assertEquals("""
                         00  WQ  00  00  00  BB  BK  00\s
                         00  00  00  00  00  BP  00  00\s
                         00  00  BP  00  00  00  BP  00\s
                         00  BP  00  BB  00  00  00  BP\s
                         00  00  00  00  BN  00  00  WP\s
                         00  00  00  00  00  WN  00  00\s
                         BR  00  00  00  00  00  WP  WK\s
                         00  00  00  00  00  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("F3"),Position.toPosition("E5"));
        assertEquals("""
                         00  WQ  00  00  00  BB  BK  00\s
                         00  00  00  00  00  BP  00  00\s
                         00  00  BP  00  00  00  BP  00\s
                         00  BP  00  BB  WN  00  00  BP\s
                         00  00  00  00  BN  00  00  WP\s
                         00  00  00  00  00  00  00  00\s
                         BR  00  00  00  00  00  WP  WK\s
                         00  00  00  00  00  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("G8"),Position.toPosition("G7"));
        assertEquals("""
                         00  WQ  00  00  00  BB  00  00\s
                         00  00  00  00  00  BP  BK  00\s
                         00  00  BP  00  00  00  BP  00\s
                         00  BP  00  BB  WN  00  00  BP\s
                         00  00  00  00  BN  00  00  WP\s
                         00  00  00  00  00  00  00  00\s
                         BR  00  00  00  00  00  WP  WK\s
                         00  00  00  00  00  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("H2"),Position.toPosition("G1"));
        assertEquals("""
                         00  WQ  00  00  00  BB  00  00\s
                         00  00  00  00  00  BP  BK  00\s
                         00  00  BP  00  00  00  BP  00\s
                         00  BP  00  BB  WN  00  00  BP\s
                         00  00  00  00  BN  00  00  WP\s
                         00  00  00  00  00  00  00  00\s
                         BR  00  00  00  00  00  WP  00\s
                         00  00  00  00  00  00  WK  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("F8"),Position.toPosition("C5"));
        assertEquals("""
                         00  WQ  00  00  00  00  00  00\s
                         00  00  00  00  00  BP  BK  00\s
                         00  00  BP  00  00  00  BP  00\s
                         00  BP  BB  BB  WN  00  00  BP\s
                         00  00  00  00  BN  00  00  WP\s
                         00  00  00  00  00  00  00  00\s
                         BR  00  00  00  00  00  WP  00\s
                         00  00  00  00  00  00  WK  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("G1"),Position.toPosition("F1"));
        assertEquals("""
                         00  WQ  00  00  00  00  00  00\s
                         00  00  00  00  00  BP  BK  00\s
                         00  00  BP  00  00  00  BP  00\s
                         00  BP  BB  BB  WN  00  00  BP\s
                         00  00  00  00  BN  00  00  WP\s
                         00  00  00  00  00  00  00  00\s
                         BR  00  00  00  00  00  WP  00\s
                         00  00  00  00  00  WK  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("E4"),Position.toPosition("G3"));
        assertEquals("""
                         00  WQ  00  00  00  00  00  00\s
                         00  00  00  00  00  BP  BK  00\s
                         00  00  BP  00  00  00  BP  00\s
                         00  BP  BB  BB  WN  00  00  BP\s
                         00  00  00  00  00  00  00  WP\s
                         00  00  00  00  00  00  BN  00\s
                         BR  00  00  00  00  00  WP  00\s
                         00  00  00  00  00  WK  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("F1"),Position.toPosition("E1"));
        assertEquals("""
                         00  WQ  00  00  00  00  00  00\s
                         00  00  00  00  00  BP  BK  00\s
                         00  00  BP  00  00  00  BP  00\s
                         00  BP  BB  BB  WN  00  00  BP\s
                         00  00  00  00  00  00  00  WP\s
                         00  00  00  00  00  00  BN  00\s
                         BR  00  00  00  00  00  WP  00\s
                         00  00  00  00  WK  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("C5"),Position.toPosition("B4"));
        assertEquals("""
                         00  WQ  00  00  00  00  00  00\s
                         00  00  00  00  00  BP  BK  00\s
                         00  00  BP  00  00  00  BP  00\s
                         00  BP  00  BB  WN  00  00  BP\s
                         00  BB  00  00  00  00  00  WP\s
                         00  00  00  00  00  00  BN  00\s
                         BR  00  00  00  00  00  WP  00\s
                         00  00  00  00  WK  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("E1"),Position.toPosition("D1"));
        assertEquals("""
                         00  WQ  00  00  00  00  00  00\s
                         00  00  00  00  00  BP  BK  00\s
                         00  00  BP  00  00  00  BP  00\s
                         00  BP  00  BB  WN  00  00  BP\s
                         00  BB  00  00  00  00  00  WP\s
                         00  00  00  00  00  00  BN  00\s
                         BR  00  00  00  00  00  WP  00\s
                         00  00  00  WK  00  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("D5"),Position.toPosition("B3"));
        assertEquals("""
                         00  WQ  00  00  00  00  00  00\s
                         00  00  00  00  00  BP  BK  00\s
                         00  00  BP  00  00  00  BP  00\s
                         00  BP  00  00  WN  00  00  BP\s
                         00  BB  00  00  00  00  00  WP\s
                         00  BB  00  00  00  00  BN  00\s
                         BR  00  00  00  00  00  WP  00\s
                         00  00  00  WK  00  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("D1"),Position.toPosition("C1"));
        assertEquals("""
                         00  WQ  00  00  00  00  00  00\s
                         00  00  00  00  00  BP  BK  00\s
                         00  00  BP  00  00  00  BP  00\s
                         00  BP  00  00  WN  00  00  BP\s
                         00  BB  00  00  00  00  00  WP\s
                         00  BB  00  00  00  00  BN  00\s
                         BR  00  00  00  00  00  WP  00\s
                         00  00  WK  00  00  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("G3"),Position.toPosition("E2"));
        assertEquals("""
                         00  WQ  00  00  00  00  00  00\s
                         00  00  00  00  00  BP  BK  00\s
                         00  00  BP  00  00  00  BP  00\s
                         00  BP  00  00  WN  00  00  BP\s
                         00  BB  00  00  00  00  00  WP\s
                         00  BB  00  00  00  00  00  00\s
                         BR  00  00  00  BN  00  WP  00\s
                         00  00  WK  00  00  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("C1"),Position.toPosition("B1"));
        assertEquals("""
                         00  WQ  00  00  00  00  00  00\s
                         00  00  00  00  00  BP  BK  00\s
                         00  00  BP  00  00  00  BP  00\s
                         00  BP  00  00  WN  00  00  BP\s
                         00  BB  00  00  00  00  00  WP\s
                         00  BB  00  00  00  00  00  00\s
                         BR  00  00  00  BN  00  WP  00\s
                         00  WK  00  00  00  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("E2"),Position.toPosition("C3"));
        assertEquals("""
                         00  WQ  00  00  00  00  00  00\s
                         00  00  00  00  00  BP  BK  00\s
                         00  00  BP  00  00  00  BP  00\s
                         00  BP  00  00  WN  00  00  BP\s
                         00  BB  00  00  00  00  00  WP\s
                         00  BB  BN  00  00  00  00  00\s
                         BR  00  00  00  00  00  WP  00\s
                         00  WK  00  00  00  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("B1"),Position.toPosition("C1"));
        assertEquals("""
                         00  WQ  00  00  00  00  00  00\s
                         00  00  00  00  00  BP  BK  00\s
                         00  00  BP  00  00  00  BP  00\s
                         00  BP  00  00  WN  00  00  BP\s
                         00  BB  00  00  00  00  00  WP\s
                         00  BB  BN  00  00  00  00  00\s
                         BR  00  00  00  00  00  WP  00\s
                         00  00  WK  00  00  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
        chessParty.getTable().makeMove(Position.toPosition("A2"),Position.toPosition("C2"));
        assertEquals("""
                         00  WQ  00  00  00  00  00  00\s
                         00  00  00  00  00  BP  BK  00\s
                         00  00  BP  00  00  00  BP  00\s
                         00  BP  00  00  WN  00  00  BP\s
                         00  BB  00  00  00  00  00  WP\s
                         00  BB  BN  00  00  00  00  00\s
                         00  00  BR  00  00  00  WP  00\s
                         00  00  WK  00  00  00  00  00\s
                        """
                ,DrawTable.makeTableToString(chessParty.getTable())
        );
    }
}
