package modelTest;

import Java.model.ChessParty;
import Java.model.Draw.DrawTable;
import Java.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestChessNotation {
    ChessParty chessParty;
    @BeforeEach
    void init() {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        chessParty = new ChessParty(player1,player2);
    }

    @Test
    void TestChessGame(){

        chessParty.getTable().makeMove("Nf3");
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

        chessParty.getTable().makeMove("Nf6");
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

        chessParty.getTable().makeMove("c4");
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

        chessParty.getTable().makeMove("g6");
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

        chessParty.getTable().makeMove("Nc3");
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

        chessParty.getTable().makeMove("Bg7");
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

        chessParty.getTable().makeMove("d4");
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
        chessParty.getTable().makeMove("O-O");
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
        chessParty.getTable().makeMove("Bf4");
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
        chessParty.getTable().makeMove("d5");
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
        chessParty.getTable().makeMove("Qb3");
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
        chessParty.getTable().makeMove("dxc4");
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
        chessParty.getTable().makeMove("Qxc4");
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
        chessParty.getTable().makeMove("c6");
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
        chessParty.getTable().makeMove("e4");
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
        chessParty.getTable().makeMove("Nbd7");
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
        chessParty.getTable().makeMove("Rd1");
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
        chessParty.getTable().makeMove("Nb6");
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
        chessParty.getTable().makeMove("Qc5");
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
        chessParty.getTable().makeMove("Bg4");
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
        chessParty.getTable().makeMove("Bg5");
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
        chessParty.getTable().makeMove("Na4");
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
        chessParty.getTable().makeMove("Qa3");
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
        chessParty.getTable().makeMove("Nxc3");
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
        chessParty.getTable().makeMove("bxc3");
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
        chessParty.getTable().makeMove("Nxe4");
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
        chessParty.getTable().makeMove("Bxe7");
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
        chessParty.getTable().makeMove("Qb6");
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
        chessParty.getTable().makeMove("Bc4");
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
        chessParty.getTable().makeMove("Nxc3");
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
        chessParty.getTable().makeMove("Bc5");
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
        chessParty.getTable().makeMove("Rfe8+");
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
        chessParty.getTable().makeMove("Kf1");
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
        chessParty.getTable().makeMove("Be6");
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
        chessParty.getTable().makeMove("Bxb6");
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
        chessParty.getTable().makeMove("Bxc4+");
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
        chessParty.getTable().makeMove("Kg1");
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
        chessParty.getTable().makeMove("Ne2+");
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
        chessParty.getTable().makeMove("Kf1");
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

        chessParty.getTable().makeMove("Nxd4+");
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
        chessParty.getTable().makeMove("Kg1");
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
        chessParty.getTable().makeMove("Ne2+");
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
        chessParty.getTable().makeMove("Kf1");
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
        chessParty.getTable().makeMove("Nc3+");
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
        chessParty.getTable().makeMove("Kg1");
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
        chessParty.getTable().makeMove("axb6");
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
        chessParty.getTable().makeMove("Qb4");
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
        chessParty.getTable().makeMove("Ra4");
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
        chessParty.getTable().makeMove("Qxb6");
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
        chessParty.getTable().makeMove("Nxd1");
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
        chessParty.getTable().makeMove("h3");
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
        chessParty.getTable().makeMove("Rxa2");
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
        chessParty.getTable().makeMove("Kh2");
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
        chessParty.getTable().makeMove("Nxf2");
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
        chessParty.getTable().makeMove("Re1");
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
        chessParty.getTable().makeMove("Rxe1");
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
        chessParty.getTable().makeMove("Qd8+");
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
        chessParty.getTable().makeMove("Bf8");
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
        chessParty.getTable().makeMove("Nxe1");
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
        chessParty.getTable().makeMove("Bd5");
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
        chessParty.getTable().makeMove("Nf3");
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
        chessParty.getTable().makeMove("Ne4");
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
        chessParty.getTable().makeMove("Qb8");
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
        chessParty.getTable().makeMove("b5");
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
        chessParty.getTable().makeMove("h4");
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
        chessParty.getTable().makeMove("h5");
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
        chessParty.getTable().makeMove("Ne5");
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
        chessParty.getTable().makeMove("Kg7");
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
        chessParty.getTable().makeMove("Kg1");
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
        chessParty.getTable().makeMove("Bc5+");
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
        chessParty.getTable().makeMove("Kf1");
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
        chessParty.getTable().makeMove("Ng3+");
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
        chessParty.getTable().makeMove("Ke1");
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
        chessParty.getTable().makeMove("Bb4+");
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
        chessParty.getTable().makeMove("Kd1");
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
        chessParty.getTable().makeMove("Bb3+");
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
        chessParty.getTable().makeMove("Kc1");
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
        chessParty.getTable().makeMove("Ne2+");
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
        chessParty.getTable().makeMove("Kb1");
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
        chessParty.getTable().makeMove("Nc3+");
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
        chessParty.getTable().makeMove("Kc1");
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
        chessParty.getTable().makeMove("Rc2#");
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
